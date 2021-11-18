package com.ui.dao;

import java.util.List;

import com.ui.model.Organizer;

public interface OrganizerDAO 
{
	public List<Organizer> getAllOrganizers();
	public void addOrganizer(Organizer c);
	public void editOrganizer(Organizer c);
	public void deleteOrganizer(int organizerid);
	public List<Organizer> getAllOrganizersByPage(int pagesize, int startindex);
}
