package cc.ccoder.tinylink.controller;

import cc.ccoder.common.base.ApplyStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.ccoder.tinylink.ext.processor.QueryTinyLinkProcessor;
import cc.ccoder.tinylink.facade.request.TinyLinkQueryRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkQueryResponse;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date RedirectController.java v1.0 2021/6/29 12:24
 */
@Controller
@RequestMapping("/t")
public class RedirectController {

    private final QueryTinyLinkProcessor queryTinyLinkProcessor;

    public RedirectController(QueryTinyLinkProcessor queryTinyLinkProcessor) {
        this.queryTinyLinkProcessor = queryTinyLinkProcessor;
    }

    @ApiIgnore
    @GetMapping("/{tinyLink}")
    public String redirect(@PathVariable String tinyLink) {
        TinyLinkQueryRequest request = new TinyLinkQueryRequest();
        request.setTinyLink(tinyLink);
        TinyLinkQueryResponse queryResponse = queryTinyLinkProcessor.process(request);
        if (queryResponse.getApplyStatus() != ApplyStatusEnum.SUCCESS) {
            return "redirect:" + "error/404";
        }
        return "redirect:" + queryResponse.getOriginLink();
    }
}
