package com.wang.raft_s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EnableScheduling
public class RaftSApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaftSApplication.class, args);
	}

}
