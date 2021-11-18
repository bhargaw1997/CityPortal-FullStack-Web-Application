package com.ui.model;

public class Product {
	
	public Product(int productId, String productName,  int sequence, String description,int categoryId, int subcategoryId,  String status, int createdBy, String createdDate,
			String ipAddress, String imageTitle, String image) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.sequence = sequence;
		this.description = description;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.imageTitle = imageTitle;
		this.image = image;
	}
	public Product(int productId, int createdBy, String ipAddress) {
		super();
		this.productId = productId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Product(int productId, String productName, int sequence, String description, int categoryId, int subcategoryId,  String status, int createdBy, String createdDate,
			String ipAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.sequence = sequence;
		this.description = description;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Product(int productId, String productName, String description, int categoryId, int subcategoryId, int createdBy, String ipAddress) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Product(String productName, int sequence, String description, int categoryId,int subcategoryId, String status, int createdBy, String ipAddress) {
		super();
		this.productName = productName;
		this.sequence = sequence;
		this.description = description;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int productId;
	private String productName;
	private int sequence;
	private String description;
	private int categoryId;
	private int subcategoryId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String imageTitle;
	private String image;
	
	
	public int getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public int getSequence() {
		return sequence;
	}
	public String getDescription() {
		return description;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public int getSubcategoryId() {
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
	public String getImageTitle() {
		return imageTitle;
	}
	public String getImage() {
		return image;
	}
	
	
		
	
}
