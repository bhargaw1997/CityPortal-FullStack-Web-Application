package com.ui.dao;

import java.util.List;

import com.ui.model.Type;

public interface TypeDAO 
{
	public List<Type> getAllTypes();
	public void addType(Type c);
	public void editType(Type c);
	public void deleteType(int typeid);
	public List<Type> getAllTypesByPage(int pagesize, int startindex);
/*	public List<Category> searchCategories(String keyword);
	public List<Category> getAllCategoriesForFrontEnd();*/
}
