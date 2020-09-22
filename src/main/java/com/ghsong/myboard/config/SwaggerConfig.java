package com.ghsong.myboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .useDefaultResponseMessages(false)      // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
                .ignoredParameterTypes(ServletRequest.class, ServletResponse.class)     // controller 메소드의 파라미터중 제외할것들
                .select()
                .apis(basePackage("com.ghsong.myboard.modules"))
                .paths(any())
                .build()
        ;
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("타이틀")
                .description("설명")
                .license("라이센스")
                .build();
    }

}
