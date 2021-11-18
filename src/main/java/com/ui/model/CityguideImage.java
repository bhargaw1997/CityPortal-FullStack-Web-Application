package com.ui.model;

public class CityguideImage {
	
	public CityguideImage(int cityguideImageId, int sequence, String imageName, String image, int cityguideId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.cityguideImageId = cityguideImageId;
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.cityguideId = cityguideId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public CityguideImage(int sequence, String imageName, String image, int cityguideId, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.cityguideId = cityguideId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int cityguideImageId;
	private int sequence;
	private String imageName;
	private String image;
	private int cityguideId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getCityguideImageId() {
		return cityguideImageId;
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
	public int getCityguideId() {
		return cityguideId;
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
