package cc.ccoder.tinylink.common.template;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date CodeServiceFacatory.java v1.0 2021/6/28 19:43
 */
public interface CodeServiceFactory<Provider extends CodeService> {

    /**
     * 获取服务
     * 
     * @param serviceCode
     *            服务编码
     * @return 返回服务，不存在时返回null
     */
    Provider getService(String serviceCode);
}
