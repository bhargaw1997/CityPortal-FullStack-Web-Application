package com.ui.dao;

import java.util.List;

import com.ui.model.Category;

public interface CategoryDAO 
{
	public List<Category> getAllCategories();
	public void addCategory(Category c);
	public void editCategory(Category c);
	public List<Category> searchCategories(String keyword);
	public void deleteCategory(int categoryid);
	public List<Category> getAllCategoriesByPage(int pagesize, int startindex);
/*	public List<Category> searchCategories(String keyword);
	public List<Category> getAllCategoriesForFrontEnd();*/
	public List<Category> getFeaturedCategory();
}
