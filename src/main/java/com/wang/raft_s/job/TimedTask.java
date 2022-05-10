package com.wang.raft_s.job;

import com.wang.raft_s.hosts.*;
import com.wang.raft_s.rpc.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class TimedTask {
	@Autowired
	private HeartCheck heartCheck;
	@Autowired
	private HostsCache hostsCache;

	@Scheduled(cron = "5/5 * * * * ?")
	public void run() {
		Set<String> hosts = hostsCache.getHosts();
		for (String host : hosts) {
			heartCheck.heartCheck(host);
		}
	}
}
