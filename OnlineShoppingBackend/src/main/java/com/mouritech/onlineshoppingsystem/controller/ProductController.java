package com.mouritech.onlineshoppingsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mouritech.onlineshoppingsystem.exception.CategoryNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.ProductNameAlreadyExistsException;
import com.mouritech.onlineshoppingsystem.exception.ProductNotFoundException;
import com.mouritech.onlineshoppingsystem.entity.Product;

import com.mouritech.onlineshoppingsystem.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class ProductController {

	@Autowired
	ProductService productService;
	//add products
	@PostMapping("product")
	public Product insertProduct(Product newproduct) throws IOException {

		return productService.insertProduct(newproduct);

	}
	// get all  products 
	@GetMapping("product")
	public List<Product> showAllProducts() {
		return productService.showAllProducts();

	}
	// get products by product id
	@GetMapping("product/{pid}")
	public Product showProductById(@PathVariable("pid") String prodId) throws ProductNotFoundException {
		return productService.showProductById(prodId);

	}
	// update product by product id
	@PutMapping("product/{pid}")
	public Product updateProductById(@PathVariable("pid") String prodId, @RequestBody Product product)
			throws ProductNotFoundException {
		return productService.updateProductById(prodId, product);

	}
	// delete product by product id
	@DeleteMapping("product/{pid}")
	public String deleteProductById(@PathVariable("pid") String prodId) throws ProductNotFoundException {
		productService.deleteProductById(prodId);
		return "deleted the product";

	}
	// get product by cat id 
	@GetMapping("/products/{catid}")
	@Transactional(timeout = 500)
	public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable("catid") String catid)
			throws CategoryNotFoundException {
		return productService.getAllProductsByCategoryId(catid);
	}
	// add product by cat id 
	@PostMapping("/products/{catid}/category")
	public ResponseEntity<Product> createProduct(@PathVariable("catid") String catid, @RequestBody Product newProduct)
			throws CategoryNotFoundException {
		return productService.createProduct(catid, newProduct);

	}

	// get product by cat- id and product name
	@GetMapping("/products/{catid}/{productname}")
	public Product getCategory_CatIdByProdName(@PathVariable("catid") String catId,
			@PathVariable("productname") String prodName)
			throws CategoryNotFoundException, ProductNameAlreadyExistsException {
		return productService.getCategory_CatIdByProdName(catId, prodName);
	}

}
