package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.service.CartService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	CartRepository cartRepo;
	
	//add cart
	@PostMapping("cart")
	public Cart insertCart(@RequestBody Cart newCart) {
		return cartService.insertCart(newCart);

	}
	//get all carts
	@GetMapping("carts")
	public List<Cart> showAllCarts() {
		return cartService.showAllCarts();
	}
	
	//get cart by cart id 
	@GetMapping("cart/{cid}")
	public Cart showCartById(@PathVariable("cid") String CartId) throws CartNotFoundException {
		return cartService.showCartById(CartId);
	}

	//update cart by cart id
	@PutMapping("cart/{cid}")
	public Cart updateCartById(@PathVariable("cid") String CartId, @RequestBody Cart Cart)
			throws CartNotFoundException {
		return cartService.updateCartById(CartId, Cart);
	}
	
	//delete cart by cart id
	@DeleteMapping("cart/{cid}")
	public String deleteCartById(@PathVariable("cid") String CartId) throws CartNotFoundException {
		cartService.deleteCartById(CartId);
		return "deleted the Cart";

	}
	//add cart data by customer id 
	@PostMapping("cart/{custId}/cust")
	public Cart iinsertCartwithCusttomerId(@PathVariable(value = "custId") String custId,
			@Valid @RequestBody Cart newCart) throws CartNotFoundException {
		return cartService.insertCartwithCusttomerId(custId, newCart);

	}
	//get cart data by customer id 
	@GetMapping("/cart/{custId}/cust")
	public Cart getCartByCustId(@PathVariable("custId") String custId) throws CustomerNotFoundException {
		return cartService.getCartBycustId(custId);
	}

}
