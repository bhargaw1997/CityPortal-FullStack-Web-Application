package com.ui.model;

public class PropertySpecification {
	
	

	public PropertySpecification(int specificationId, String specificationName, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.specificationId = specificationId;
		this.specificationName = specificationName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public PropertySpecification(int specificationId, String specificationName, int createdBy, String ipAddress) {
		super();
		this.specificationId = specificationId;
		this.specificationName = specificationName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public PropertySpecification(String specificationName,String status, int createdBy, String ipAddress) {
		super();
		this.specificationName = specificationName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public PropertySpecification(int propertyspecificationId, int specificationId, String value, int propertyId, int createdBy,
			String createdDate, String ipAddress, String specificationName) {
		super();
		this.propertyspecificationId = propertyspecificationId;
		this.specificationId = specificationId;
		this.value = value;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.specificationName = specificationName;
	}
	public PropertySpecification(int specificationId, String value, int propertyId, int createdBy, String ipAddress) {
		super();
		this.specificationId = specificationId;
		this.value = value;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	private int propertyspecificationId;
	private int specificationId;
	private String specificationName;
	private String value;
	private int propertyId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	
	public int getPropertySpecificationId() {
		return propertyspecificationId;
	}
	public int getSpecificationId() {
		return specificationId;
	}
	public String getSpecificationName() {
		return specificationName;
	}
	public String getValue() {
		return value;
	}
	public int getPropertyId() {
		return propertyId;
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
