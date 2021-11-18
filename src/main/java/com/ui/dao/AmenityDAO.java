package com.ui.dao;

import java.util.List;

import com.ui.model.Amenity;

public interface AmenityDAO 
{
	public List<Amenity> getAllAmenities();
	public void addAmenity(Amenity c);
	public void editAmenity(Amenity c);
	public void deleteAmenity(int amenityid);
	public List<Amenity> getAllAmenitiesByPage(int pagesize, int startindex);
}
