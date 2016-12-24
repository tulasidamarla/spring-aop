package com.springsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springsample.repository.CustomerRepository;
import com.springsample.repository.CustomerRepositoryImpl;
import com.springsample.service.CustomerService;
import com.springsample.service.CustomerServiceImpl;

@Configuration
public class AppConfig {
	
		@Bean(name = "customerService")
		public CustomerService getCustomerService() {
			CustomerServiceImpl service = new CustomerServiceImpl();
			// Incase of setter injection
			 service.setCustomerRepository(getCustomerRepository());
			// Incase of constructor injection
			// Customerservice service = new
			// CustomerServiceImpl(getCustomerRepository());
			return service;
		}

		@Bean(name = "customerRepository")
		public CustomerRepository getCustomerRepository() {
			return new CustomerRepositoryImpl();
		}
	
}
