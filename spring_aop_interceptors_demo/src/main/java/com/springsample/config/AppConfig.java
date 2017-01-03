package com.springsample.config;



import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages={"com.springsample"})
public class AppConfig {
	
	@Bean
	public CustomizableTraceInterceptor customizableTraceInterceptor() {
	    CustomizableTraceInterceptor cti = new CustomizableTraceInterceptor();
	    cti.setEnterMessage("Entering method '" + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME + "("+ CustomizableTraceInterceptor.PLACEHOLDER_ARGUMENTS+")' of class [" + CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME + "]");
	    cti.setExitMessage("Exiting method '" + CustomizableTraceInterceptor.PLACEHOLDER_METHOD_NAME + "' of class [" + CustomizableTraceInterceptor.PLACEHOLDER_TARGET_CLASS_NAME + "] took " + CustomizableTraceInterceptor.PLACEHOLDER_INVOCATION_TIME+"ms.");
	    return cti;
	}
	
	@Bean
	public Advisor traceAdvisor() {
	    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	    pointcut.setExpression("execution(* getCustomers(..))");
	    return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
	}
		
}
