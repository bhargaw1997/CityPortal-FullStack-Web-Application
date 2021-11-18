package com.ui.model;

public class Cityguide {
	
	public Cityguide(int cityguideId, String placeName, String locationUrl, String description, String status, int createdBy, String createdDate, String ipAddress, String imageName, String image) {
		super();
		this.cityguideId = cityguideId;
		this.placeName = placeName;
		this.locationUrl = locationUrl;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.imageName = imageName;
		this.image = image;
	}
	public Cityguide(int cityguideId, int createdBy, String ipAddress) {
		super();
		this.cityguideId = cityguideId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Cityguide(int cityguideId, String placeName, String locationUrl, String description, String status, int createdBy, String createdDate,
			String ipAddress) {
		super();
		this.cityguideId = cityguideId;
		this.placeName = placeName;
		this.locationUrl = locationUrl;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Cityguide(int cityguideId, String placeName, String locationUrl, String description, int createdBy, String ipAddress) {
		super();
		this.cityguideId = cityguideId;
		this.placeName = placeName;
		this.locationUrl = locationUrl;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Cityguide(String placeName, String locationUrl, String description, String status, int createdBy, String ipAddress) {
		super();
		this.placeName = placeName;
		this.locationUrl = locationUrl;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int cityguideId;
	private String placeName;
	private String locationUrl;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String imageName;
	private String image;
	
	
	public int getCityguideId() {
		return cityguideId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public String getLocationUrl() {
		return locationUrl;
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
