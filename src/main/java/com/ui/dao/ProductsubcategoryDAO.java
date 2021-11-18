package com.ui.dao;

import java.util.List;

import com.ui.model.Productsubcategory;

public interface ProductsubcategoryDAO 
{
	public List<Productsubcategory> getAllProductsubcategories();
	public List<Productsubcategory> getProductsubcategoryByProductcategoryId(int productcategoryId);
	public void addProductsubcategory(Productsubcategory s);
	public void editProductsubcategory(Productsubcategory s);
	public void deleteProductsubcategory(int productsubcategoryid);
	public List<Productsubcategory> getAllProductsubcategoriesByPage(int pagesize, int startindex);

}
