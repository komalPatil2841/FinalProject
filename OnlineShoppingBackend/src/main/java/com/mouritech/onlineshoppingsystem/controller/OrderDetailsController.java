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

import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.OrderDetailsNotFoundException;
import com.mouritech.onlineshoppingsystem.service.OrderDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderdetailsService;

	// get all orders details
	@GetMapping("/orderdetails")
	public List<OrderDetails> getAllOrders() {
		return orderdetailsService.getAllOrderDetails();
	}

	// Post all orders details
	@PostMapping("/orderdetails")
	public OrderDetails saveOrderDetails(@Valid @RequestBody OrderDetails orderdetails) {
		return orderdetailsService.saveOrderDetails(orderdetails);
	}

	// save order details with corresponded orderId
	@PostMapping("orders/{orderId}/orderdetails")
	public OrderDetails insertOrderDetails(@PathVariable(value = "orderId") String orderId,
			@Valid @RequestBody OrderDetails newOrderDetails) throws OrderDetailsNotFoundException {

		return orderdetailsService.insertOrderDetails(orderId, newOrderDetails);

	}

	// get all the order details by corresponding orderId
	@GetMapping("/orders/{orderId}/orderdetails")
	public List<OrderDetails> getAllOrderDetailsByOrderId(@PathVariable(value = "orderId") String orderId) {
		return orderdetailsService.findByOrder_OrderId(orderId);
	}

	// delete order details by corresponding orderId
	@DeleteMapping("/orders/{orderId}/orderdetails")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable(value = "orderId") String orderId)
			throws OrderDetailsNotFoundException {

		return orderdetailsService.deleteOrderDetails(orderId);

	}

	// get all the order details by corresponding customerId
//		@GetMapping("/orders/{customerId}")
//		public List<OrderDetails> getAllOrderDetailsByCustomertId(@PathVariable(value = "customerId") String custId)throws CustomerNotFoundException {
//			return orderdetailsService.findByCustomer_CustId(custId);
//		}

	// update orderDetails by orderDetailsId update status and comment
	@PutMapping("/orders/orderdetails/{orderDetailsId}")
	public ResponseEntity<OrderDetails> updateOrderDetails(
			@PathVariable(value = "orderDetailsId") String orderDetailsId,
			@Valid @RequestBody OrderDetails orderDetails) throws OrderDetailsNotFoundException {

		return orderdetailsService.updateOrderDetails(orderDetailsId, orderDetails);

	}

}
