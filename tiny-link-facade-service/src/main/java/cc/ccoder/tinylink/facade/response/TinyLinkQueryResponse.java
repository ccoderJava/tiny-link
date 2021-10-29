package cc.ccoder.tinylink.facade.response;

import cc.ccoder.common.response.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p></p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkQueryResponse.java v1.0  2021/6/29 12:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TinyLinkQueryResponse extends CommonResponse {

    private String originLink;

}
