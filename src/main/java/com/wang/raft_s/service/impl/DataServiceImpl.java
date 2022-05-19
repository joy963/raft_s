package com.wang.raft_s.service.impl;

import com.wang.raft_s.service.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

	@Override
	public boolean addData(String key, String value) {
		return false;
	}

	@Override
	public boolean deleteData(String key) {
		return false;
	}

	@Override
	public boolean updateData(String key, String dataOld, String dataNew) {
		return false;
	}

	@Override
	public String findData(String key) {
		return null;
	}
}
