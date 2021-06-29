package cc.ccoder.tinylink.ext.strategy;

import org.springframework.stereotype.Component;

/**
 * <p>
 * 放号器 转化成62进制
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date NumberAssignStrategy.java v1.0 2021/6/28 23:08
 */
@Component
public class NumberAssignStrategy implements TinyLinkStrategy {
    @Override
    public String getServiceCode() {
        return AlgorithmStrategy.NUMBER.getCode();
    }

    @Override
    public String algorithmGenerate(String originLink) {
        //
        return "";
    }
}
