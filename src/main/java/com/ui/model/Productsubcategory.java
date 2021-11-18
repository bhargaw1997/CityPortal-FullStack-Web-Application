package com.ui.model;

public class Productsubcategory {
	
	
	public Productsubcategory(int subcategoryId, String subcategoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
	}
	public Productsubcategory(int subcategoryId, String subcategoryName, int categoryId, String status, int createdBy,
			String createdDate, String ipAddress, String categoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
		this.categoryId = categoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.categoryName = categoryName;
	}
	public Productsubcategory(int subcategoryId, String subcategoryName, int categoryId, int createdBy, String ipAddress) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
		this.categoryId = categoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Productsubcategory(String subcategoryName, int categoryId, String status, int createdBy, String ipAddress) {
		super();
		this.subcategoryName = subcategoryName;
		this.categoryId = categoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int subcategoryId;
	private String subcategoryName;
	private int categoryId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String categoryName;
	
	
	public int getProductsubcategoryId() {
		return subcategoryId;
	}
	public String getProductsubcategoryName() {
		return subcategoryName;
	}
	public int getProductcategoryId() {
		return categoryId;
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
	public String getProductcategoryName() {
		return categoryName;
	}
	
	
	
		
	
}
