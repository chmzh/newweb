package com.cmz.web1.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SchedulerService {
	@Scheduled(cron="* * * * * ?")
	public void test(){
		//每秒执行一次该方法
	}
}
