package cc.ccoder.tinylink.ext.processor;

import cc.ccoder.common.base.ApplyStatusEnum;
import cc.ccoder.common.base.CommonResponseCode;
import cc.ccoder.common.exception.FailException;
import cc.ccoder.common.template.factory.AbstractProcessTemplate;
import cc.ccoder.tinylink.entity.TinyLink;
import cc.ccoder.tinylink.ext.integration.CacheClient;
import cc.ccoder.tinylink.facade.request.TinyLinkQueryRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkQueryResponse;
import cc.ccoder.tinylink.repository.TinyLinkRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date QueryTinyLinkProcessor.java v1.0 2021/6/29 12:26
 */
@Component
public class QueryTinyLinkProcessor extends AbstractProcessTemplate<TinyLinkQueryRequest, TinyLinkQueryResponse> {

    private final CacheClient cacheClient;

    private final TinyLinkRepository tinyLinkRepository;

    public QueryTinyLinkProcessor(CacheClient cacheClient, TinyLinkRepository tinyLinkRepository) {
        this.cacheClient = cacheClient;
        this.tinyLinkRepository = tinyLinkRepository;
    }

    @Override
    protected String getServiceName() {
        return "查询短链";
    }

    @Override
    protected TinyLinkQueryResponse createEmptyResponse() {
        return new TinyLinkQueryResponse();
    }

    @Override
    protected void validate(TinyLinkQueryRequest request) {

    }

    @Override
    protected void process(TinyLinkQueryRequest request, TinyLinkQueryResponse response) {
        try {
            String originLink = (String) cacheClient.get(request.getTinyLink(), () -> {
                TinyLink tinyLink = tinyLinkRepository.queryTinyLink(request.getTinyLink());
                if (tinyLink == null) {
                    throw new FailException(CommonResponseCode.NOT_EXIST, CommonResponseCode.NOT_EXIST.getMessage());
                }
                return tinyLink.getOriginLink();
            });
            response.setApplyStatus(ApplyStatusEnum.SUCCESS);
            response.setOriginLink(originLink);
        } catch (ExecutionException e) {
            throw new FailException(CommonResponseCode.SYSTEM_ERROR, e.getMessage());
        }
    }
}
