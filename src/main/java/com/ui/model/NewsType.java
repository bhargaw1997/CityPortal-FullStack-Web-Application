package com.ui.model;

public class NewsType {
	
	

	public NewsType(int newstypeId, String newstypeName, String newsDescription, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.newstypeId = newstypeId;
		this.newstypeName = newstypeName;
		this.newsDescription = newsDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public NewsType(int newstypeId, String newstypeName, String newsDescription, int createdBy, String ipAddress) {
		super();
		this.newstypeId = newstypeId;
		this.newstypeName = newstypeName;
		this.newsDescription = newsDescription;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public NewsType(String newstypeName, String newsDescription, String status, int createdBy, String ipAddress) {
		super();
		this.newstypeName = newstypeName;
		this.newsDescription = newsDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int newstypeId;
	private String newstypeName;
	private String newsDescription;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getNewsTypeId() {
		return newstypeId;
	}
	public String getNewsTypeName() {
		return newstypeName;
	}
	public String getNewsDescription() {
		return newsDescription;
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
