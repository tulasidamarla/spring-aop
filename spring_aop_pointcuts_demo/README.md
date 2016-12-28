Pointcut defines where an aspect can be added in the code. 

The signature of the Pointcut is as follows.

pointcut_type(Declaration). For ex,

execution(void getCustomer()) 

execution is the type of pointcut. i.e. Method execution. <br/>
Declaration signature contains 3 parts. 

1)return type
2)method name
3)parameters

If we want to apply this to all methods inside our code, we should use wildcards like this.

execution(* *(..)) // .. represents var args

Here are few examples with wild cards.

execution(* getCustomers()) // any return type, method name getCustomers and should have no arguments

execution(* getCustomers(int int)) // any return type, method name getCustomers and should have two arguments of type int

execution(* getCustomers(*)) // any return type, method name getCustomers and one argument of any type

execution(* com.springsample.service.CustomerService.getCustomers(..)) // any return type, fully qualified method name as com.springsample.service.CustomerService.getCustomers and any no of arguments

execution(* com.springsample..*Service.getCustomers(..)) // any return type, any subpackage of com.springsample(.. represents subpackage) and any class or interface ends with Service(*Service) with method name as getCustomers and any no of arguments

execution(* *.*(..)) // any return type, any class and any method (package is default if you don't mention) and any no of arguments

execution(* *..*.*(..)) // any return type, any package, any subpackage, any class, any method and any arguments

Pointcut Expressions using Annotations
--------------------------------------
If a method is annotated with a custom annotation, then we can write a point cut expression to match methods with that annotation and advice them.
For ex, Let us create a custom annotation like this:

	package com.springsample.repository;
	
	import java.lang.annotation.ElementType;
	import java.lang.annotation.Retention;
	import java.lang.annotation.RetentionPolicy;
	import java.lang.annotation.Target;
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface FindAll {
	
	}

If a method is annotated like this,

	@FindAll
	public List<Customer> findAll(){
	....
	}

Then pointcut expression would be,
	execution(@com.springsample.repository.FindAll * *(..))

Pointcut Expressions using Spring Bean Names
--------------------------------------------

The default bean name of any class is camel case. For ex, If class name is CustomerServiceImpl then bean name is customerServiceImpl. We can customize bean names. For ex, 
If we use java configuration the name of the method which is annotated with @Bean is the bean name.
If we use any stereo type annotations(Component,Service and Repository) with the class, then we can pass bean name with string parameter within the annoation.   

The advice method can be implemented like this,

	@Before("bean(customerService)")
	public void logModel(JoinPoint jp){
		logger.info("Service method call " + jp.getStaticPart().getSignature().toString());
	}
 
Pointcuts and Boolean Operators
-------------------------------

Pointcuts can be combined using boolean operators. For ex,

execution(* com.springsample..*Service(..) || execution(* com.springsample..*Repository(..))

Reusing Pointcuts
-----------------
If same pointcut needs to be repeated multiple times, then we can write an empty method in a class with the pointcut like this.

	package com.springsample.pointcuts;
	import org.aspectj.lang.annotation.Pointcut;
	public class CustomerPointCuts {
		@Pointcut("execution(* com.springsample..*Service.*(..))")
		public void traceTransaction(){}
	}

We can use the above defined point any where required like this.

@Around("com.springsample.pointcuts.CustomerPointCuts.traceTransaction()")




