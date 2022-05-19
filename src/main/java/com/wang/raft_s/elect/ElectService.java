package com.wang.raft_s.elect;

import com.wang.raft_s.host.*;
import com.wang.raft_s.log.*;
import java.util.*;
import java.util.concurrent.*;
import javax.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class ElectService {

	@Autowired
	private HostManager hostManager;
	@Autowired
	private VoteSender voteSender;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private LogManager logManager;
	@Autowired
	private TermManager termManager;

	private final ForkJoinPool joinPool = new ForkJoinPool(10);

	@PostConstruct
	protected void init() {
		initiateElection();
	}

	private void initiateElection() {
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			log.warn("initiate election error ", e);
		}
		initiateVote();
	}

	public void startElection() {
		initiateVote();
	}

	private void initiateVote() {
		int totalCount = 0;
		Set<String> hosts = hostManager.getHosts();
		for (String host : hosts) {
			totalCount += joinPool.submit(new RecursiveTask<Integer>() {
				@Override
				protected Integer compute() {
					return voteSender.sendVoteMessage(host);
				}
			}).join();
		}
		if (totalCount > hosts.size() / 2) {
			log.info("vote to master!");
			roleManager.voteToMaster();
		}
	}

	private final TermCache termCache = new TermCache();

	public synchronized Integer processVoteRequest(Long term, Long logId) {
		if (termManager.getTerm() > term) {
			return 0;
		}
		if (logId < logManager.getLastLog()) {
			return 0;
		}
		int times = termCache.getOrDefault(term, 0);
		if (times == 2) {
			return 0;
		} else {
			termCache.put(term, times + 1);
			return 1;
		}
	}

	static class TermCache extends LinkedHashMap<Long, Integer> {
		public TermCache() {
			super(1000, 0.75f, true);
		}

		@Override
		protected boolean removeEldestEntry(Map.Entry<Long, Integer> eldest) {
			return this.size() > 1000;
		}
	}
}
