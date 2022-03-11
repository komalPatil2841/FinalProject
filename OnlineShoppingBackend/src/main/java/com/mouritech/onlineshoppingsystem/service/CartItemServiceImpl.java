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


		
		public String generateCartItemitemId() {
			Random rand = new Random(); //instance of random class
		      int upperbound = 255;
		        //generate random values from 0-254
		      Long cId = (long) rand.nextInt(upperbound);
			return "CARTITEM" + cId; 
		
		}
		
		@Override
		public CartItem insertCartItem(CartItem newCartItem) {
			newCartItem.setItemId(generateCartItemitemId());
			return cartItemRepository.save(newCartItem);
		}

		

		@Override
		public List<CartItem> showAllCartItems() {
			// TODO Auto-generated method stub
			return cartItemRepository.findAll();
		}

		@Override
		public CartItem showCartItemById(String itemId) throws CartItemNotFoundException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseEntity<CartItem> insertCartItembyCartId(String cartId, CartItem newCartItem)throws CartNotFoundException {
		   CartItem cartItem = cartRepository.findByCartId(cartId).map(cart -> {
			
				newCartItem.setItemId(generateCartItemitemId());
				newCartItem.setCart(cart);
				return cartItemRepository.save(newCartItem);
			}).orElseThrow(() -> new CartNotFoundException("Cart not found with" + cartId));
		return new ResponseEntity<CartItem>(newCartItem, HttpStatus.CREATED);
			}
		
		
		@Override
		public List<CartItem> findByCart_cartId(String cartId) {
			return cartItemRepository.findByCart_CartId(cartId);
		}
		
}
