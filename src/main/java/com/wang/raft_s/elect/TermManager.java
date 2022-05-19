package com.wang.raft_s.elect;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class TermManager {

	private final AtomicLong term = new AtomicLong(0);

	private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

	public Long incTerm() {
		return term.incrementAndGet();
	}

	public Long getTerm() {
		return term.get();
	}

	public void setTerm(Long t) {
		term.set(t);
	}
}
