package com.ui.model;

public class Productcategory {
	
	

	public Productcategory(int categoryId, String categoryName, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Productcategory(int categoryId, String categoryName, int createdBy, String ipAddress) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Productcategory(String categoryName, String status, int createdBy, String ipAddress) {
		super();
		this.categoryName = categoryName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int categoryId;
	private String categoryName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getProductcategoryId() {
		return categoryId;
	}
	public String getProductcategoryName() {
		return categoryName;
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
	
		
	
}
