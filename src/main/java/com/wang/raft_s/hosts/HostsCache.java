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

	@Value("#{'${seed.hosts}'.split(',')}")
	@Setter
	private List<String> hosts;

	private CopyOnWriteArraySet<String> hostSet;

	@PostConstruct
	protected void init() {
		hostSet = new CopyOnWriteArraySet<>();
		hostSet.addAll(hosts);
	}

	public Set<String> getHosts() {
		return hostSet;
	}

	public boolean existHost(String host) {
		return hostSet.contains(host);
	}

	public void removeHost(String host) {
		hostSet.remove(host);
	}

	public void addHost(String host) {
		hostSet.add(host);
	}
}
