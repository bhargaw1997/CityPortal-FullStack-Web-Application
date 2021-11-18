package com.ui.model;

public class Organizer {
	
	

	public Organizer(int organizerId, String organizerName, String mobileNumber, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.organizerId = organizerId;
		this.organizerName = organizerName;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Organizer(int organizerId, String organizerName, String mobileNumber, int createdBy, String ipAddress) {
		super();
		this.organizerId = organizerId;
		this.organizerName = organizerName;
		this.mobileNumber = mobileNumber;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Organizer(String organizerName, String mobileNumber, String status, int createdBy, String ipAddress) {
		super();
		this.organizerName = organizerName;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int organizerId;
	private String organizerName;
	private String mobileNumber;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getOrganizerId() {
		return organizerId;
	}
	public String getOrganizerName() {
		return organizerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
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
