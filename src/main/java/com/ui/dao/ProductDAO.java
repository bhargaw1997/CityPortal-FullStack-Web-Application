package com.ui.dao;

import java.util.List;

import com.ui.model.Product;
import com.ui.model.ProductImage;
import com.ui.model.Tax;
import com.ui.model.State;

public interface ProductDAO 
{
	public List<Product> getAllProducts();
	public void addProduct(Product p);
	public void editProduct(Product p);
	public void deleteProduct(int productid);
	public List<Product> getAllProductsByPage(int pagesize, int startindex);
	public List<ProductImage> getProductImageByProductId(int productid);
	public List<Tax> getTaxByProductId(int productid);
	public List<Tax> getTaxByProductIdAndStateId(int productid, int stateid);
	public int getLastProductId();
	public void addProductImage(ProductImage productImage);
	public void addTax(Tax productTax);
	public void deleteProductImage(int productid);
	public void deleteTax(int productid);
	public List<Product> getLastProductByCategoryIdAndSubcategoryId(int categoryid, int subcategoryid);
	public List<Product> getProductsByCategoryId(int categoryid);
	public List<Product> getProductsByCategoryIdAndSubcategoryId(int categoryid, int subcategoryid);
	public List<Product> getProductsWithOneImageByCategoryId(int categoryid);
	public List<Product> getProductsWithOneImageBySubcategoryId(int subcategoryid);
	public List<Product> getProductsWithOneImageByProductId(int productid);
	public List<Product> getProductsByBrandId(int brandid);
	public List<State> getStatesByProductIdAndTaxId(int productid, int taxId);
}
