package com.wang.raft_s.log;

import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class LogManager {
	private static final int size = 10000;
	private LogEntry[] logs;

	{
		logs = new LogEntry[size];
	}

	public Long getLastLog() {
		return logs[size].getId();
	}

	private volatile int index = 0;

	private volatile long coverageId = -1;

	public synchronized void addLog(LogEntry logEntry) {
		if (index == size) {
			coverageId = index;
		}
		logs[index % size] = logEntry;
		index++;
	}

	public long getCoverageId() {
		return coverageId;
	}
}
