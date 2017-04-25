package com.tomcat.hosting.mongo.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "users", noClassnameStored = true)
public class Users {
	@Id 
	private ObjectId id;
	@Property("user_id")
	private String userId;
	private String password;
	@Property("user_role")
	private int userRole;
	@Property("first_name")
	private int firstName;
	@Property("last_name")
	private int lastName;
	@Property("company_name")
	private int companyName;
	@Property("email_address")
	private int emailAddress;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getFirstName() {
		return firstName;
	}
	public void setFirstName(int firstName) {
		this.firstName = firstName;
	}
	public int getLastName() {
		return lastName;
	}
	public void setLastName(int lastName) {
		this.lastName = lastName;
	}
	public int getCompanyName() {
		return companyName;
	}
	public void setCompanyName(int companyName) {
		this.companyName = companyName;
	}
	public int getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(int emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}

