package com.example.service;

import java.util.List;

import com.example.model.Order;

public interface OrderService {

	Order placeOrder(Order order);

	Order updateOrderStatus(Long orderId, String status);

	List<Order> getOrdersByCustomer(Long customerId);

	List<Order> getAllOrders();
}
