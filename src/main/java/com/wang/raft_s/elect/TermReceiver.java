package com.wang.raft_s.elect;

import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/term")
public class TermReceiver {
	@Autowired
	private TermManager termManager;

	@GetMapping("/renew")
	public Boolean renewalTerm(@RequestParam("term") Long term, HttpServletRequest request) {
		termManager.setTerm(term);
		return true;
	}
}
