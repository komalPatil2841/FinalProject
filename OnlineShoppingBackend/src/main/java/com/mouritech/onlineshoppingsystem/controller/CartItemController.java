package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mouritech.onlineshoppingsystem.entity.CartItem;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CartItemRepository;
import com.mouritech.onlineshoppingsystem.service.CartItemService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CartItemController {
	@Autowired
	CartItemService cartitemService;

	@Autowired
	CartItemRepository cartitemRepo;
	
	//add cart items 
	@PostMapping("CartItem")
	public CartItem insertCartItem(@RequestBody CartItem newCartItem) {
		return cartitemService.insertCartItem(newCartItem);
	}
	
	// get Cart Item 
	@GetMapping("CartItem")
	public List<CartItem> showAllCarts() {
		return cartitemService.showAllCartItems();
	}
	
	//add cart item data by cart id 
	@PostMapping("CartItem/{cartId}/items")
	public ResponseEntity<CartItem> insertCartItembyCartId(@PathVariable(value = "cartId") String cartId,
			@RequestBody CartItem newCartItem) throws CartNotFoundException {
		return cartitemService.insertCartItembyCartId(cartId, newCartItem);

	}
	//get cart items by cart id  
	@GetMapping("/CartItem/{cartId}/items")
	public List<CartItem> getAllCartItemByOrderId(@PathVariable(value = "cartId") String cartId) {
		return cartitemService.findByCart_cartId(cartId);
	}
}
