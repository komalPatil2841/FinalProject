package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import com.mouritech.onlineshoppingsystem.entity.Category;
import com.mouritech.onlineshoppingsystem.exception.CategoryNotFoundException;

public interface CategoryService {
	Category insertCategory(Category newCategory);

	Category showCatById(String catId) throws CategoryNotFoundException;

	List<Category> showAllCategorys();

	Category updateCatById(String catId, Category category) throws CategoryNotFoundException;

	void deleteCatById(String catId) throws CategoryNotFoundException;

}
