package cc.ccoder.tinylink.common.exception;

import cc.ccoder.tinylink.common.base.CodeEnum;
import cc.ccoder.tinylink.common.base.CodeMessageEnum;
import cc.ccoder.tinylink.common.common.CommonResponseCode;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date ErrorException.java v1.0 2021/6/28 20:20
 */
public class ErrorException extends RuntimeException {

    /** 返回码 */
    final String code;

    public ErrorException(String message) {
        this(CommonResponseCode.SYSTEM_ERROR, message);
    }

    public ErrorException(CodeMessageEnum codeMessage) {
        super(codeMessage.getMessage());
        this.code = codeMessage.getCode();
    }

    public ErrorException(CodeEnum code, String message) {
        super(message);
        this.code = code.getCode();
    }

    public ErrorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorException(Throwable e) {
        this(CommonResponseCode.SYSTEM_ERROR, e);
    }

    public ErrorException(String message, Throwable e) {
        this(CommonResponseCode.SYSTEM_ERROR, message, e);
    }

    public ErrorException(CodeMessageEnum codeMessage, Throwable e) {
        super(codeMessage.getMessage(), e);
        this.code = codeMessage.getCode();
    }

    public ErrorException(CodeEnum code, String message, Throwable e) {
        super(message, e);
        this.code = code.getCode();
    }

    public ErrorException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
