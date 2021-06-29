package cc.ccoder.tinylink.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * Swagger配置
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date SwaggerConfiguration.java v1.0 2021/6/29 12:57
 */
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
            // apis： 添加swagger接口提取范围
            .apis(RequestHandlerSelectors.basePackage("cc.ccoder.tinylink.controller"))
            // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("短链接服务").description("一个亲测可用的短链接服务")
            .contact(new Contact("cong-ccoder", "www.ccoder.cc", "congccoder@gmail.com")).version("1.0").build();
    }
}
