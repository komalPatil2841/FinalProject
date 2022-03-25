package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.exception.CartItemNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CartItemRepository;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
@Service
public class CartItemServiceImpl implements CartItemService {
	
		@Autowired
		private CartItemRepository cartItemRepository;
		
		@Autowired
		private CartRepository cartRepository;

		@Override
		public CartItem insertCartItem(CartItem newCartItem) {
			
			return cartItemRepository.save(newCartItem);
		}

		

		@Override
		public List<CartItem> showAllCartItems() {
			// TODO Auto-generated method stub
			return cartItemRepository.findAll();
		}

		@Override
		public CartItem showCartItemById(Long itemId) throws CartItemNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseEntity<CartItem> insertCartItembyCartId(Long cartId, CartItem newCartItem)throws CartNotFoundException {
		   CartItem cartItem = cartRepository.findByCartId(cartId).map(cart -> {
			
				
				newCartItem.setCart(cart);
				return cartItemRepository.save(newCartItem);
			}).orElseThrow(() -> new CartNotFoundException("Cart not found with" + cartId));
		return new ResponseEntity<CartItem>(newCartItem, HttpStatus.CREATED);
			}

		@Override
		public void removeFromCart(Long id) throws CartItemNotFoundException {
			if (cartItemRepository.existsById(id)){
				cartItemRepository.deleteById(id);
			} else {
				throw new  CartItemNotFoundException("Could not remove. Item not found with id: " + id + " in cart.");
			}
		}
		
		
		@Override
		public List<CartItem> findByCart_cartId(Long cartId) {
			return cartItemRepository.findByCart_CartId(cartId);
		}
		
}
