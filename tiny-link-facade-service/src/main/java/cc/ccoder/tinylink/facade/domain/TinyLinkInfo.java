package cc.ccoder.tinylink.facade.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkInfo.java v1.0  2021/6/28 22:45
 */
@Data
@Builder
public class TinyLinkInfo implements Serializable {

    private String tinyLink;

    private String originLink;

    private String tinyType;

    private long timestamp;
}
