package cc.ccoder.tinylink.common.exception;

import cc.ccoder.tinylink.common.base.CodeEnum;
import cc.ccoder.tinylink.common.base.CodeMessageEnum;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date FailException.java v1.0 2021/6/28 20:18
 */
public class FailException extends RuntimeException {

    /** 返回码 */
    final String code;

    public FailException(CodeMessageEnum codeMessage) {
        super(codeMessage.getMessage());
        this.code = codeMessage.getCode();
    }

    public FailException(CodeEnum code, String message) {
        super(message);
        this.code = code.getCode();
    }

    public FailException(String code, String message) {
        super(message);
        this.code = code;
    }

    public FailException(CodeEnum code, CodeMessageEnum unityResultCode) {
        super(unityResultCode.getMessage());
        this.code = code != null ? code.getCode() : null;
    }

    public String getCode() {
        return code;
    }

}
