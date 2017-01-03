Spring has provided lot of interceptors which are useful in regular enterprise application. 
org.springframework.aop package contains list of interceptors which are useful for logging, performance, exception handling etc.

we don't need to write aspects if we want to use simple tracing, exception handling etc. Here is an example for tracing.

The following changes are required in Configuration file.

	@Bean
	public CustomizableTraceInterceptor customizableTraceInterceptor() {
	    CustomizableTraceInterceptor cti = new CustomizableTraceInterceptor();
	    cti.setEnterMessage("Entering method '" + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME + "("+ 									CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS+")' of class [" + 									CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME + "]");
	    cti.setExitMessage("Exiting method '" + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME + "' of class [" +
	    							CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME + "] took " +
	    							CustomizableTraceInterceptor.PLACEHOLDER_INVOCATION_TIME+"ms.");
	    return cti;
	}
	
	@Bean
	public Advisor traceAdvisor() {
	    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	    pointcut.setExpression("execution(* getCustomers(..))");
	    return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
	} 

In application.properties file add the below line.

	logging.level.org.springframework.aop.interceptor.CustomizableTraceInterceptor=TRACE