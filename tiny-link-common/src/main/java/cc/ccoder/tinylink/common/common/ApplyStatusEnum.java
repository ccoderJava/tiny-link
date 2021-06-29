package cc.ccoder.tinylink.common.common;

import cc.ccoder.tinylink.common.base.CodeMessageEnum;

/**
 * <p>
 * 申请状态枚举
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date ApplyStatusEnum.java v1.0 2021/6/28 17:44
 */
public enum ApplyStatusEnum implements CodeMessageEnum {

    /** 申请成功 */
    SUCCESS("S", "申请成功"),

    /** 申请失败 */
    FAIL("F", "申请失败"),

    /** 申请异常，结果不确定 */
    ERROR("E", "申请异常"),

    ;

    private final String code;

    private final String message;

    /**
     * 根据编码获取枚举
     *
     * @param code
     *            编码
     * @return 对应枚举类型
     */
    public static ApplyStatusEnum getByCode(String code) {
        for (ApplyStatusEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    ApplyStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
