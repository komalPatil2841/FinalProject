package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Cart;

import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.repository.CustomerRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public String generateCartId() {
		Random rand = new Random();
		int upperbound = 255;
		Long cId = (long) rand.nextInt(upperbound);
		return "CART00" + cId;

	}

	@Override
	public Cart insertCart(Cart newCart) {
		newCart.setCartId(generateCartId());
		return cartRepository.save(newCart);
	}

	@Override
	public Cart showCartById(String cartId) throws CartNotFoundException {
		return cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart not found with id " + cartId));
	}

	@Override
	public Cart getCartBycustId(String custId) throws CustomerNotFoundException {
		return cartRepository.findByCustomer_CustId(custId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + custId));
	}

	@Override
	public List<Cart> showAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public Cart updateCartById(String cartId, Cart cart) throws CartNotFoundException {
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new CartNotFoundException("cart not found with id " + cartId));
		cartRepository.save(existingCart);
		return existingCart;
	}

	@Override
	public void deleteCartById(String cartId) throws CartNotFoundException {
		Cart existingCart = cartRepository.findByCartId(cartId)
				.orElseThrow(() -> new CartNotFoundException("cart not found with id " + cartId));
		cartRepository.delete(existingCart);
	}

	@Override
	public Cart insertCartwithCusttomerId(String custId, @Valid Cart newCart) throws CartNotFoundException {
		return customerRepository.findByCustId(custId).map(cust -> {
			newCart.setCustomer(cust);
			newCart.setCartId(generateCartId());
			return cartRepository.save(newCart);
		}).orElseThrow(() -> new CartNotFoundException("Cart not found"));
	}

}
