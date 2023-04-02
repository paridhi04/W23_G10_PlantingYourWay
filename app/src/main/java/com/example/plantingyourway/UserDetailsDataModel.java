package com.example.plantingyourway;


import com.google.gson.annotations.SerializedName;

public class UserDetailsDataModel{
	@SerializedName("id")
	private int id;

	@SerializedName("user_name")
	private String userName;


	@SerializedName("user_address")
	private String userAddress;

	@SerializedName("user_city")
	private String userCity;


	@SerializedName("user_province")
	private String userProvince;

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_password")
	private String userPassword;

	@SerializedName("user_phone")
	private String userPhone;

	public UserDetailsDataModel(String userName, String userAddress, String userCity, String userProvince, String userEmail, String userPassword, String userPhone) {
		this.userName = userName;
		this.userAddress = userAddress;
		this.userCity = userCity;
		this.userProvince = userProvince;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
	}

	public UserDetailsDataModel(int id, String userName, String userAddress, String userCity, String userProvince, String userEmail, String userPassword, String userPhone) {
		this.id = id;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userCity = userCity;
		this.userProvince = userProvince;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
	}

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserProvince(String userProvince){
		this.userProvince = userProvince;
	}

	public String getUserProvince(){
		return userProvince;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}

	public String getUserPhone(){
		return userPhone;
	}

	public void setUserCity(String userCity){
		this.userCity = userCity;
	}

	public String getUserCity(){
		return userCity;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"UserDetailsDataModel{" + 
			"user_email = '" + userEmail + '\'' + 
			",user_password = '" + userPassword + '\'' + 
			",user_province = '" + userProvince + '\'' + 
			",user_name = '" + userName + '\'' + 
			",user_address = '" + userAddress + '\'' + 
			",user_phone = '" + userPhone + '\'' + 
			",user_city = '" + userCity + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}