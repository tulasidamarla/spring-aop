package com.springsample.repository;

import java.util.List;

import com.springsample.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

}