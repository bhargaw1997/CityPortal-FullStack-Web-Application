package com.ui.model;


public class Review {
	
	public Review(int reviewId,String reviewerName, String review, int propertyId, int createdBy,
			String createdDate, String ipAddress) {
		super();
		this.reviewId = reviewId;
		this.reviewerName = reviewerName;
		this.review=review;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Review(int reviewId, String reviewerName, String review, int propertyId, int createdBy, String ipAddress) {
		super();
		this.reviewId = reviewId;
		this.reviewerName = reviewerName;
		this.review=review;
		this.propertyId = propertyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int reviewId;
	private String reviewerName;
	private String review;
	private int propertyId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getReviewId() {
		return reviewId;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public String getReview() {
		return review;
	}
	public int getPropertyId() {
		return propertyId;
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
