package com.ui.model;

public class Theatre {
	
	public Theatre(int theatreId, String theatreName,String theatreAddress, String theatredescription,
			 String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatredescription = theatredescription;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	
	public Theatre(String theatreName,String theatreAddress, String theatredescription, String status, int createdBy,String ipAddress) {
		super();
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatredescription = theatredescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public Theatre(int theatreId, String theatreName,String theatreAddress,String theatredescription,
		int createdBy,String ipAddress) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreAddress = theatreAddress;
		this.theatredescription = theatredescription;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public Theatre(int theatreId, String theatreName) {
			super();
			this.theatreId = theatreId;
			this.theatreName = theatreName;
		}
	
	private int theatreId;
	private String theatreName;
	private String theatreAddress;
	private String theatredescription;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getTheatreId() {
		return theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public String getTheatreAddress() {
		return theatreAddress;
	}
	public String getTheatredescription() {
		return theatredescription;
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
