package com.springsample.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class CustomerPointCuts {
	
	@Pointcut("execution(* com.springsample..*Service.*(..))")
	public void traceTransaction(){}

}
