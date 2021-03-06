package com.ui.dao;

import java.util.List;

import com.ui.model.Subcategory;

public interface SubcategoryDAO 
{
	public List<Subcategory> getAllSubcategories();
	public List<Subcategory> getSubcategoriesByCategoryId(int categoryid);
	public void addSubcategory(Subcategory c);
	public void editSubcategory(Subcategory c);
	public void deleteSubcategory(int subcategoryid);
	public List<Subcategory> getAllSubcategoriesByPage(int pagesize, int startindex);
	/*public List<Subcategory> searchSubcategories(String keyword);*/
}
