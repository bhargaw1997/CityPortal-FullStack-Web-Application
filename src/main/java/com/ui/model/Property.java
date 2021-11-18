package com.ui.model;

public class Property {
	
	public Property(int propertyId, String propertyName,String propertyCondition,String projectType,String propertyType,String projectSubtype,Float propertyPrice,String propertyLocation, int sequence, String description,
			String status, int createdBy, String createdDate,
			String ipAddress, String imageName, String image) {
		super();
		this.propertyId = propertyId;
		this.propertyName = propertyName;
		this.propertyCondition = propertyCondition;
		this.projectType = projectType;
		this.propertyType = propertyType;
		this.projectSubtype = projectSubtype;
		this.propertyPrice = propertyPrice;
		this.propertyLocation=propertyLocation;
		this.sequence = sequence;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.imageName = imageName;
		this.image = image;
	}
	public Property(int propertyId, int createdBy, String ipAddress) {
		super();
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Property(int propertyId, String propertyName,String propertyCondition,String projectType,String propertyType,String projectSubtype,Float propertyPrice,String propertyLocation, int sequence, String description,
			String status, int createdBy, String createdDate,
			String ipAddress) {
		super();
		this.propertyId = propertyId;
		this.propertyName = propertyName;
		this.propertyCondition = propertyCondition;
		this.projectType = projectType;
		this.propertyType = propertyType;
		this.projectSubtype = projectSubtype;
		this.propertyPrice = propertyPrice;
		this.propertyLocation=propertyLocation;
		this.sequence = sequence;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Property(int propertyId, String propertyName,String propertyCondition,String projectType,String propertyType,String projectSubtype,Float propertyPrice,String propertyLocation, String description, int createdBy, String ipAddress) {
		super();
		this.propertyId = propertyId;
		this.propertyCondition = propertyCondition;
		this.projectType = projectType;
		this.propertyType = propertyType;
		this.projectSubtype = projectSubtype;
		this.propertyPrice = propertyPrice;
		this.propertyLocation=propertyLocation;
		this.propertyName = propertyName;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Property(String propertyName,String propertyCondition,String projectType,String propertyType,String projectSubtype,Float propertyPrice,String propertyLocation,int sequence, String description,String status, int createdBy, String ipAddress) {
		super();
		this.propertyName = propertyName;
		this.propertyCondition = propertyCondition;
		this.projectType = projectType;
		this.propertyType = propertyType;
		this.projectSubtype = projectSubtype;
		this.propertyPrice = propertyPrice;
		this.propertyLocation=propertyLocation;
		this.sequence = sequence;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int propertyId;
	private String propertyName;
	private String propertyCondition;
	private String projectType;
	private String propertyType;
	private String projectSubtype;
	private float propertyPrice;
	private String propertyLocation;
	private int sequence;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String imageName;
	private String image;
	
	
	public int getPropertyId() {
		return propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public String getPropertyCondition() {
		return propertyCondition;
	}
	public String getProjectType() {
		return projectType;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public String getProjectSubtype() {
		return projectSubtype;
	}
	public float getPropertyPrice() {
		return propertyPrice;
	}
	public String getPropertyLocation() {
		return propertyLocation;
	}
	public int getSequence() {
		return sequence;
	}
	public String getDescription() {
		return description;
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
	public String getImageName() {
		return imageName;
	}
	public String getImage() {
		return image;
	}

	
	
		
	
}
