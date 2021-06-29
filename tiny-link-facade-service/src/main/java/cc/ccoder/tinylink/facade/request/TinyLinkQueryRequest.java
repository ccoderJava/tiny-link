package cc.ccoder.tinylink.facade.request;

import cc.ccoder.tinylink.common.request.AbstractRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkQueryRequest.java v1.0 2021/6/29 12:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TinyLinkQueryRequest extends AbstractRequest {

    private String tinyLink;

}
