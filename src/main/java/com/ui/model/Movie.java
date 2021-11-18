package com.ui.model;

public class Movie {
	
	public Movie(int movieId, String movieName, String releaseDate,String image,String rating, String movieTrailer, String cbfc, String movieGenre , String movieDuration, String movieLanguage, String movieView,String description,
			 String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.image = image;
		this.rating = rating;
		this.movieTrailer = movieTrailer;
		this.cbfc = cbfc;
		this.movieGenre = movieGenre;
		this.movieDuration = movieDuration;
		this.movieLanguage = movieLanguage;
		this.movieView = movieView;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Movie(int movieId, String movieName, String releaseDate,String image,String rating,String movieTrailer, String cbfc, String movieGenre , String movieDuration, String movieLanguage, String movieView,  String description,
			 int createdBy, String ipAddress) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.image = image;
		this.rating = rating;
		this.movieTrailer = movieTrailer;
		this.cbfc = cbfc;
		this.movieGenre = movieGenre;
		this.movieDuration = movieDuration;
		this.movieLanguage = movieLanguage;
		this.movieView = movieView;
		this.description = description;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Movie(String movieName, String releaseDate, String image,String rating,String movieTrailer, String cbfc, String movieGenre , String movieDuration, String movieLanguage, String movieView, String description, 
			String status, int createdBy, String ipAddress) {
		super();
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.image = image;
		this.rating = rating;
		this.movieTrailer = movieTrailer;
		this.cbfc = cbfc;
		this.movieGenre = movieGenre;
		this.movieDuration = movieDuration;
		this.movieLanguage = movieLanguage;
		this.movieView = movieView;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Movie(int movieId, String movieName) {
		// TODO Auto-generated constructor stub
		this.movieId = movieId;
		this.movieName = movieName;
	}
	private int movieId;
	private String movieName;
	private String releaseDate;
	private String movieTrailer;
	private String cbfc;
	private String movieGenre;
	private String movieDuration;
	private String movieLanguage;
	private String movieView;
	private String image;
	private String description;
	private String rating;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getMovieId() {
		return movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getMovieTrailer() {
		return movieTrailer;
	}
	public String getcbfc() {
		return cbfc;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public String getMovieDuration() {
		return movieDuration;
	}
	public String getMovieLanguage() {
		return movieLanguage;
	}
	public String getMovieView() {
		return movieView;
	}
	public String getImage() {
		return image;
	}
	public String getDescription() {
		return description;
	}
	public String getRating() {
		return rating;
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
	
	
		
	
}
