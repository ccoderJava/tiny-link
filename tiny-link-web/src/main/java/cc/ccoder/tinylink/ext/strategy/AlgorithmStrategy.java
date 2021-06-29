package cc.ccoder.tinylink.ext.strategy;

import cc.ccoder.tinylink.common.base.CodeEnum;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date AlgorithmStrategy.java v1.0 2021/6/28 23:12
 */
public enum AlgorithmStrategy implements CodeEnum {

    /**
     * 摘要算法
     */
    DIGEST("digest"),

    /**
     * 放号器
     */
    NUMBER("number");

    private final String code;

    AlgorithmStrategy(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
