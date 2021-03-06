package cc.ccoder.tinylink.ext.processor;

import cc.ccoder.common.base.ApplyStatusEnum;
import cc.ccoder.common.base.CommonResponseCode;
import cc.ccoder.common.exception.FailException;
import cc.ccoder.common.template.factory.AbstractProcessTemplate;
import cc.ccoder.common.util.ValidatorUtil;
import cc.ccoder.tinylink.common.TinyLinkConstant;
import cc.ccoder.tinylink.common.TinyLinkType;
import cc.ccoder.tinylink.configuration.TinyLinkConfiguration;
import cc.ccoder.tinylink.entity.TinyLink;
import cc.ccoder.tinylink.ext.integration.CacheClient;
import cc.ccoder.tinylink.ext.strategy.TinyLinkAlgorithmStrategyFactory;
import cc.ccoder.tinylink.facade.domain.TinyLinkInfo;
import cc.ccoder.tinylink.facade.request.TinyLinkRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkGenerateResponse;
import cc.ccoder.tinylink.repository.TinyLinkRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

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
        return "???????????????";
    }

    @Override
    protected TinyLinkGenerateResponse createEmptyResponse() {
        return new TinyLinkGenerateResponse();
    }

    @Override
    protected void validate(TinyLinkRequest request) {
        ValidatorUtil.validate(request);
        // ?????????????????????????????????????????????????????????
        if (request.getOriginLink().startsWith(tinyLinkConfiguration.getTinyLinkDomain())) {
            throw new FailException(CommonResponseCode.BIZ_CHECK_FAIL, "??????????????????????????????");
        }
        // ????????????????????????
        validateCustomTinyLink(request);
        // ????????????
        request.setOriginLink(getOriginalLink(request.getOriginLink()));
    }

    @Override
    protected void process(TinyLinkRequest request, TinyLinkGenerateResponse response) {
        String originDigest = DigestUtils.md5DigestAsHex(request.getOriginLink().getBytes(StandardCharsets.UTF_8));
        TinyLink tinyLink = tinyLinkRepository.loadOriginLinkSummary(originDigest);
        if (tinyLink != null) {
            // ????????? ???????????? ????????????
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
        // FIXME: 2021/6/29 ???????????? ???????????? ??????????????????
        currentTinyLink.setExpiredTime(null);
        currentTinyLink.setCreateTime(LocalDateTime.now());
        currentTinyLink.setUpdateTime(LocalDateTime.now());
        if (StringUtils.isBlank(request.getCustomTinyLink())) {
            // ???????????????????????????
            String link = tinyLinkStrategyFactory.getService(tinyLinkConfiguration.getTinyStrategy().getCode())
                    .algorithmGenerate(request.getOriginLink());
            currentTinyLink.setTinyLink(link);
            currentTinyLink.setLinkType(TinyLinkType.SYSTEM.getCode());
        } else {
            // ?????????????????????
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
     * ??????????????????????????????????????????
     *
     * @param request ????????????
     */
    private void validateCustomTinyLink(TinyLinkRequest request) {
        if (StringUtils.isBlank(request.getCustomTinyLink())) {
            return;
        }
        TinyLink tinyLink = tinyLinkRepository.queryTinyLink(request.getCustomTinyLink());
        if (tinyLink != null) {
            throw new FailException(CommonResponseCode.BIZ_CHECK_FAIL,
                    String.format("??????????????????[%s]?????????", request.getCustomTinyLink()));
        }
    }

    /**
     * ?????????????????????
     *
     * @param originLink ?????????
     * @return ?????????
     */
    private String getOriginalLink(String originLink) {
        if (!originLink.startsWith(TinyLinkConstant.HTTP_LINK) && !originLink.startsWith(TinyLinkConstant.HTTPS_LINK)) {
            originLink = TinyLinkConstant.HTTP_LINK + originLink;
        }
        return originLink;
    }

}
