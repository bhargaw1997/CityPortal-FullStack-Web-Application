package com.ui.model;


public class TimeSlot {
	
	public TimeSlot(int timeSlotId,int screenNumberId,String showTime, int movieId, int createdBy,
			String createdDate, String ipAddress,String screennumber,String theatreName,int theatreId) {
		super();
		this.timeSlotId = timeSlotId;
		this.screenNumberId = screenNumberId;
		this.showTime = showTime;
		this.movieId = movieId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.screennumber = screennumber;
		this.theatreName = theatreName;
		this.theatreId = theatreId;
	}
	public TimeSlot(int timeSlotId,int screenNumberId,String showTime,int createdBy,
			String createdDate, String ipAddress,String screennumber,String theatreName) {
		super();
		this.timeSlotId = timeSlotId;
		this.screenNumberId = screenNumberId;
		this.showTime = showTime;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.screennumber = screennumber;
		this.theatreName = theatreName;
	}
	public TimeSlot(int screenNumberId,String showTime, int movieId, int createdBy, String ipAddress) {
		super();
		
		this.screenNumberId = screenNumberId;
		this.showTime = showTime;
		this.movieId = movieId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public TimeSlot(int timeSlotId,int screenNumberId,String showTime, int movieId, int createdBy,String ipAddress,String screennumber,String theatreName) {
		super();
		this.timeSlotId = timeSlotId;
		this.screenNumberId = screenNumberId;
		this.showTime = showTime;
		this.movieId = movieId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.screennumber = screennumber;
		this.theatreName = theatreName;
	}
	private int timeSlotId;
	private int screenNumberId;
	private String showTime;
	private int movieId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String screennumber;
	private String theatreName;
	private int theatreId;
	
	public int getTimeSlotId() {
		return timeSlotId;
	}
	public int getScreenNumberId() {
		return screenNumberId;
	}
	public int getTheatreId() {
		return theatreId;
	}
	public String getShowTime() {
		return showTime;
	}
	public int getMovieId() {
		return movieId;
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
	public String getScreenNumber() {
		return screennumber;
	}
	public String getTheatreName() {
		return theatreName;
	}
}
