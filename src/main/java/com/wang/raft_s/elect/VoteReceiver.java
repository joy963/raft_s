package com.wang.raft_s.elect;

import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vote")
public class VoteReceiver {

	@Autowired
	private ElectService electService;

	@GetMapping("/ask")
	public Integer receiveVote(@RequestParam("logId") Long logId,
		@RequestParam("term") Long term) {
		return electService.processVoteRequest(term, logId);
	}

}
