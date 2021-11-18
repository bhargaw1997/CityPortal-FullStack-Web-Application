package com.ui.dao;

import java.util.List;

import com.ui.model.Producttax;

public interface ProducttaxDAO 
{
	public List<Producttax> getAllProducttaxes();
	public void addProducttax(Producttax c);
	public void editProducttax(Producttax c);
	public void deleteProducttax(int producttaxid);
	public List<Producttax> getAllProducttaxesByPage(int pagesize, int startindex);

}
