package com.ui.model;

public class News {
	
	public News(int newsId, String newsTitle, String newsSubtitle, String newsDescription,
			int newstypeId, String status, int createdBy, String createdDate, String ipAddress, String imageName, String image) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.imageName = imageName;
		this.image = image;
	}
	
	public News(int newsId, String newsTitle, String newsSubtitle,String newsDescription,
			int newstypeId, String status, int createdBy, String createdDate, String ipAddress, String newstypeName) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.newstypeName=newstypeName;
	}
	
	public News(int newsId, String newsTitle, String newsSubtitle,String newsDescription,
			int newstypeId, String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	
	public News(int newsId, String newsTitle, String newsSubtitle,String newsDescription,
			int newstypeId, String status, int createdBy, String createdDate, String ipAddress, String newstypeName,
			String imageName, String image) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.newstypeName=newstypeName;
		this.imageName = imageName;
		this.image = image;
	}
	
	public News(String newsTitle, String newsSubtitle, String newsDescription,
			int newstypeId, String status, int createdBy,String ipAddress) {
		super();
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public News(int newsId, String newsTitle, String newsSubtitle,String newsDescription,
			int newstypeId,int createdBy,String ipAddress) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDescription = newsDescription;
		this.newstypeId = newstypeId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	private int newsId;
	private String newsTitle;
	private String newsSubtitle;
	private String newsDescription;
	private int newstypeId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String newstypeName;
	
	private String imageName;
	private String image;
	
	
	public int getNewsId() {
		return newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public String getNewsSubtitle() {
		return newsSubtitle;
	}
	public String getNewsdescription() {
		return newsDescription;
	}
	public int getNewsTypeId() {
		return newstypeId;
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
	public String getNewsTypeName() {
		return newstypeName;
		
	}
	
}
