package cc.ccoder.tinylink.common.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 可以通过code映射的服务工厂
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date AbstractCodeServiceFactory.java v1.0 2021/6/28 20:00
 */
@Slf4j
public abstract class AbstractCodeServiceFactory<Provider extends CodeService> implements CodeServiceFactory<Provider> {

    private Map<String, Provider> serviceProviderMap = new HashMap<>();

    public AbstractCodeServiceFactory(List<Provider> providers) {
        initializeProviderMap(providers);
    }

    /**
     * 初始化注册服务
     * 
     * @param providers
     *            服务接口
     */
    private void initializeProviderMap(List<Provider> providers) {
        log.info("初始化工厂服务:{}", getFactoryName());
        if (providers == null) {
            return;
        }
        for (Provider provider : providers) {
            String serviceCode = provider.getServiceCode();
            if (serviceCode == null) {
                throw new IllegalArgumentException(String.format("注册服务编码不可为空:%s", provider.getClass()));
            }
            if (!serviceProviderMap.containsKey(serviceCode)) {
                serviceProviderMap.put(serviceCode, provider);
                log.info("已注册服务: {}, {}", serviceCode, provider.getClass());
            } else {
                throw new IllegalArgumentException(String.format("注册服务重复: %s, %s, %s", serviceCode,
                    serviceProviderMap.get(serviceCode).getClass(), provider.getClass()));
            }
        }
    }

    /**
     * 获取服务 服务接口不存在时返回null
     * 
     * @param serviceCode
     *            服务编码
     * @return 服务接口
     */
    @Override
    public Provider getService(String serviceCode) {
        return serviceProviderMap.get(serviceCode);
    }

    /**
     * 服务工厂名称
     *
     * @return 工厂名称用于日志
     */
    protected abstract String getFactoryName();
}
