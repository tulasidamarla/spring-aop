This example demo how annotations can be used to define pointcuts. To define a pointcut for all those methods which are annotated with the stereo type annotation of spring say Service, code would be like this.

	@Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))")
	public void traceTransaction(){}
	
Aspect implementations would be like this:

	@Around("com.springsample.pointcuts.SystemArchitecture.traceTransaction()")
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

