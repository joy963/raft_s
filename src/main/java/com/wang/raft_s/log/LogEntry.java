package com.wang.raft_s.log;

import lombok.*;

@Setter
@Getter
public class LogEntry {
	private Long id;
	private String log;
}
