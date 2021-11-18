package com.ui.model;


public class User
{

	public User(int userId,String firstName, String middleName, String lastName,String email, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public User(int userId, String firstName, String lastName, String mobileNumber1,String mobileNumber2, int createdBy, String ipAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public User(String firstName, String lastName, String address1, String address2,int areaId,
			String pincode, String mobileNumber1,String mobileNumber2, String email, String password, int userTypeId, String status,
			String ipAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.areaId = areaId;
		this.pincode = pincode;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.status = status;
		this.ipAddress = ipAddress;
	}

	public User(String firstName, String lastName, String mobileNumber1,String mobileNumber2, String email, String password, int userTypeId,
			String status, String ipAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.status = status;
		this.ipAddress = ipAddress;
	}

	public User(int userId, String firstName, String middleName, String lastName, String address1, String address2,
			 int areaId, String pincode, String mobileNumber1,String mobileNumber2, String email, String password,
			int userTypeId, String status, int createdBy, String createdDate, String ipAddress, String userTypeName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.areaId = areaId;
		this.pincode = pincode;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.userTypeName = userTypeName;
	}
	
	public User(int userId, String firstName, String middleName, String lastName, String address1, String address2,
			 int areaId, String pincode, String mobileNumber1,String mobileNumber2, String email, String password,
			int userTypeId, int createdBy, String ipAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.areaId = areaId;
		this.pincode = pincode;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	public User(String firstName, String middleName, String lastName, String address1, String address2,
			 int areaId, String pincode, String mobileNumber1, String mobileNumber2, String email, String password,
			int userTypeId, String status, int createdBy, String ipAddress) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.areaId = areaId;
		this.pincode = pincode;
		this.mobileNumber1 = mobileNumber1;
		this.mobileNumber2 = mobileNumber2;
		this.email = email;
		this.password = password;
		this.userTypeId = userTypeId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address1;
	private String address2;
	private int areaId;
	private String pincode;
	private String mobileNumber1;
	private String mobileNumber2;
	private String email;
	private String password;
	private int userTypeId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String userTypeName;
	
	public int getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public int getAreaId() {
		return areaId;
	}
	public String getPincode() {
		return pincode;
	}
	public String getMobileNumber1() {
		return mobileNumber1;
	}
	public String getMobileNumber2() {
		return mobileNumber2;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getUserTypeId() {
		return userTypeId;
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

	public String getUserTypeName() {
		return userTypeName;
	}
	
}
