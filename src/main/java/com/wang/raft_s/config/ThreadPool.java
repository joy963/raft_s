package com.wang.raft_s.config;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPool {
	@Setter
	private Integer core;

	@Setter
	private Integer max;

	@Setter
	private Integer queue;

	private final Map<String, ThreadPoolExecutor> pools = new HashMap<>();

	private final Lock lock = new ReentrantLock();

	public ThreadPoolExecutor getPool(String name) {
		if (pools.containsKey(name)) {
			return pools.get(name);
		}
		if (pools.size() > 10) {
			throw new RuntimeException("pools are too many");
		}
		try {
			lock.lock();
			ThreadPoolExecutor newPool =
				new ThreadPoolExecutor(core, max, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(queue));
			pools.put(name, newPool);
			return newPool;
		} finally {
			lock.unlock();
		}
	}

}
