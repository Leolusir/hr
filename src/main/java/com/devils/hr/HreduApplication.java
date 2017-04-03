package com.devils.hr;

import com.devils.hr.responses.RespWrapper;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.devils.hr.controllers")
public class HreduApplication implements CommandLineRunner {

	@Autowired
	private TypeResolver typeResolver;

	public static void main(String[] args) {
		SpringApplication.run(HreduApplication.class, args);
    }

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("                                                        ");
		System.out.println("                                                        ");
		System.out.println("-----------------  Project HuiRen EDU  -----------------");
		System.out.println("                                                        ");
		System.out.println("                 ___====-_  _-====___");
		System.out.println("           _--^^^#####//      \\#####^^^--_");
		System.out.println("        _-^##########// (    ) \\##########^-_");
		System.out.println("       -############//  |\\^^/|  \\############-");
		System.out.println("     _/############//   (@::@)   \\############\\_");
		System.out.println("    /#############((     \\\\//     ))#############\\");
		System.out.println("   -###############\\     (oo)    //###############-");
		System.out.println("  -#################\\   / VV \\  //#################-");
		System.out.println(" -###################\\ /      \\//###################-");
		System.out.println("_#/|##########/\\######(   /\\   )######/\\##########|\\#_");
		System.out.println("|/ |#/\\#/\\#/\\/  \\#/\\##\\  |  |  /##/\\#/  \\/\\#/\\#/\\#| \\|");
		System.out.println("`  |/  V  V  `   V  \\#\\| |  | |/#/  V   '  V  V  \\|  '");
		System.out.println("   `   `  `      `   / | |  | | \\   '      '  '   '");
		System.out.println("                    (  | |  | |  )");
		System.out.println("                   __\\ | |  | | /__");
		System.out.println("                  (vvv(VVV)(VVV)vvv)");
		System.out.println("                                                      ");
		System.out.println("-----------------  Server start now  -----------------");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
	}

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
				.securityContexts(newArrayList(securityContext()))
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

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(
				new SecurityReference("mykey", authorizationScopes));
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				"hr-app-client-id",
				"hr-app-client-secret",
				"hr-app-realm",
				"hr-app",
				"hr-apiKey",
				ApiKeyVehicle.HEADER,
				"hr-api_key",
				"," /*scope separator*/);
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration(
				"validatorUrl",// url
				"none",       // docExpansion          => none | list
				"alpha",      // apiSorter             => alpha
				"schema",     // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
				false,        // enableJsonEditor      => true | false
				true,         // showRequestHeaders    => true | false
				60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
	}
}
