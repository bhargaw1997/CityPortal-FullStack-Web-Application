package com.ui.dao;

import java.util.List;

import com.ui.model.TimeSlot;
import com.ui.model.Directory;
import com.ui.model.Movie;
import com.ui.model.Theatre;

public interface MoviesDAO 
{
	public List<Movie> getAllMovies();
	public List<Movie> getLastSixMovies();
	public void addMovie(Movie c);
	public void editMovie(Movie c);
	public void deleteMovie(int movieid);
	public int getLastMovieId();
	public List<TimeSlot> getAllTimeSlot();
	public List<TimeSlot> getTimeSlotByMovieId(int movieid);
	public List<Theatre> getTheatreByMovieId(int movieid);
	public void addTimeSlot(TimeSlot timeSlot);
	public void deleteTimeSlot(int timeslotid);
	public List<Movie> getAllMoviesByPage(int pagesize, int startindex);
/*	public List<Category> searchCategories(String keyword);
	public List<Category> getAllCategoriesForFrontEnd();*/
	public Movie getMovieByMovieId(int movieid);
}
