package com.wang.raft_s.job;

import com.wang.raft_s.hosts.*;
import com.wang.raft_s.rpc.*;
import java.util.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class TimedTask {
	@Autowired
	private HeartCheck heartCheck;
	@Autowired
	private HostsCache hostsCache;

	@Scheduled(cron = "5/5 * * * * ?")
	public void run() {
		Set<String> hosts = hostsCache.getHosts();
		for (String host : hosts) {
			boolean heart = heartCheck.heartCheck(host);
			if (!heart) {
				hostsCache.removeHost(host);
				log.info("remove host: {}", host);
			}
		}
	}
}
