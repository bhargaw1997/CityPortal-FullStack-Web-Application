package com.ui.model;


public class EventAgenda {
	
	public EventAgenda(int eventAgendaId,String eventagenda, int eventId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.eventAgendaId = eventAgendaId;
		this.eventagenda = eventagenda;
		this.eventId = eventId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public EventAgenda(int eventAgendaId,String eventagenda,int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.eventAgendaId = eventAgendaId;
		this.eventagenda = eventagenda;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public EventAgenda(String eventagenda, int eventId, int createdBy, String ipAddress) {
		super();
		this.eventagenda = eventagenda;
		this.eventId = eventId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public EventAgenda(int eventAgendaId,String eventagenda, int eventId, int createdBy,String ipAddress) {
		super();
		this.eventAgendaId = eventAgendaId;
		this.eventagenda = eventagenda;
		this.eventId = eventId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int eventAgendaId;
	private String eventagenda;
	private int eventId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getEventAgendaId() {
		return eventAgendaId;
	}
	public String getEventAgenda() {
		return eventagenda;
	}
	public int getEventId() {
		return eventId;
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
