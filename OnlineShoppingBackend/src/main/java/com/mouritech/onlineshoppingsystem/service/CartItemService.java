package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.exception.CartItemNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;

public interface CartItemService {

	CartItem showCartItemById(Long itemId) throws CartItemNotFoundException;

	CartItem insertCartItem(CartItem newCartItem);

	List<CartItem> showAllCartItems();

	ResponseEntity<CartItem> insertCartItembyCartId(Long cartId, CartItem newCartItem) throws CartNotFoundException;

	List<CartItem> findByCart_cartId(Long cartId);

	void removeFromCart(Long id) throws CartItemNotFoundException;
}
