package com.cugb.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
//Spring AOP
@Aspect
@Component
public class MyAspect {

	private final static Logger LOGGER = LoggerFactory.getLogger(MyAspect.class);
	@Pointcut("execution(* com.cugb.service.*.*(..))")
	public void pointCut()
	{
		
	}
	@org.aspectj.lang.annotation.Before("pointCut()")
	public void before()
	{
		LOGGER.info("Before start......");
	}
	
	@org.aspectj.lang.annotation.After("pointCut()")
	public void after()
	{
		LOGGER.info("After.....");
	}
	
	@AfterReturning("pointCut()")
	public void afterRunning()
	{
		LOGGER.info("After running.....");
	}
	@AfterThrowing("pointCut()")
	public void afterThrowning()
	{
		throw new RuntimeException("运行时错误......");
	}
	
}
