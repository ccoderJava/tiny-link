package cc.ccoder.tinylink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cc.ccoder.tinylink.common.common.ApplyStatusEnum;
import cc.ccoder.tinylink.ext.processor.GenerateTinyLinkProcessor;
import cc.ccoder.tinylink.facade.request.TinyLinkRequest;
import cc.ccoder.tinylink.facade.response.TinyLinkGenerateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkController.java v1.0 2021/6/28 17:11
 */
@Slf4j
@Api(tags = "短链接", value = "/")
@RestController
@RequestMapping(value = "/")
public class TinyLinkController {

    private final GenerateTinyLinkProcessor generateTinyLinkProcessor;

    public TinyLinkController(GenerateTinyLinkProcessor generateTinyLinkProcessor) {
        this.generateTinyLinkProcessor = generateTinyLinkProcessor;
    }

    @ApiOperation(value = "生成短链接", notes = "生成短链接", response = ResponseEntity.class)
    @RequestMapping(value = "generateTinyLink", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Object>
        generateTinyLink(@ApiParam(value = "请求参数", required = true) TinyLinkRequest tinyLinkRequest) {
        TinyLinkGenerateResponse generateResponse = generateTinyLinkProcessor.process(tinyLinkRequest);
        if (generateResponse.getApplyStatus() == ApplyStatusEnum.SUCCESS) {
            return ResponseEntity.ok(generateResponse.getTinyLinkInfo());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateResponse.getMessage());
    }

}
