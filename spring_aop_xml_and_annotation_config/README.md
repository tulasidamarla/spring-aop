For XML configuration of AOP, the following dependencies of aspectj should be added.

		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>1.7.3</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.7.3</version>
		</dependency>
		
In application context xml file, the following changes are required.

	<context:annotation-config/>
	<context:component-scan base-package="com.springsample"/>
	<aop:aspectj-autoproxy/>

Note: aop is in different namespace, not from context. XML header will be like this.

	<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">
		
Note: For adding logging please add the maven dependency of log4j and log4j.properties. Refer the code.		 		