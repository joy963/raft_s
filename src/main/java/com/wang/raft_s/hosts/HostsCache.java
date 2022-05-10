package com.wang.raft_s.hosts;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import javax.annotation.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.util.*;

@Component
@Slf4j
public class HostsCache {

	@Autowired
	private Ping ping;

	@Value("#{'${seed.hosts}'.split(',')}")
	@Setter
	private List<String> hosts;

	private CopyOnWriteArraySet<String> hostSet;

	@PostConstruct
	protected void init() {
		checkHosts(hosts);
		hostSet = new CopyOnWriteArraySet<>();
		hostSet.addAll(hosts);
	}

	public Set<String> getHosts() {
		return hostSet;
	}

	public boolean existHost(String host) {
		return hostSet.contains(host);
	}

	public boolean removeHost(String host) {
		return hostSet.remove(host);
	}

	public boolean addHost(String host) {
		return hostSet.add(host);
	}

	private void checkHosts(List<String> hosts) {
		if (CollectionUtils.isEmpty(hosts)) {
			throw new RuntimeException("seed hosts is required, please check");
		}
		for (String host : hosts) {
			String[] split;
			try {
				split = host.split(":");
				Integer.parseInt(split[1]);
			} catch (Exception e) {
				throw new RuntimeException("seed hosts is error,please check");
			}
			String ip = split[0];
			if (!ping.isReachable(ip)) {
				throw new RuntimeException("seed hosts is not reachable,please check");
			}
		}
	}
}
