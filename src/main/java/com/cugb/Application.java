package com.cugb;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cugb.interceptor.MyInterceptor;

@SpringBootApplication
@MapperScan(basePackages= {"com.cugb.dao"})
//@EnableScheduling
public class Application implements WebMvcConfigurer{

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	public static void main(String []args)
	{
		SpringApplication.run(Application.class, args);
		LOGGER.info("application start..........");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		//注册拦截器到Spring MVC机制，然后它会返回一个拦截器注册
		InterceptorRegistration ir =  registry.addInterceptor(new MyInterceptor());
		//指定拦截匹配模式，限制拦截器拦截请求
		ir.addPathPatterns("/file/*");
	}
}
