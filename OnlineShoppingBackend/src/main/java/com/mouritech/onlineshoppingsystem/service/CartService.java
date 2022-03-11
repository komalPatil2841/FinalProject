package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;


public interface CartService {

	Cart insertCart(Cart newCart);

	Cart showCartById(String cartId) throws CartNotFoundException;

	List<Cart> showAllCarts();

	Cart updateCartById(String cartId, Cart cart) throws CartNotFoundException;

	Cart insertCartwithCusttomerId(String custId, Cart newCart);

	Cart getCartBycustId(String custId) throws CustomerNotFoundException;

	void deleteCartById(String cartId);

}
