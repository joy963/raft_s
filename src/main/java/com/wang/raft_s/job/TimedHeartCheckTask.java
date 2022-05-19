package com.wang.raft_s.job;

import com.wang.raft_s.config.*;
import com.wang.raft_s.host.*;
import com.wang.raft_s.service.impl.*;
import java.util.*;
import java.util.concurrent.*;
import javax.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class TimedHeartCheckTask {
	@Autowired
	private HeartCheckService heartCheckService;
	@Autowired
	private HostManager hostManager;
	@Autowired
	private ThreadPool threadPool;

	private final ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

	private ThreadPoolExecutor threadPoolExecutor;

	@PostConstruct
	public void run() {
		scheduledExecutorService.scheduleWithFixedDelay(new HeartCheckWorker(), 5, 5, TimeUnit.SECONDS);
		this.threadPoolExecutor = threadPool.getPool(this.getClass().getName());
	}

	class HeartCheckWorker implements Runnable {
		@Override
		public void run() {
			Set<String> hosts = hostManager.getHosts();
			for (String host : hosts) {
				threadPoolExecutor.execute(() -> {
					boolean heart = heartCheckService.heartCheck(host);
					if (!heart) {
						hostManager.removeHost(host);
						log.info("remove host: {}", host);
					}
				});
			}
		}
	}
}
