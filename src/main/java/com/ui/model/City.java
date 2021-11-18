package com.ui.model;

public class City {
	
	
	public City(int cityId, String cityName, int stateId, String status, int createdBy, String createdDate,
			String ipAddress, String stateName, int countryId, String countryName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.stateName = stateName;
		this.countryId = countryId;
		this.countryName = countryName;
	}
	public City(int cityId, String cityName, int stateId, int createdBy, String ipAddress) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public City(String cityName, int stateId, String status, int createdBy, String ipAddress) {
		super();
		this.cityName = cityName;
		this.stateId = stateId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int cityId;
	private String cityName;
	private int stateId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private int countryId;
	private String countryName;
	private String stateName;
	
	
	public int getCityId() {
		return cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public int getStateId() {
		return stateId;
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
	public int getCountryId() {
		return countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public String getStateName() {
		return stateName;
	}
	
	
	
	
		
	
}
