package com.wang.raft_s.config;

import com.google.common.base.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(Predicates.or(PathSelectors.ant("/**")))
			.paths(Predicates.not(PathSelectors.regex("/error.*")))
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("raft alg")
			.description("Learning raft algorithm")
			.version("1.0")
			.build();
	}
}
