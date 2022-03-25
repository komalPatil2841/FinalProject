package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.OrderDetailsNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.OrderDetailsRepository;
import com.mouritech.onlineshoppingsystem.repository.OrderRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderdetailsRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		
		return orderdetailsRepository.findAll();
	}

	@Override
	public OrderDetails saveOrderDetails(OrderDetails OrderDetails) {

		return orderdetailsRepository.save(OrderDetails);
	}



	@Override
	public OrderDetails insertOrderDetails(String orderId, @Valid OrderDetails newOrderDetails)
			throws OrderDetailsNotFoundException {
		return orderRepository.findByOrderId(orderId).map(order -> {
			newOrderDetails.setOrder(order);
			//newOrderDetails.setOrderDetailsId(generateOrderDetailsId());
			return orderdetailsRepository.save(newOrderDetails);
		}).orElseThrow(() -> new OrderDetailsNotFoundException("order not found"));
	}

	@Override
	public List<OrderDetails> findByOrder_OrderId(String orderId) {

		return orderdetailsRepository.findByOrder_OrderId(orderId);
	}

	@Override
	public ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws OrderDetailsNotFoundException {
		return orderdetailsRepository.findByOrderDetailsId(orderDetailsId).map(orderDetails -> {
			orderdetailsRepository.delete(orderDetails);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new OrderNotFoundException("orderdetails not found" + orderDetailsId));
	}

	@Override
	public ResponseEntity<OrderDetails> updateOrderDetails(Long orderDetailsId, @Valid OrderDetails orderDetails)
			throws OrderDetailsNotFoundException {
		OrderDetails newOrderDetails = orderdetailsRepository.findByOrderDetailsId(orderDetailsId).orElseThrow(
				() -> new OrderDetailsNotFoundException("orderdetails not found for this id :: " + orderDetailsId));

		newOrderDetails.setComments(orderDetails.getComments());

		final OrderDetails updatedOrerDetails = orderdetailsRepository.save(newOrderDetails);
		return ResponseEntity.ok(updatedOrerDetails);
	}

}
