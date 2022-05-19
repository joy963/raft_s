package com.wang.raft_s.request;

import com.wang.raft_s.elect.*;
import com.wang.raft_s.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
@Slf4j
public class RequestReceiver {

	@Autowired
	private RoleManager roleManager;

	@Autowired
	@Qualifier("dataMaster")
	private DataService dataMasterService;

	@Autowired
	@Qualifier("dataFollower")
	private DataService dataFollowerService;

	@GetMapping("/add")
	@ApiOperation("add data")
	public Boolean addData(@RequestParam(value = "key") String key,
		@RequestParam(value = "value", required = false) String value) {
		if (roleManager.isMaster()) {
			return dataMasterService.addData(key, value);
		}
		return dataFollowerService.addData(key, value);
	}

	@GetMapping("/delete")
	@ApiOperation("delete data")
	public Boolean deleteData(@RequestParam(value = "key") String key) {
		if (roleManager.isMaster()) {
			return dataMasterService.deleteData(key);
		}
		return dataFollowerService.deleteData(key);
	}

	@GetMapping("/update")
	@ApiOperation("update data")
	public Boolean updateData(@RequestParam(value = "key") String key,
		@RequestParam(value = "newData") String newData,
		@RequestParam(value = "upsert") Boolean upsert) {
		if (roleManager.isMaster()) {
			return dataMasterService.updateData(key, newData, upsert);
		}
		return dataFollowerService.updateData(key, newData, upsert);
	}

	@GetMapping("/query")
	@ApiOperation("query data")
	public String queryData(@RequestParam(value = "key") String key) {
		if (roleManager.isMaster()) {
			return dataMasterService.findData(key);
		}
		return dataFollowerService.findData(key);
	}

	@GetMapping("/is-master")
	@ApiOperation("check is master")
	public Boolean checkMaster() {
		return roleManager.isMaster();
	}
}
