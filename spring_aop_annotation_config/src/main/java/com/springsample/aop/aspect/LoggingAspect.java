package com.springsample.aop.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(* getCustomers())")
	public void enterLog(JoinPoint jp){
		logger.info("entering the method " + jp.getStaticPart().getSignature().toString());
	}
	
	@After("execution(* getCustomers())")
	public void exitLog(JoinPoint jp){
		logger.info("exiting the method " + jp.getStaticPart().getSignature().toString());
	}
}
