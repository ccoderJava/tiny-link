package cc.ccoder.tinylink.common;

import cc.ccoder.common.base.CodeMessageEnum;
import org.apache.commons.lang3.StringUtils;


/**
 * <p>
 * 短链生成方式
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkType.java v1.0 2021/6/29 12:05
 */
public enum TinyLinkType implements CodeMessageEnum {
    /**
     * 系统生成
     */
    SYSTEM("S", "system"),

    /**
     * 自定义生成
     */
    CUSTOM("C", "custom");

    private final String code;

    private final String message;

    TinyLinkType(String code, String message) {
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

    public static TinyLinkType getCode(String code) {
        for (TinyLinkType type : TinyLinkType.values()) {
            if (StringUtils.equalsIgnoreCase(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }
}
