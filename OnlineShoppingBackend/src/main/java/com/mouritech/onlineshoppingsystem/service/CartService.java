package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;


public interface CartService {

	Cart insertCart(Cart newCart);

	Cart showCartById(Long cartId) throws CartNotFoundException;

	List<Cart> showAllCarts();

	Cart updateCartById(Long cartId, Cart cart) throws CartNotFoundException;

	//Cart insertCartwithUserId(Long userId, Cart newCart);

	//Cart getCartByUserId(Long userId) throws UserNotFoundException;

	void deleteCartById(Long cartId);

	

	

}
