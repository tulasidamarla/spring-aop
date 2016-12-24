
package com.springsample.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springsample.model.Customer;

@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
	
	/* (non-Javadoc)
	 * @see com.springsample.repository.CustomerRepository#findAll()
	 */
	@Override
	public List<Customer> findAll(){
		List<Customer> customers = new ArrayList<>();
		Customer c= new Customer();
		c.setFirstName("Tulasi");
		c.setLastName("Ram");
		customers.add(c);
		return customers;
	}

}
