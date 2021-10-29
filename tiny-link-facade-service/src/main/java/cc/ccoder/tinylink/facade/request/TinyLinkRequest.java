package cc.ccoder.tinylink.facade.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import cc.ccoder.common.request.AbstractRequest;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkRequest.java v1.0 2021/6/28 17:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TinyLinkRequest extends AbstractRequest {

    /**
     * 源长链接
     */
    @NotBlank(message = "源长链接不能为空")
    @Length(max = 512, message = "源长链接长度不能超过512")
    @Pattern(regexp = "^(http|https)://([^/:]+)(:\\d*)?(.*)$", message = "长链接必须以http或https开头")
    private String originLink;

    /**
     * 自定义短链接
     */
    private String customTinyLink;


}
