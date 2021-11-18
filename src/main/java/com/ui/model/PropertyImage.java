package com.ui.model;

public class PropertyImage {
	
	public PropertyImage(int propertyImageId, int sequence, String imageName, String image, int propertyId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.propertyImageId = propertyImageId;
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public PropertyImage(int sequence, String imageName, String image, int propertyId, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int propertyImageId;
	private int sequence;
	private String imageName;
	private String image;
	private int propertyId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getPropertyImageId() {
		return propertyImageId;
	}
	public int getSequence() {
		return sequence;
	}
	public String getImageName() {
		return imageName;
	}
	public String getImage() {
		return image;
	}
	public int getPropertyId() {
		return propertyId;
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
