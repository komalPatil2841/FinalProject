package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CustomerRepository;
import com.mouritech.onlineshoppingsystem.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
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
		return "ORD" + cId; 
	
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
	public ResponseEntity<Order> createOrder(String custId,Order newOrder) throws CustomerNotFoundException {
	
		Order order = customerRepository.findByCustId(custId).map(
				customer ->{
					newOrder.setCustomer(customer);
					newOrder.setOrderId(generateOrderId());
					return orderRepository.save(newOrder);
				}).orElseThrow(()-> new CustomerNotFoundException("Customer not found with id = "  + custId));
		return new ResponseEntity<Order>(newOrder,HttpStatus.CREATED);
	}
	@Override
	public ResponseEntity<List<Order>> getAllOrdersByCustomerId(String custId) throws CustomerNotFoundException {
		if(!customerRepository.existsCustomerByCustId(custId)) {
			throw new CustomerNotFoundException("Customer not found with id = "  + custId);
		}
		List<Order> orders = orderRepository.findByCustomer_CustId(custId);
		return new ResponseEntity<List<Order>>(orders,HttpStatus.OK);
	}

}


	
