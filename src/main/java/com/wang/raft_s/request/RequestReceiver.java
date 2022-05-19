package com.wang.raft_s.request;

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
	private DataService dataService;

	@GetMapping("/add")
	@ApiOperation("add data")
	public Boolean addData(@RequestParam(value = "key", required = false) String key,
		@RequestParam(value = "value", required = false) String value) {
		return dataService.addData(key, value);
	}

	@GetMapping("/delete")
	@ApiOperation("delete data")
	public Boolean deleteData(@RequestParam(value = "key", required = false) String key) {
		return dataService.deleteData(key);
	}

	@GetMapping("/update")
	@ApiOperation("update data")
	public Boolean updateData(@RequestParam(value = "key", required = false) String key,
		@RequestParam(value = "oldData", required = false) String oldData,
		@RequestParam(value = "newData", required = false) String newData) {
		return dataService.updateData(key, oldData, newData);
	}

	@GetMapping("/query")
	@ApiOperation("query data")
	public String queryData(@RequestParam(value = "key", required = false) String key) {
		return dataService.findData(key);
	}
}
