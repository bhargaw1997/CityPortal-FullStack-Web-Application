package com.ui.model;

public class Type {
	
	public Type(int typeId, String typeName, String typeCode, String image, String description,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Type(int typeId, String typeName, String typeCode, String image, String description,
			int createdBy, String ipAddress) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.image = image;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Type(String typeName, String typeCode, String image, String description,
			String status, int createdBy, String ipAddress) {
		super();
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.image = image;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int typeId;
	private String typeName;
	private String typeCode;
	private String image;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getTypeId() {
		return typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public String getImage() {
		return image;
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
}
