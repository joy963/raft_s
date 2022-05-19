package com.wang.raft_s.service;

public interface DataService {
	boolean addData(String key, String value);

	boolean deleteData(String key);

	boolean updateData(String key, String dataOld, String dataNew);

	String findData(String key);
}
