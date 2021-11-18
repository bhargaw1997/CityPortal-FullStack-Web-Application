package com.ui.dao;

import java.util.List;

import com.ui.model.Property;
import com.ui.model.PropertyImage;
import com.ui.model.Amenity;
import com.ui.model.Review;
import com.ui.model.PropertySpecification;

public interface PropertyDAO 
{
	public List<Property> getAllProperties();
	public void addProperty(Property p);
	public void editProperty(Property p);
	public void deleteProperty(int propertyid);
	public List<Property> getAllPropertiesByPage(int pagesize, int startindex);
	public List<PropertyImage> getPropertyImageByPropertyId(int propertyid);
	public List<Review> getReviewByPropertyId(int propertyid);
	public List<Amenity> getPropertyAmenitiesByPropertyId(int propertyid);
	public List<PropertySpecification> getPropertySpecification1ByPropertyId(int propertyid);
	public int getLastPropertyId();
	public void addPropertyImage(PropertyImage propertyImage);
	public void addReview(Review review);
	public void addPropertyAmenities(Amenity amenities);
	public void addPropertySpecification1(PropertySpecification propertyspecification);
	public void deletePropertyImage(int propertyid);
	public void deleteReview(int reviewid);
	public void deleteAmenities(int amenitiesid);
	public void deletePropertySpecification1(int propertyspecifcationid);
	public List<Property> getPropertiesWithOneImageByPropertyId(int propertyid);
}
