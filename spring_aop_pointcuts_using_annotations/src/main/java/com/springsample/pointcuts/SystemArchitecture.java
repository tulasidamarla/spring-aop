package com.springsample.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {
	
	@Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
	public void traceTransaction(){}

}
