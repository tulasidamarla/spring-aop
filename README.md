# spring-aop
AOP is a very important foundations of spring framework. It is used to implement typical enterprise features like transactions, logging, security etc. AOP gives flexibility to choose where these features needs to be implemented in the application. This will simplify the business code.

Using AOP we can remove transaction code from our application and can create a transaction aspect seperately. similary we can remove logging code, exception handling code etc.

Without AOP application code is cluttered with lot of stuff code related to transactions, logging, exception handling etc, so it is often hard to see what is actually happening inside a method. Also, there will be lot of redundant code in many classes.

Cross cutting concerns
----------------------
Transactions , Exception handling, logging are called cross cutting concerns because a lot of classes and methods must implement them, means they actually cut through the code. It means Aspect is nothing but an implementation of a cross cutting concern. So, an Aspect contains two things. Where it should be applied(pointcut) and what should be implemented(advice).

If you are developing an application, first implement the business logic, then write aspects for cross cutting concerns or you can use some of the aspects that spring is already providing.

you can use Spirng AOP or aspectj to weave aspects in to the application.

Here is an example for log tracing aspect.

@Component

@Aspect

public class TracingAspect{
	Logger logger = Logger.getLogger(TracingAspect.class);
	
	@Before("execution(void dosomething())")
	public void startLog(){
		logger.trace("entering the method");
	}

}

We need to annotate the class using @Aspect and also @Component be recognized as spring bean.

Note: Aspect annotation is available with aspectj library, not from sping. we need aspectjrt and aspectjweaver dependencies for this to work.

@Before annotation is spring annotation to apply before a method execution. pointcut expression inside is, void dosomething() . The original syntax is:

return_type method_name(arguments)

For ex, if we want to implement this for all methods, the pointcut expression is --> * *(..) 
  
 The wildcard to accept all for both return type and method name is *, but for arguments it is '..' .

There is a problem with the above aspect. The method startLog() prints the log message but it did not say which method it actually entered. We can use joinpoint for this.

Joinpoints are a point in the control flow of a program. For ex, executing a method is a joinpoint. Advices are presented with the information about the join point like method name, class name etc. We can modify the method startLog() in the above example to use joinpoint like this.

	@Before("execution(void dosomething())")
	public void startLog(JointPoint joinPoint){
		logger.trace("entering the method" + joinPoint.getStaticPart().getSignature().toString());
	}


