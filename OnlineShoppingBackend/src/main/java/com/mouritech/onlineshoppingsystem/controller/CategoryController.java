package com.mouritech.onlineshoppingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.mouritech.onlineshoppingsystem.entity.Category;

import com.mouritech.onlineshoppingsystem.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	//add category 
	@PostMapping("category")
	public Category insertCategory(@RequestBody Category newCategory) {

		return categoryService.insertCategory(newCategory);

	}
	//get all category
	@GetMapping("category")
	public List<Category> showAllCategorys() {
		return categoryService.showAllCategorys();

	}
	//get category by category id
	@GetMapping("category/{cid}")
	public Category showCatById(@PathVariable("cid") String catId) throws CategoryNotFoundException {
		return categoryService.showCatById(catId);

	}
	//update category by category id
	@PutMapping("category/{cid}")
	public Category updateCatById(@PathVariable("cid") String catId, @RequestBody Category category)
			throws CategoryNotFoundException {
		return categoryService.updateCatById(catId, category);

	}
	//delete category by category id 
	@DeleteMapping("category/{cid}")
	public String deleteCatById(@PathVariable("cid") String catId) throws CategoryNotFoundException {
		categoryService.deleteCatById(catId);
		return "deleted the category";

	}

}
