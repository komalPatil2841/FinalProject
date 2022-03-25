package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;


public interface OrderService {

	Order saveOrder(Order order);

	List<Order> getAllOrders();

	ResponseEntity<Order> updateOrders(String orderId, @Valid Order orderDetails) throws OrderNotFoundException;

	ResponseEntity<?> deleteOrder(String orderId) throws OrderNotFoundException ;

	ResponseEntity<Order> getOrderById(String orderId) throws OrderNotFoundException;
	//
	ResponseEntity<List<Order>> getAllOrdersByUserId(String userId) throws UserNotFoundException;



	ResponseEntity<Order> createOrder(String userId, Order newOrder) throws UserNotFoundException;

}
