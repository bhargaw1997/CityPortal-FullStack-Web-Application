package com.ui.dao;

import java.util.List;

import com.ui.model.PropertySpecification;

public interface PropertySpecificationDAO 
{
	public List<PropertySpecification> getAllPropertySpecification();
	public void addPropertySpecification(PropertySpecification c);
	public void editPropertySpecification(PropertySpecification c);
	public void deletePropertySpecification(int specificationid);
	public List<PropertySpecification> getAllPropertySpecificationByPage(int pagesize, int startindex);
}
