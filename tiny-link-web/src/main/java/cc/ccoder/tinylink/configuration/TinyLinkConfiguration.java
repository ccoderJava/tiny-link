package cc.ccoder.tinylink.configuration;

import cc.ccoder.tinylink.ext.strategy.AlgorithmStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkConfiguration.java v1.0 2021/6/28 22:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "tiny-link")
public class TinyLinkConfiguration {

    /**
     * 服务域名
     */
    private String domain;
    /**
     * 短链域名
     */
    private String tinyLinkDomain;
    /**
     * 短链接生成策略
     */
    private AlgorithmStrategy tinyStrategy;
}
