package com.ui.model;

public class Specification {
	
	
	public Specification(int specificationId, String specificationName, int subcategoryId, String status, int createdBy, String createdDate,
			String ipAddress, String subcategoryName, int categoryId, String categoryName) {
		super();
		this.specificationId = specificationId;
		this.specificationName = specificationName;
		this.subcategoryId = subcategoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.subcategoryName = subcategoryName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public Specification(int specificationId, String specificationName, int subcategoryId, int categoryId, int createdBy, String ipAddress) {
		super();
		this.specificationId = specificationId;
		this.specificationName = specificationName;
		this.subcategoryId = subcategoryId;
		this.categoryId = categoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Specification(String specificationName, int subcategoryId, String status, int createdBy, String ipAddress) {
		super();
		this.specificationName = specificationName;
		this.subcategoryId = subcategoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Specification(String specificationName, int subcategoryId,int categoryId, String status, int createdBy, String ipAddress) {
		super();
		this.specificationName = specificationName;
		this.subcategoryId = subcategoryId;
		this.categoryId = categoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int specificationId;
	private String specificationName;
	private int subcategoryId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private int categoryId;
	private String categoryName;
	private String subcategoryName;
	
	
	public int getSpecificationId() {
		return specificationId;
	}
	public String getSpecificationName() {
		return specificationName;
	}
	public int getProductsubcategoryId() {
		return subcategoryId;
	}
	public String getStatus() {
		return status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public int getProductcategoryId() {
		return categoryId;
	}
	public String getProductcategoryName() {
		return categoryName;
	}
	public String getProductsubcategoryName() {
		return subcategoryName;
	}
	
	
	
	
		
	
}
