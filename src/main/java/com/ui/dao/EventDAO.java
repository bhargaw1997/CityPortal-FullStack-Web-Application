package com.ui.dao;

import java.util.List;

import com.ui.model.Event;
import com.ui.model.EventImage;
import com.ui.model.EventAgenda;

public interface EventDAO 
{
	public List<Event> getAllEvents();
	public Event getEventByEventId(int eventid);
	public void addEvent(Event e);
	public void editEvent(Event e);
	public void deleteEvent(int eventid);
	public List<Event> getAllEventsByPage(int pagesize, int startindex);
	public List<Event> getEventsWithOneImageByPage(int pagesize, int startindex);
	public List<EventImage> getEventImageByEventId(int eventid);
	public List<EventAgenda> getAllEventAgenda();
	public List<EventAgenda> getEventAgendaByEventId(int eventid);
	public int getLastEventId();
	public void addEventImage(EventImage eventimage);
	public void deleteEventImage(int imageid);
	public void addEventAgenda(EventAgenda eventAgenda);
	public void deleteEventAgenda(int eventagendaid);
	public List<Event> getEventsWithOneImageByEventId(int eventid);
	public List<Event> getLastEightEventWithOneImage();
}
