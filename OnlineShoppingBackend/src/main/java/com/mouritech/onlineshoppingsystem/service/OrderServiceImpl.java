package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Order;

import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.OrderRepository;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Order saveOrder(Order order) {
		
		order.setOrderId(generateOrderId());
		return orderRepository.save(order);
	}


	public String generateOrderId() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 255;
	        //generate random values from 0-254
	      Long cId = (long) rand.nextInt(upperbound);
		return "O" + cId; 
	
	}
	
	@Override
	public List<Order> getAllOrders() {
		return  orderRepository.findAll();
	}


	@Override
	public  ResponseEntity<Order> updateOrders(String orderId, @Valid Order orderDetails) throws OrderNotFoundException {
		  Order order = orderRepository.findByOrderId(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order not found for this id :: " + orderId));

		
		        order.setOrderedOn(orderDetails.getOrderedOn());
		        order.setOrderStatus(orderDetails.getOrderStatus());
			        final Order updatedOrder = orderRepository.save(order);
			        return ResponseEntity.ok(updatedOrder);

	}


	@Override
	public ResponseEntity<?> deleteOrder(String orderId) throws OrderNotFoundException {
		
		return orderRepository.findByOrderId(orderId).map(order -> {
			orderRepository.delete(order);
		return ResponseEntity.ok().build();
		}).orElseThrow(()->new OrderNotFoundException("order not found"));
	}


	@Override
	public ResponseEntity<Order> getOrderById(String orderId) throws OrderNotFoundException {
		 Order order = orderRepository.findByOrderId(orderId)
		        .orElseThrow(() -> new OrderNotFoundException("order not found for this id :: " + orderId));
		      return ResponseEntity.ok().body(order);
	}
	
	@Override
	public ResponseEntity<Order> createOrder(String userId,Order newOrder) throws UserNotFoundException {
	
		Order order = userRepository.findByUserId(userId).map(
				user ->{
					newOrder.setUser(user);
					newOrder.setOrderId(generateOrderId());
					return orderRepository.save(newOrder);
				}).orElseThrow(()-> new UserNotFoundException("User not found with id = "  + userId));
		return new ResponseEntity<Order>(newOrder,HttpStatus.CREATED);
	}
	@Override
	public ResponseEntity<List<Order>> getAllOrdersByUserId(String userId) throws UserNotFoundException {
		if(!userRepository.existsUserByUserId(userId)) {
			throw new UserNotFoundException("User not found with id = "  + userId);
		}
		List<Order> orders = orderRepository.findByUser_UserId(userId);
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}

}


	
