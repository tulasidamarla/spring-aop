package com.springsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsample.model.Customer;
import com.springsample.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getCustomers(){
		//Uncomment this code to test for @After and @AfterThrowing demo 
		/*if(true){
			throw new RuntimeException("test exception");
		}*/
		
		return customerRepository.findAll();
	}

	public void setCustomerRepository(CustomerRepository repository) {
		this.customerRepository = repository;
	}

}
