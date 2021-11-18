package com.ui.dao;

import java.util.List;

import com.ui.model.Specification;

public interface SpecificationDAO 
{
	public List<Specification> getAllSpecifications();
	public List<Specification> getSpecificationByProductsubcategoryId(int productsubcategoryId);
	public void addSpecification(Specification c);
	public void editSpecification(Specification c);
	public void deleteSpecification(int specificationid);
	public List<Specification> getAllSpecificationsByPage(int pagesize, int startindex);

}
