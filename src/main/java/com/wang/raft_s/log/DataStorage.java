package com.wang.raft_s.log;

import java.util.*;
import java.util.concurrent.*;
import javax.annotation.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Component
@Slf4j
public class DataStorage {

	private Map<String, String> datas;

	@PostConstruct
	void init() {
		datas = new ConcurrentHashMap<>();
	}

	public Map<String, String> getDatas() {
		return datas;
	}
}
