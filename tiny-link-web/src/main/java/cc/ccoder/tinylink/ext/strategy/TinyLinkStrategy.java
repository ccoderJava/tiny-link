package cc.ccoder.tinylink.ext.strategy;

import cc.ccoder.common.template.factory.CodeService;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkStrategy.java v1.0 2021/6/28 23:02
 */
public interface TinyLinkStrategy extends CodeService {

    /**
     * 根据配置算法生成短链接
     *
     * @param originLink 源长链接 请求参数
     * @return 响应参数
     */
    String algorithmGenerate(String originLink);
}
