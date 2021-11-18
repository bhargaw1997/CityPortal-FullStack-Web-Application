package com.ui.model;

public class Producttax {
	
	

	public Producttax(int taxId, String taxName, String status, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.taxId = taxId;
		this.taxName = taxName;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Producttax(int taxId, String taxName, int createdBy, String ipAddress) {
		super();
		this.taxId = taxId;
		this.taxName = taxName;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Producttax(String taxName, String status, int createdBy, String ipAddress) {
		super();
		this.taxName = taxName;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int taxId;
	private String taxName;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getProducttaxId() {
		return taxId;
	}
	public String getProducttaxName() {
		return taxName;
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
