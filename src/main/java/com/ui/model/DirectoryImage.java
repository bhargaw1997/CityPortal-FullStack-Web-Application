package com.ui.model;

public class DirectoryImage {
	
	public DirectoryImage(int imageId, int sequence, String imageName, String image, int directoryId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.imageId = imageId;
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.directoryId = directoryId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public DirectoryImage(int sequence, String imageName, String image, int directoryId, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.directoryId = directoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int imageId;
	private int sequence;
	private String imageName;
	private String image;
	private int directoryId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getImageId() {
		return imageId;
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
	public int getDirectoryId() {
		return directoryId;
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
