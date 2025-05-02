package com.example.service;

import java.util.Optional;

import com.example.model.Customer;

public interface CustomerService {

	Customer register(Customer customer);

	Optional<Customer> login(String email, String password);

	Customer getProfile(Long customerId);

	Customer updateProfile(Long id, Customer updated);
	
//	Customer getCustomerById(Long id);
}
