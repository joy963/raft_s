package com.wang.raft_s.service.impl;

import com.wang.raft_s.log.*;
import com.wang.raft_s.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Slf4j
@Service(value = "dataMaster")
public class DataServiceMasterImpl implements DataService {

	@Autowired
	private DataStorage dataStorage;

	@Override
	public boolean addData(String key, String value) {
		dataStorage.getDatas().put(key, value);
		return true;
	}

	@Override
	public boolean deleteData(String key) {
		dataStorage.getDatas().remove(key);
		return true;
	}

	@Override
	public boolean updateData(String key, String dataNew, Boolean upsert) {
		if (dataStorage.getDatas().containsKey(key) || upsert) {
			dataStorage.getDatas().put(key, dataNew);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public String findData(String key) {
		return dataStorage.getDatas().get(key);
	}
}
