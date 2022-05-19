package com.wang.raft_s.elect;

import org.springframework.stereotype.*;

@Component
public class RoleManager {

	private volatile boolean isMaster = false;

	public boolean isMaster() {
		return isMaster;
	}

	public boolean isFollower() {
		return !isMaster;
	}

	public synchronized void voteToMaster() {
		isMaster = true;
	}
}
