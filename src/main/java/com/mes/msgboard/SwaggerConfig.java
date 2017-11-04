package com.mes.msgboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.base.Predicates;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfiguration() {

		List<Parameter> globalParams = new ArrayList<>();

		globalParams.add(new ParameterBuilder().name("Authorization").description("Add Bearer token").required(true)
				.parameterType("header").build());

		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo()).select()
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.paths(Predicates.not(PathSelectors.regex("/oauth.*"))).build();
				//.globalOperationParameters(globalParams);
	}

	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("MES Api Documentation").description("Refer to the documentation below")
				.contact(new Contact("Dhrubajyoti Bhattacharjee", "url", "email")).version("1.0").build();
	}

	@Primary
	@Bean
	public ApiListingScanner addOauthOperations(ApiDescriptionReader apiDescriptionReader,
			ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
		return new OauthOperationScaner(apiDescriptionReader, apiModelReader, pluginsManager);
	}
}
