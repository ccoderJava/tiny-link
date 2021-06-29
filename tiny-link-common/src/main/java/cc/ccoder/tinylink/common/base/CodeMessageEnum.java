package cc.ccoder.tinylink.common.base;

import java.io.Serializable;

/**
 * <p>
 * 带编码及描述信息的接口
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date CodeMessage.java v1.0 2021/6/28 17:43
 */
public interface CodeMessageEnum extends CodeEnum, Serializable {

    /**
     * 枚举描述
     * 
     * @return 枚举描述
     */
    String getMessage();

}
