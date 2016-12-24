package com.springsample.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(* getCustomers())")
	public void startLog(){
		logger.info("entering the method");
	}
}
