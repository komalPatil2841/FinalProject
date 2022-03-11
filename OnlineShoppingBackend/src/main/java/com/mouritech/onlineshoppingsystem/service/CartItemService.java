package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.exception.CartItemNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;

public interface CartItemService {

	CartItem showCartItemById(String itemId) throws CartItemNotFoundException;

	CartItem insertCartItem(CartItem newCartItem);

	List<CartItem> showAllCartItems();

	ResponseEntity<CartItem> insertCartItembyCartId(String cartId, CartItem newCartItem) throws CartNotFoundException;

	List<CartItem> findByCart_cartId(String cartId);
}
