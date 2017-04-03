package com.devils.hr.configs;

import com.devils.hr.responses.RespWrapper;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * Created by AndyL on 2017/4/3.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigs {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devils.hr.controllers"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .securitySchemes(newArrayList(apiKey()))
                .enableUrlTemplating(true)
                .tags(new Tag("HR EDU 云服务", "All apis relating to HR EDU"))
                .additionalModels(typeResolver.resolve(RespWrapper.class))
                .apiInfo(new ApiInfo("HR EDU 教育服务平台", "Api 在线文档", "v1.0.0",
                        "", new Contact("Andy", "", ""), "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0"))
                ;
    }

    private ApiKey apiKey() {
        return new ApiKey("hr_key", "weilaizhanshi", "shengage");
    }

}
