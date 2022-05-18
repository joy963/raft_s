package com.wang.raft_s.rpc;

import com.wang.raft_s.hosts.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Component
@Slf4j
public class HeartCheck {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HostsCache hostsCache;

	@Value("${server.port}")
	private Integer port;

	public boolean heartCheck(String host) {
		if (!host.contains("/")) {
			host += "/heart/beat/" + port;
		}
		try {
			restTemplate.getForObject("http://" + host, Boolean.class);
		} catch (Exception e) {
			log.info("heart broken from:{},{}", host, e.getMessage());
			return false;
		}
		return true;
	}
}
