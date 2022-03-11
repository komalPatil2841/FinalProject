package com.mouritech.onlineshoppingsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mouritech.onlineshoppingsystem.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	Optional<CartItem> findByItemId(String itemId);

	List<CartItem> findByCart_CartId(String cartId);
}
