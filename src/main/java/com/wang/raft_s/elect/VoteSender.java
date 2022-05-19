package com.wang.raft_s.elect;

import com.wang.raft_s.log.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Component
@Slf4j
public class VoteSender {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LogManager logManager;
	@Autowired
	private TermManager termManager;
	String voteUrl = "/vote/ask";

	public int sendVoteMessage(String host) {
		Integer result;
		try {
			result = restTemplate
				.getForObject(
					"http://" + host + voteUrl + "?logId=" + logManager.getLastLog() + "&term=" + termManager.getTerm(),
					Integer.class);
		} catch (Exception e) {
			return 0;
		}
		return result == null ? 0 : result;
	}
}
