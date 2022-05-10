package com.wang.raft_s.config;

import org.springframework.context.annotation.*;
import org.springframework.http.client.*;
import org.springframework.web.client.*;

@Configuration
public class RestTempConfig {
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(5000);
		return new RestTemplate(factory);
	}
}
