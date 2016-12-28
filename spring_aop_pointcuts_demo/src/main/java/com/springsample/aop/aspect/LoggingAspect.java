package com.springsample.aop.aspect;


import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.springsample.model.Customer;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	@Before("execution(* com.springsample..*Service.getCustomers(..))")
	public void enterLog(JoinPoint jp){
		logger.info("entering the method " + jp.getStaticPart().getSignature().toString());
	}
	
	@After("execution(* com.springsample..*Service.getCustomers(..))")
	public void exitLog(JoinPoint jp){
		logger.info("exiting the method " + jp.getStaticPart().getSignature().toString());
	}
	
	@Around("execution(* com.springsample..*Service.getCustomers(..))")
	public Object aroundMethod(ProceedingJoinPoint pjp){
		logger.info("around method start " + pjp.getStaticPart().getSignature().toString());
		Object result= null;
		try {
			result=pjp.proceed();
		} catch (Throwable e) {
			logger.info("around method exception " + pjp.getStaticPart().getSignature().toString());
			e.printStackTrace();
		}
		logger.info("around method end " + pjp.getStaticPart().getSignature().toString());
		return result;
	}
	
	@AfterThrowing(pointcut="execution(* com.springsample..*Service.getCustomers(..))",throwing="ex")
	public void logException(RuntimeException ex){
		logger.info("exception occured , reason : " + ex);
	}
	
	@AfterReturning(pointcut="execution(* com.springsample..*Service.getCustomers(..))", returning="list")
	public void logResult(List<Customer> list){
		logger.info("Method returned successfully with " + list.size() + " customers" );
	}
	
	@Around("execution(@com.springsample.repository.FindAll * *(..))")
	public Object annotationAdvice(ProceedingJoinPoint pjp){
		logger.info("annotation around method start " + pjp.getStaticPart().getSignature().toString());
		Object result= null;
		try {
			result=pjp.proceed();
		} catch (Throwable e) {
			logger.info("annotation around method exception " + pjp.getStaticPart().getSignature().toString());
			e.printStackTrace();
		}
		logger.info("annotation around method end " + pjp.getStaticPart().getSignature().toString());
		return result;
	}
	
	@Before("bean(customerService)")
	public void logModel(JoinPoint jp){
		logger.info("Service method call " + jp.getStaticPart().getSignature().toString());
	}
}
