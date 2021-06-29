package cc.ccoder.tinylink.ext.processor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cc.ccoder.tinylink.common.TinyLinkConstant;
import cc.ccoder.tinylink.common.TinyLinkType;
import cc.ccoder.tinylink.common.common.ApplyStatusEnum;
import cc.ccoder.tinylink.common.common.CommonResponseCode;
import cc.ccoder.tinylink.common.exception.FailException;
import cc.ccoder.tinylink.common.template.AbstractProcessTemplate;
import cc.ccoder.tinylink.common.util.ValidatorUtil;
import cc.ccoder.tinylink.configuration.TinyLinkConfiguration;
import cc.ccoder.tinylink.entity.TinyLink;
import cc.ccoder.tinylink.ext.integration.CacheClient;
import cc.ccoder.tinylink.ext.strategy.TinyLinkAlgorithmStrategyFactory;
import cc.ccoder.tinylink.facade.domain.TinyLinkInfo;
import cc.ccoder.tinylink.facade.request.TinyLinkRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkGenerateResponse;
import cc.ccoder.tinylink.repository.TinyLinkRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date GenerateTinyLinkProcessor.java v1.0 2021/6/28 20:36
 */
@Slf4j
@Service
public class GenerateTinyLinkProcessor extends AbstractProcessTemplate<TinyLinkRequest, TinyLinkGenerateResponse> {

    private final TinyLinkConfiguration tinyLinkConfiguration;

    private final TinyLinkRepository tinyLinkRepository;

    private final TinyLinkAlgorithmStrategyFactory tinyLinkStrategyFactory;

    private final CacheClient cacheClient;

    public GenerateTinyLinkProcessor(TinyLinkConfiguration tinyLinkConfiguration, TinyLinkRepository tinyLinkRepository,
        TinyLinkAlgorithmStrategyFactory tinyLinkStrategyFactory, CacheClient cacheClient) {
        this.tinyLinkConfiguration = tinyLinkConfiguration;
        this.tinyLinkRepository = tinyLinkRepository;
        this.tinyLinkStrategyFactory = tinyLinkStrategyFactory;
        this.cacheClient = cacheClient;
    }

    @Override
    protected String getServiceName() {
        return "申请短链接";
    }

    @Override
    protected TinyLinkGenerateResponse createEmptyResponse() {
        return new TinyLinkGenerateResponse();
    }

    @Override
    protected void validate(TinyLinkRequest request) {
        ValidatorUtil.validate(request);
        // 输入的源链接如果是短链接则不能继续生成
        if (request.getOriginLink().startsWith(tinyLinkConfiguration.getTinyLinkDomain())) {
            throw new FailException(CommonResponseCode.BIZ_CHECK_FAIL, "源链接不可输入短链接");
        }
        // 校验自定义短链接
        validateCustomTinyLink(request);
        // 校验协议
        request.setOriginLink(getOriginalLink(request.getOriginLink()));
    }

    @Override
    protected void process(TinyLinkRequest request, TinyLinkGenerateResponse response) {
        String originDigest = DigestUtils.md5DigestAsHex(request.getOriginLink().getBytes(StandardCharsets.UTF_8));
        TinyLink tinyLink = tinyLinkRepository.loadOriginLinkSummary(originDigest);
        if (tinyLink != null) {
            // 已存在 放入缓存 直接返回
            cacheClient.put(tinyLink.getTinyLink(), tinyLink.getOriginLink());
            TinyLinkInfo tinyLinkInfo = TinyLinkInfo.builder().tinyType(tinyLink.getLinkType())
                .tinyLink(tinyLinkConfiguration.getTinyLinkDomain() + tinyLink.getTinyLink())
                .originLink(request.getOriginLink()).timestamp(System.currentTimeMillis()).build();
            response.setTinyLinkInfo(tinyLinkInfo);
            response.setApplyStatus(ApplyStatusEnum.SUCCESS);
            return;
        }

        TinyLink currentTinyLink = new TinyLink();
        currentTinyLink.setOriginLink(request.getOriginLink());
        currentTinyLink.setOriginLinkSummary(originDigest);
        // FIXME: 2021/6/29 有效期间 待定开发 默认长期有效
        currentTinyLink.setExpiredTime(null);
        currentTinyLink.setCreateTime(LocalDateTime.now());
        currentTinyLink.setUpdateTime(LocalDateTime.now());
        if (StringUtils.isBlank(request.getCustomTinyLink())) {
            // 系统计算生成短链接
            String link = tinyLinkStrategyFactory.getService(tinyLinkConfiguration.getTinyStrategy().getCode())
                .algorithmGenerate(request.getOriginLink());
            currentTinyLink.setTinyLink(link);
            currentTinyLink.setLinkType(TinyLinkType.SYSTEM.getCode());
        } else {
            // 用户自定义短链
            currentTinyLink.setTinyLink(request.getCustomTinyLink());
            currentTinyLink.setLinkType(TinyLinkType.CUSTOM.getCode());
        }
        tinyLinkRepository.create(currentTinyLink);
        cacheClient.put(currentTinyLink.getTinyLink(), currentTinyLink.getOriginLink());

        response.setApplyStatus(ApplyStatusEnum.SUCCESS);
        TinyLinkInfo tinyLinkInfo = TinyLinkInfo.builder().tinyType(currentTinyLink.getLinkType())
            .tinyLink(tinyLinkConfiguration.getTinyLinkDomain() + currentTinyLink.getTinyLink())
            .originLink(request.getOriginLink()).timestamp(System.currentTimeMillis()).build();
        response.setTinyLinkInfo(tinyLinkInfo);
    }

    /**
     * 校验自定义短链接是否已经存在
     * 
     * @param request
     *            请求参数
     */
    private void validateCustomTinyLink(TinyLinkRequest request) {
        if (StringUtils.isBlank(request.getCustomTinyLink())) {
            return;
        }
        TinyLink tinyLink = tinyLinkRepository.queryTinyLink(request.getCustomTinyLink());
        if (tinyLink != null) {
            throw new FailException(CommonResponseCode.BIZ_CHECK_FAIL,
                String.format("自定义短链接[%s]已存在", request.getCustomTinyLink()));
        }
    }

    /**
     * 补足源链接协议
     * 
     * @param originLink
     *            源链接
     * @return 源链接
     */
    private String getOriginalLink(String originLink) {
        if (!originLink.startsWith(TinyLinkConstant.HTTP_LINK) && !originLink.startsWith(TinyLinkConstant.HTTPS_LINK)) {
            originLink = TinyLinkConstant.HTTP_LINK + originLink;
        }
        return originLink;
    }

}
