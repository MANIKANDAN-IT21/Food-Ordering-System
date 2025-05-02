package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MenuItemDTO;
import com.example.feign.MenuServiceClient;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;

@Service
public class CustomerServiceImpl {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private MenuServiceClient menuServiceClient;

	public List<MenuItemDTO> fetchMenu(Long restaurantId) {
		return menuServiceClient.getMenuByRestaurant(restaurantId);
	}

	public Customer register(Customer customer) {
		return repository.save(customer);
	}

	public Optional<Customer> login(String email, String password) {
		return repository.findByEmail(email).filter(c -> c.getPassword().equals(password));
	}

	public Customer getProfile(Long customerId) {
	    return repository.findById(customerId).get();
	}


	public Customer updateProfile(Long id, Customer updated) {
		Customer c = repository.findById(id).orElseThrow();
		c.setName(updated.getName());
		c.setAddress(updated.getAddress());
		c.setPhone(updated.getPhone());
		return repository.save(c);
	}

}
