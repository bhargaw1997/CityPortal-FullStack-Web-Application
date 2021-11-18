package com.ui.model;

public class Amenity {
	

	public Amenity(int amenityId, String amenityName, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.amenityId = amenityId;
		this.amenityName = amenityName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Amenity(int amenityId, String amenityName, int createdBy, String ipAddress) {
		super();
		this.amenityId = amenityId;
		this.amenityName = amenityName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Amenity(String amenityName,String status, int createdBy, String ipAddress) {
		super();
		this.amenityName = amenityName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public Amenity(int propertyAmenitiesId, int amenityId, String value, int propertyId, int createdBy,
			String createdDate, String ipAddress, String amenityName) {
		super();
		this.propertyAmenitiesId = propertyAmenitiesId;
		this.amenityId = amenityId;
		this.value = value;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.amenityName = amenityName;
	}
	public Amenity(int amenityId, String value, int propertyId, int createdBy, String ipAddress) {
		super();
		this.amenityId = amenityId;
		this.value = value;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	
	private int amenityId;
	private int propertyAmenitiesId;
	private String amenityName;
	private String value;
	private int propertyId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getAmenityId() {
		return amenityId;
	}
	public int getPropertyAmentiesId() {
		return propertyAmenitiesId;
	}
	public String getAmenityName() {
		return amenityName;
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
