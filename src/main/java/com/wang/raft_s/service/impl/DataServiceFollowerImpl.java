package com.wang.raft_s.service.impl;

import com.wang.raft_s.service.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Slf4j
@Service(value = "dataFollower")
public class DataServiceFollowerImpl implements DataService {
	@Override
	public boolean addData(String key, String value) {
		return false;
	}

	@Override
	public boolean deleteData(String key) {
		return false;
	}

	@Override
	public boolean updateData(String key, String dataNew, Boolean upsert) {
		return false;
	}

	@Override
	public String findData(String key) {
		return null;
	}
}
