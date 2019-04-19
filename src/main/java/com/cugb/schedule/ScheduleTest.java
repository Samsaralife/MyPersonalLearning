package com.cugb.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTest {

	int count1 = 1;
	int count2 = 1;
	@Scheduled(fixedRate=1000)
	@Async
	public void test1() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"每秒执行一次，执行第【"+count1+"】次");
		count1++;
	}
	@Scheduled(fixedRate=1000)
	@Async
	public void test2() {
		System.out.println("【"+Thread.currentThread().getName()+"】"+"每秒执行一次，执行第【"+count2+"】次");
		count2++;
	}
}
