package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Order;
import com.example.repository.OrderRepository;

@Service
public class OrderServiceImpl  implements OrderService{
	
	  @Autowired
	    private OrderRepository repository;

	    @Override
	    public Order placeOrder(Order order) {
	        order.setStatus("Pending");
	        return repository.save(order);
	    }

	    @Override
	    public Order updateOrderStatus(Long orderId, String status) {
	        Order order = repository.findById(orderId).orElseThrow();
	        order.setStatus(status);
	        return repository.save(order);
	    }

	    @Override
	    public List<Order> getOrdersByCustomer(Long customerId) {
	        return repository.findByCustomerId(customerId);
	    }

	    @Override
	    public List<Order> getAllOrders() {
	        return repository.findAll();
	    }
}
