package com.mouritech.onlineshoppingsystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mouritech.onlineshoppingsystem.entity.ProductImage;
import com.mouritech.onlineshoppingsystem.exception.ProductImageNotFoundException;
import com.mouritech.onlineshoppingsystem.exception.ProductNotFoundException;
import com.mouritech.onlineshoppingsystem.service.ProductImageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class ProductImageController {

	@Autowired
	private ProductImageService productimageService;

	// add image for corresponding product
	@PostMapping("/menu/{prodId}/prodImages")
	public Optional<ProductImage> addImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {

		return productimageService.addImage(prodId, file);

	}

	// get corresponding image for product id
	@GetMapping("/menu/{prodId}/prodImages")
	public ProductImage getImageByProdId(@PathVariable(value = "prodId") String prodId) {
		return productimageService.getImageByProdId(prodId);
	}

	// get all images
	@GetMapping("/menu/images")
	public List<ProductImage> getAllImages() {

		return productimageService.getAllImages();

	}

	@GetMapping("/menu/images/{imageid}")
	public ProductImage getImageById(@PathVariable("imageid") Long imageId) throws ProductImageNotFoundException {
		return productimageService.getImageById(imageId);
	}

	// update responding image by productId
	@PutMapping("/menu/{prodId}/prodImages")
	public Optional<Object> updateImage(@PathVariable(value = "prodId") String prodId,
			@RequestParam("prodImage") MultipartFile file) throws IOException {
		return productimageService.updateImage(prodId, file);

	}

	// delete image for corresponding product
	@DeleteMapping("/menu/{prodId}/prodImages/{imageId}")
	public ResponseEntity<?> deleteImage(@PathVariable(value = "prodId") String prodId,
			@PathVariable(value = "imageId") Long imageId) throws ProductNotFoundException {

		return productimageService.deleteImage(prodId, imageId);

	}

}
