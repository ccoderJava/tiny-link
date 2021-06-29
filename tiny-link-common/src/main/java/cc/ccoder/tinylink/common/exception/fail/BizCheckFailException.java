package cc.ccoder.tinylink.common.exception.fail;

import cc.ccoder.tinylink.common.common.CommonResponseCode;
import cc.ccoder.tinylink.common.exception.FailException;

/**
 * <p>
 * 业务校验异常
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date BizCheckFailException.java v1.0 2021/6/28 20:22
 */
public class BizCheckFailException extends FailException {

    public BizCheckFailException(String message) {
        super(CommonResponseCode.BIZ_CHECK_FAIL, message);
    }

}