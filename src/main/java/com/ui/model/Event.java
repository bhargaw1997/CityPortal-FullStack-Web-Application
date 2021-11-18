package com.ui.model;

public class Event {
	
	public Event(int eventId, String eventName,String eventVenue,String registrationFees,String eventStartdate,String eventEnddate, String eventdescription,
			int organizerId, String status, int createdBy, String createdDate, String ipAddress, String imageName, String image) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.registrationFees = registrationFees;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventdescription = eventdescription;
		this.organizerId = organizerId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.imageName = imageName;
		this.image = image;
	}
	
	public Event(int eventId, String eventName,String eventVenue,String registrationFees,String eventStartdate,String eventEnddate, String eventdescription,
			int organizerId, String status, int createdBy, String createdDate, String ipAddress,String organizerName, String imageName, String image) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.registrationFees = registrationFees;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventdescription = eventdescription;
		this.organizerId = organizerId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.organizerName=organizerName;
		this.imageName = imageName;
		this.image = image;
	}
	
	public Event(int eventId, String eventName,String eventVenue,String registrationFees,String eventStartdate,String eventEnddate, String eventdescription,
			int organizerId, String status, int createdBy, String createdDate, String ipAddress,String organizerName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.registrationFees = registrationFees;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventdescription = eventdescription;
		this.organizerId = organizerId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.organizerName=organizerName;
	}
	
	public Event(String eventName,String eventVenue,String registrationFees,String eventStartdate,String eventEnddate, String eventdescription,
			int organizerId, String status, int createdBy,String ipAddress) {
		super();
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.registrationFees = registrationFees;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventdescription = eventdescription;
		this.organizerId = organizerId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public Event(int eventId, String eventName,String eventVenue,String registrationFees,String eventStartdate,String eventEnddate,String eventdescription,
			int organizerId,int createdBy,String ipAddress) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVenue = eventVenue;
		this.registrationFees = registrationFees;
		this.eventStartdate = eventStartdate;
		this.eventEnddate = eventEnddate;
		this.eventdescription = eventdescription;
		this.organizerId = organizerId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	private int eventId;
	private String eventName;
	private String eventVenue;
	private String registrationFees;
	private String eventStartdate;
	private String eventEnddate;
	private String eventdescription;
	private int organizerId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String organizerName;
	private String imageName;
	private String image;
	
	
	public int getEventId() {
		return eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public String getEventVenue() {
		return eventVenue;
	}
	public String getRegistrationFees() {
		return registrationFees;
	}
	public String getEventStartdate() {
		return eventStartdate;
	}
	public String getEventEnddate() {
		return eventEnddate;
	}
	public String getEventdescription() {
		return eventdescription;
	}
	public int getOrganizerId() {
		return organizerId;
	}
	public String getOrganizerName() {
		return organizerName;
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
	
}
