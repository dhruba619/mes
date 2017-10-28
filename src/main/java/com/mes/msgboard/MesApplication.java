package com.mes.msgboard;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
@EnableResourceServer
@ComponentScan(basePackages = "com.*")
public class MesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MesApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo()).select()
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.paths(Predicates.not(PathSelectors.regex("/oauth.*"))).build();
	}
	
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }		

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("MES Api Documentation")
				.description("Refer to the documentation below").description("Use the following curl command to get access token"
						+ "\n\rAdd the token in Authorisation Header as \"Bearer {token}\""
						+ "\n\r<strong>curl -X POST --user 'mes:secret' -d 'grant_type=password&username=johnd&password=1234' https://mes-beta.herokuapp.com/oauth/token</strong>"
						+ "\n\r username <strong>johnd</strong> has ADMIN rights, and can create categories."
						+ "\n\r username <strong>jacksond</strong> is a regular user. Both has same passwords")

				.contact(new Contact("name", "url", "email")).version("1.0").build();
	}
}
