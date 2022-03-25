package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.ProductNameAlreadyExistsException;
import com.mouritech.onlineshoppingsystem.exception.ProductNotFoundException;

public interface ProductService {

	Product insertProduct(Product newProduct);

	Product showProductById(String prodId) throws ProductNotFoundException;

	List<Product> showAllProducts();

	Product updateProductById(String prodId, Product product) throws ProductNotFoundException;

	void deleteProductById(String prodId) throws ProductNotFoundException;

	ResponseEntity<List<Product>> getAllProductsByCategoryId(String catid);

	ResponseEntity<Product> createProduct(String catid, Product newProduct);

	Product getCategory_CatIdByProdName(String catid, String productname) throws ProductNameAlreadyExistsException;

}
