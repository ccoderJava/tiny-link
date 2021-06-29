package cc.ccoder.tinylink.ext.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import cc.ccoder.tinylink.common.template.AbstractCodeServiceFactory;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date AlgorithmStrategyFactory.java v1.0 2021/6/29 11:56
 */
@Component
public class TinyLinkAlgorithmStrategyFactory extends AbstractCodeServiceFactory<TinyLinkStrategy> {
    public TinyLinkAlgorithmStrategyFactory(List<TinyLinkStrategy> tinyLinkStrategies) {
        super(tinyLinkStrategies);
    }

    @Override
    protected String getFactoryName() {
        return "短链生成工厂";
    }
}
