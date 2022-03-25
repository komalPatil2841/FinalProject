package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.entity.Product;
import com.mouritech.onlineshoppingsystem.exception.CategoryNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.ProductNameAlreadyExistsException;
import com.mouritech.onlineshoppingsystem.exception.ProductNotFoundException;
import com.mouritech.onlineshoppingsystem.repository.CategoryRepository;
import com.mouritech.onlineshoppingsystem.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product insertProduct(Product newProduct) {

		newProduct.setProdId(generateProductId());
		return productRepository.save(newProduct);
	}

	public String generateProductId() {
		Random rand = new Random(); // instance of random class
		int upperbound = 255;
		// generate random values from 0-254
		Long pId = (long) rand.nextInt(upperbound);
		return "P00" + pId;

	}

	@Override
	public Product showProductById(String prodId) throws ProductNotFoundException {
	
		return productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ProductNotFoundException("product not found with id " + prodId));
	}

	@Override
	public List<Product> showAllProducts() {
	
		return productRepository.findAll();

	}

	@Override
	public Product updateProductById(String prodId, Product product) throws ProductNotFoundException {
		Product existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ProductNotFoundException("product not found with id " + prodId));
		existingProduct.setProdName(product.getProdName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setAvailableQuantity(product.getAvailableQuantity());
		productRepository.save(existingProduct);
		return existingProduct;
	}

	@Override
	public void deleteProductById(String prodId) throws ProductNotFoundException {
		Product existingProduct = productRepository.findByProdId(prodId)
				.orElseThrow(() -> new ProductNotFoundException("product not found with id " + prodId));
		productRepository.delete(existingProduct);
	}

	@Override
	public ResponseEntity<List<Product>> getAllProductsByCategoryId(String catid) {
		if (!categoryRepository.existsCategoryByCatId(catid)) {
			throw new CategoryNotFoundException("Category not found with id = " + catid);
		}
		List<Product> products = productRepository.findByCategory_CatId(catid);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> createProduct(String catid, Product newProduct) throws CategoryNotFoundException {

		Product product = categoryRepository.findByCatId(catid).map(category -> {
			newProduct.setCategory(category);
			newProduct.setProdId(generateProductId());
			return productRepository.save(newProduct);
		}).orElseThrow(() -> new CategoryNotFoundException("category not found with id = " + catid));
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@Override
	public Product getCategory_CatIdByProdName(String catid, String productname)
			throws ProductNameAlreadyExistsException {

		return productRepository.findByCategory_CatIdAndProdName(catid, productname).orElseThrow(
				() -> new ProductNameAlreadyExistsException("Product already exists with the name = " + productname));
	}

}
