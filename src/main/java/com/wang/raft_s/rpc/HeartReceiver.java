package com.wang.raft_s.rpc;

import com.wang.raft_s.hosts.*;
import javax.servlet.http.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heart")
@Slf4j
public class HeartReceiver {
	@Autowired
	private HostsCache hostsCache;

	@GetMapping("/beat/{port}")
	public Boolean sendBeat(@PathVariable("port") int port, HttpServletRequest request) {
		String ip = request.getRemoteHost();
		String url = ip + ":" + port;
		if (!hostsCache.existHost(url)) {
			hostsCache.addHost(url);
			log.info("find host:{}", url);
		}
		return true;
	}
}
