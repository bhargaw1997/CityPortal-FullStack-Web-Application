package com.ui.model;

public class NewsImage {
	
	public NewsImage(int newsImageId, int sequence, String imageName, String image, int newsId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.newsImageId = newsImageId;
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.newsId = newsId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public NewsImage(int sequence, String imageName, String image, int newsId, int createdBy, String ipAddress) {
		super();
		this.sequence = sequence;
		this.imageName = imageName;
		this.image = image;
		this.newsId = newsId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int newsImageId;
	private int sequence;
	private String imageName;
	private String image;
	private int newsId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getNewsImageId() {
		return newsImageId;
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
	public int getNewsId() {
		return newsId;
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
