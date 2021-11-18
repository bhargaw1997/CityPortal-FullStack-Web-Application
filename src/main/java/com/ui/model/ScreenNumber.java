package com.ui.model;


public class ScreenNumber {
	
	public ScreenNumber(int screenNumberId,String screennumber, String noofSeats, int theatreId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.screenNumberId = screenNumberId;
		this.screennumber = screennumber;
		this.noofSeats = noofSeats;
		this.theatreId = theatreId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	
	public ScreenNumber(int screenNumberId,String screennumber, String noofSeats, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.screenNumberId = screenNumberId;
		this.screennumber = screennumber;
		this.noofSeats = noofSeats;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}

	public ScreenNumber(String screennumber,String noofSeats, int theatreId, int createdBy, String ipAddress) {
		super();
		this.screennumber = screennumber;
		this.noofSeats = noofSeats;
		this.theatreId = theatreId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public ScreenNumber(int screenNumberId,String screennumber,String noofSeats, int theatreId, int createdBy,String ipAddress) {
		super();
		this.screenNumberId = screenNumberId;
		this.screennumber = screennumber;
		this.noofSeats = noofSeats;
		this.theatreId = theatreId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int screenNumberId;
	private String screennumber;
	private String noofSeats;
	private int theatreId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getScreenNumberId() {
		return screenNumberId;
	}
	public String getScreenNumber() {
		return screennumber;
	}
	public String getNoofSeats() {
		return noofSeats;
	}
	public int getTheatreId() {
		return theatreId;
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
