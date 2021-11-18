package com.ui.model;

public class Area {
	
	
	public Area(int areaId, String areaName, int cityId, String areaCode, String status, int createdBy, String createdDate,
			String ipAddress, String cityName, int stateId, String stateName, int countryId, String countryName) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.cityId = cityId;
		this.areaCode = areaCode;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.cityName = cityName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.countryId = countryId;
		this.countryName = countryName;
	}
	public Area(int areaId, String areaName, int cityId, String areaCode, int createdBy, String ipAddress) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.cityId = cityId;
		this.areaCode = areaCode;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Area(String areaName, int cityId, String areaCode, String status, int createdBy, String ipAddress) {
		super();
		this.areaName = areaName;
		this.cityId = cityId;
		this.areaCode = areaCode;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int areaId;
	private String areaName;
	private int cityId;
	private String areaCode;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String cityName;
	private int stateId;
	private String stateName;
	private int countryId;
	private String countryName;
	
	
	public int getAreaId() {
		return areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public int getCityId() {
		return cityId;
	}
	public String getAreaCode() {
		return areaCode;
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
	public String getCityName() {
		return cityName;
	}
	public int getStateId() {
		return stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public int getCountryId() {
		return countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	
	
	
	
	
	
		
	
}
