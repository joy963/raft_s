package com.wang.raft_s.host;

import java.util.*;
import java.util.concurrent.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class HostManager {

	@Value("#{'${seed.hosts}'.split(',')}")
	@Setter
	private CopyOnWriteArraySet<String> hostSet;

	public Set<String> getHosts() {
		return hostSet;
	}

	public int getHostSize() {
		return hostSet.size();
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
