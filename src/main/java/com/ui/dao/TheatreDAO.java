package com.ui.dao;

import java.util.List;

import com.ui.model.Theatre;
import com.ui.model.ScreenNumber;

public interface TheatreDAO 
{
	public List<Theatre> getAllTheatres();
	public void addTheatre(Theatre e);
	public void editTheatre(Theatre e);
	public void deleteTheatre(int theatreid);
	public List<Theatre> getAllTheatresByPage(int pagesize, int startindex);
	public List<ScreenNumber> getAllScreenNumber();
	public List<ScreenNumber> getScreenNumberByTheatreId(int theatreid);
	public int getLastTheatreId();
	public void addScreenNumber(ScreenNumber screenNumber);
	public void deleteScreenNumber(int screennumberid);
}
