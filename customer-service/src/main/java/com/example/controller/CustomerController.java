package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MenuItemDTO;
import com.example.feign.MenuServiceClient;
import com.example.model.Customer;
import com.example.service.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl service;

	@Autowired
	private MenuServiceClient menuServiceClient;

	@GetMapping("menu")
	public List<MenuItemDTO> viewMenu() {
		return menuServiceClient.getAllMenu();
	}
	
//	@GetMapping("menu/restaurant/{restaurantId}")
//	public List<MenuItemDTO> viewMenu(@PathVariable Long restaurantId) {
//		return menuServiceClient.getMenuByRestaurant(restaurantId);
//	}
	@GetMapping("menu/restaurant/{restaurantId}")
	public List<MenuItemDTO> getMenuByRestaurant(@PathVariable Long restaurantId) {
		return menuServiceClient.getMenuByRestaurant(restaurantId);
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.register(customer));
	}

	@PostMapping("/login")
	public ResponseEntity<Customer> login(@RequestBody Customer loginRequest) {
		return service.login(loginRequest.getEmail(), loginRequest.getPassword()).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(401).build());
	}

	@GetMapping("/profile/{cid}")
	public Customer getProfile(@PathVariable("cid") Long customerId) {   
	    return service.getProfile(customerId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateProfile(@PathVariable Long id, @RequestBody Customer customer) {
		return ResponseEntity.ok(service.updateProfile(id, customer));
	}
}
