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

	public boolean heartCheck(String host, int port) {
		String url = String.format("%s%d%s", host, port, "/heart/beat");
		return this.heartCheck(url);
	}

	public boolean heartCheck(String host) {
		if (!host.contains("/")) {
			host += "/heart/beat";
		}
		Boolean heartExist = restTemplate.getForObject(host, Boolean.class);
		if (heartExist == null || !heartExist) {
			hostsCache.removeHost(host);
			log.info("heart broken from:{}", host);
			return false;
		}
		return true;
	}
}
