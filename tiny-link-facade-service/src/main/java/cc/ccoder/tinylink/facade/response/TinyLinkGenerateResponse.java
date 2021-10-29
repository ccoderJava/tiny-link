package cc.ccoder.tinylink.facade.response;

import cc.ccoder.common.response.CommonResponse;
import cc.ccoder.tinylink.facade.domain.TinyLinkInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date ServerResponse.java v1.0 2021/6/28 17:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class TinyLinkGenerateResponse extends CommonResponse {

    private TinyLinkInfo tinyLinkInfo;

}
