package com.springsample.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAspect {
	
	private static final Logger logger = Logger.getLogger(TransactionAspect.class);
	
	@Around("com.springsample.pointcuts.CustomerPointCuts.traceTransaction()")
	public Object aroundMethod(ProceedingJoinPoint pjp){
		logger.info("transaction start ----------------> " + pjp.getStaticPart().getSignature().toString());
		Object result= null;
		try {
			result=pjp.proceed();
		} catch (Throwable e) {
			logger.info("transaction rollback ----------> " + pjp.getStaticPart().getSignature().toString());
			e.printStackTrace();
		}
		logger.info("transaction end -------------> " + pjp.getStaticPart().getSignature().toString());
		return result;
	}

}
