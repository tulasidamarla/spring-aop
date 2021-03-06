package com.springsample.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {
	
	@Pointcut("execution(* com.springsample..*ServiceImpl.getCustomers(..))")
	public void traceTransaction(){}

}
