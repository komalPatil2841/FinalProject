package com.mouritech.onlineshoppingsystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.entity.Order;
import com.mouritech.onlineshoppingsystem.entity.OrderDetails;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.CartNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.CategoryNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderDetailsNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.OrderNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.ProductImageNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.ProductNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;
import com.mouritech.onlineshoppingsystem.service.CartService;
import com.mouritech.onlineshoppingsystem.service.CategoryService;
import com.mouritech.onlineshoppingsystem.service.OrderDetailsService;
import com.mouritech.onlineshoppingsystem.service.OrderService;
import com.mouritech.onlineshoppingsystem.service.ProductImageService;
import com.mouritech.onlineshoppingsystem.service.ProductService;
import com.mouritech.onlineshoppingsystem.service.UserService;

@RestController

@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductImageService productimageService;
	@Autowired
	private OrderDetailsService orderdetailsService;
	//////////////////////////////Category//////////////////////////////////////////
	//add category 
	@PostMapping("add-category")
		public Category insertCategory(@RequestBody Category newCategory) {
		return categoryService.insertCategory(newCategory);
	}
	

	//update category by category id
	@PutMapping("update-category/{cid}")
	public Category updateCatById(@PathVariable("cid") String catId, @RequestBody Category category)
			throws CategoryNotFoundException {
		return categoryService.updateCatById(catId, category);

	}
	//delete category by category id 
	@DeleteMapping("delete-category/{cid}")
	public String deleteCatById(@PathVariable("cid") String catId) throws CategoryNotFoundException {
		categoryService.deleteCatById(catId);
		return "deleted the category";
	}
	////////////////////product////////////////////////////////////
	//add products
	@PostMapping("add-product")
	public Product insertProduct(Product newproduct) throws IOException {

		return productService.insertProduct(newproduct);

	}
	
	// get products by product id
	@GetMapping("get-product/{pid}")
	public Product showProductById(@PathVariable("pid") String prodId) throws ProductNotFoundException {
		return productService.showProductById(prodId);

	}
	// update product by product id
	@PutMapping("update-product/{pid}")
	public Product updateProductById(@PathVariable("pid") String prodId, @RequestBody Product product)
			throws ProductNotFoundException {
		return productService.updateProductById(prodId, product);

	}
	// delete product by product id
	@DeleteMapping("delete-product/{pid}")
	public String deleteProductById(@PathVariable("pid") String prodId) throws ProductNotFoundException {
		productService.deleteProductById(prodId);
		return "deleted the product";

	}

	// add product by cat id 
	@PostMapping("add-product/{catid}/category")
	public ResponseEntity<Product> createProduct(@PathVariable("catid") String catid, @RequestBody Product newProduct)
			throws CategoryNotFoundException {
		return productService.createProduct(catid, newProduct);

	}
	/////////////////////////////////ProductImage/////////////////////


	// add image for corresponding product
	@PostMapping("add-image/{prodId}/prodImage")
	public Optional<ProductImage> addImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {

		return productimageService.addImage(prodId, file);

	}



	// update responding image by productId
	@PutMapping("update-image/{prodId}/prodImages")
	public Optional<Object> updateImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {
		return productimageService.updateImage(prodId, file);

	}

	// delete image for corresponding product
	@DeleteMapping("delete-image/{prodId}/prodImages/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable(value = "prodId") String prodId,
			@PathVariable(value = "imageId") Long imageId) throws ProductNotFoundException {

		return productimageService.deleteImage(prodId, imageId);

	}
////////////////////////user//////
	
	@Autowired
	UserService userService;

	// delete a user by id
	@DeleteMapping("delete-user/{uid}")
	public String deleteUserById(@PathVariable("uid") String userId) throws UserNotFoundException {
		userService.deleteUserById(userId);
		return "deleted the user";

	}

//	//get all users
	@GetMapping("/get-user")
	public List<User> showAllUsers() {
		return userService.showAllUsers();

	}


	
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
	public Cart showCartById(@PathVariable("cid") Long CartId) throws CartNotFoundException {
		return cartService.showCartById(CartId);
	}

	//update cart by cart id
	@PutMapping("cart/{cid}")
	public Cart updateCartById(@PathVariable("cid") Long CartId, @RequestBody Cart Cart)
			throws CartNotFoundException {
		return cartService.updateCartById(CartId, Cart);
	}
	
	//delete cart by cart id
	@DeleteMapping("cart/{cid}")
	public String deleteCartById(@PathVariable("cid") Long CartId) throws CartNotFoundException {
		cartService.deleteCartById(CartId);
		return "deleted the Cart";

	}

/////////////////////order/////////////////////
	


	// get all orders
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}


	// update orders
	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") String orderId,
			@Valid @RequestBody Order orderDetails) throws OrderNotFoundException {

		return orderService.updateOrders(orderId, orderDetails);

	}

	// delete order
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") String orderId)
			throws OrderNotFoundException {
		return orderService.deleteOrder(orderId);

	}
	//////orderDetails////
	// delete order details by corresponding orderId
	@DeleteMapping("/orders/{orderId}/orderdetails")
	public ResponseEntity<?> deleteOrderDetails(@PathVariable(value = "orderId") Long orderId)
			throws OrderDetailsNotFoundException {

		return orderdetailsService.deleteOrderDetails(orderId);

	}
	// update orderDetails by orderDetailsId update status and comment
	@PutMapping("/orders/orderdetails/{orderDetailsId}")
	public ResponseEntity<OrderDetails> updateOrderDetails(
			@PathVariable(value = "orderDetailsId") Long orderDetailsId,
			@Valid @RequestBody OrderDetails orderDetails) throws OrderDetailsNotFoundException {

		return orderdetailsService.updateOrderDetails(orderDetailsId, orderDetails);

	}


	
}
