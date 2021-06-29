package cc.ccoder.tinylink.facade;

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
 * @date TinyLinkFacade.java v1.0 2021/6/28 17:40
 */
public interface TinyLinkFacade {

    /**
     * 申请短链接
     * 
     * @param request
     *            请求参数
     * @return 响应参数
     */
    TinyLinkGenerateResponse generateTinyLink(TinyLinkRequest request);

    /**
     * 短链接查询
     * 
     * @param request
     *            请求参数
     * @return 响应参数
     */
    TinyLinkQueryResponse queryTinyLink(TinyLinkQueryRequest request);

}
