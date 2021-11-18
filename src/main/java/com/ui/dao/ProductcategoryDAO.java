package com.ui.dao;

import java.util.List;

import com.ui.model.Productcategory;

public interface ProductcategoryDAO 
{
	public List<Productcategory> getAllProductcategories();
	public void addProductcategory(Productcategory c);
	public void editProductcategory(Productcategory c);
	public void deleteProductcategory(int productcategoryid);
	public List<Productcategory> getAllProductcategoriesByPage(int pagesize, int startindex);

}
