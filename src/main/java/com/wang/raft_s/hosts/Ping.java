package com.wang.raft_s.hosts;

import java.io.*;
import java.net.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class Ping {
	public boolean isReachable(String hostOrIp, int timeout) {
		try {
			InetAddress inetAddress = InetAddress.getByName(hostOrIp);
			if (!inetAddress.isReachable(timeout)) {
				return false;
			}
			log.info("host/ip:'{}' is reachable", hostOrIp);
			return true;
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
			return false;
		}
	}

	public boolean isReachable(String hostOrIp) {
		return this.isReachable(hostOrIp, 5);
	}
}
