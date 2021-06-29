package cc.ccoder.tinylink.ext;

import org.springframework.stereotype.Component;

import cc.ccoder.tinylink.ext.processor.GenerateTinyLinkProcessor;
import cc.ccoder.tinylink.ext.processor.QueryTinyLinkProcessor;
import cc.ccoder.tinylink.facade.TinyLinkFacade;
import cc.ccoder.tinylink.facade.request.TinyLinkQueryRequest;
import cc.ccoder.tinylink.facade.request.TinyLinkRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkGenerateResponse;
import cc.ccoder.tinylink.facade.response.TinyLinkQueryResponse;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkFacadeImpl.java v1.0 2021/6/28 20:35
 */
@Component
public class TinyLinkFacadeImpl implements TinyLinkFacade {

    private final GenerateTinyLinkProcessor generateTinyLinkProcessor;

    private final QueryTinyLinkProcessor queryTinyLinkProcessor;

    public TinyLinkFacadeImpl(GenerateTinyLinkProcessor generateTinyLinkProcessor,
        QueryTinyLinkProcessor queryTinyLinkProcessor) {
        this.generateTinyLinkProcessor = generateTinyLinkProcessor;
        this.queryTinyLinkProcessor = queryTinyLinkProcessor;
    }

    @Override
    public TinyLinkGenerateResponse generateTinyLink(TinyLinkRequest request) {
        return generateTinyLinkProcessor.process(request);
    }

    @Override
    public TinyLinkQueryResponse queryTinyLink(TinyLinkQueryRequest request) {
        return queryTinyLinkProcessor.process(request);
    }
}
