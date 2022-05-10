package com.wang.raft_s.rpc;

import com.wang.raft_s.hosts.*;
import javax.servlet.http.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heart")
@Slf4j
public class HeartSend {
	@Autowired
	private HostsCache hostsCache;

	@GetMapping("/beat")
	public Boolean sendBeat(HttpServletRequest request) {
		String remoteHost = request.getRemoteHost();
		if (!hostsCache.existHost(remoteHost)) {
			hostsCache.addHost(remoteHost);
			log.info("find host:{}", remoteHost);
		}
		return true;
	}
}
