package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.exception.OrderDetailsNotFoundException;

public interface OrderDetailsService {

	OrderDetails insertOrderDetails(String orderId, @Valid OrderDetails newOrderDetails)
			throws OrderDetailsNotFoundException;

	List<OrderDetails> findByOrder_OrderId(String orderId);

	ResponseEntity<?> deleteOrderDetails(Long orderDetailsId) throws OrderDetailsNotFoundException;

	ResponseEntity<OrderDetails> updateOrderDetails(Long orderDetailsId, @Valid OrderDetails orderDetails)
			throws OrderDetailsNotFoundException;

	List<OrderDetails> getAllOrderDetails();

	OrderDetails saveOrderDetails(OrderDetails OrderDetails);


}
