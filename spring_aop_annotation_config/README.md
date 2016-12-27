For AOP configuration in java, app config java class needs to be annotated with @EnableAspectJAutoProxy.

Note: All other configuration of log4j, aspecjrt, aspecjweaver dependencies have to be added in pom.xml file. 

Spring supports the following type of advices out of the box.

@Before 
-------
Executed before the target method is executed. An exception can prevent the target method being called and it is propagated to the caller.

@After
------
Executed after the target method is executed. Sucessful execution of the method or an exception in the method can't stop After adivce execution.

@AfterThrowing
--------------
Executed if the target method throws an exception. Exception is propagated to the caller. Here is the sample code.

	@AfterThrowing(pointcut="execution(* getCustomers())",throwing="ex")
	public void logException(RuntimeException ex){
		logger.info("exception occured , reason : " + ex);
	}

Note: The above code is executed in a typesafe manner. i.e If any RuntimeException is thrown or any super class of RuntimeException is thrown then it is adviced. For ex, if an IOException is thrown from target method then this will not be executed.

@AfterReturning
---------------
Executed if the target method returns successfully. We can't change the result or can't throw any additional exceptions. This advice also can be bound in typesafe manner like this.

	@AfterReturning(pointcut="execution(* getCustomers())", returning="list")
	public void logResult(List<Customer> list){
		logger.info("Method returned successfully with " + list.size() + " customers" );
	}

@Around
-------

Around advice wraps around the method. i.e it is called before and after the target method execution. 

This is the only advice can prevent the target method being called. Before advice can prevent but an exception is thrown and it will be propagated back to the caller.

This is the only advice that can catch exception and can throw a different exception. After advice can read the exception but can't modify.

This is the only advice that can modify the return type. AfterReturning advice can read the return type but can't modify.

To achieve all the above, AroundAdvice is given the current method call using ProceedingJoinPoint.
This is an extension of JoinPoint. It allows the advice to proceed with the target method using proceed() method. If advice method doesn't call proceed, then target method will not be invoked.

Here is the sample method:

	@Around("execution(* getCustomers())")
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

Note: Unlike other advices, the Around advice should have a return type.

If all the advices are used against one target method, then the order of execution as follows.

@Around
@Before
Method execution
@Around
@After
@AfterThrowing
@AfterReturning
