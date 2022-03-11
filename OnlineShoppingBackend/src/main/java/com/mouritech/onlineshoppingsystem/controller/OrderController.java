package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;
import com.mouritech.onlineshoppingsystem.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// save an order
	@PostMapping("/orders")
	public Order saveOrder(@Valid @RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	// get all orders
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	// update orders
	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") String orderId,
			@Valid @RequestBody Order orderDetails) throws OrderNotFoundException {

		return orderService.updateOrders(orderId, orderDetails);

	}

	// delete order
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") String orderId)
			throws OrderNotFoundException {
		return orderService.deleteOrder(orderId);

	}

//		//get order by orderId
//		@GetMapping("/orders/{id}")
//	  public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") String orderId)
//	      throws OrderNotFoundException {
//			return orderService.getOrderById(orderId);
//			
//		}
	//get order by customer id
	@GetMapping("/orders/{customerid}")
	public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@PathVariable("customerid") String custId)
			throws CustomerNotFoundException {
		return orderService.getAllOrdersByCustomerId(custId);
	}
	//add order by customer id
	@PostMapping("/orders/{customerid}/customer")
	public ResponseEntity<Order> createOrder(@PathVariable("customerid") String custId, @RequestBody Order newOrder)
			throws CustomerNotFoundException {
		return orderService.createOrder(custId, newOrder);

	}

}
