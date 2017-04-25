package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")

@NamedQueries({
@NamedQuery(name="User.findAll", query="SELECT n FROM User n"),
@NamedQuery(name="User.findByRole", query="SELECT n FROM User n where userRole= :role"), 
@NamedQuery(name = "User.findUserById", query = "SELECT n FROM User n where userId= :id") 
})
public class User implements Serializable {
	public static int REGULAR = 0;
	public static int VENDOR = 1;
	public static int ADMIN = 2;
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 256)
	private String userId;

	@Column(name="activation_code")
	private String activationCode;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	

	@Column(name="email_address")
	private String emailAddress;

	@Column(name="invalid_login")
	private int invalidLogin;

	private String password;


	@Column(name="signup_time")
	private Timestamp signupTime;


	private int status;

	@Column(name="user_role")
	private int userRole;
	
	@Column(name="company_id")
	private int companyId;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getInvalidLogin() {
		return this.invalidLogin;
	}

	public void setInvalidLogin(int invalidLogin) {
		this.invalidLogin = invalidLogin;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Timestamp getSignupTime() {
		return this.signupTime;
	}

	public void setSignupTime(Timestamp signupTime) {
		this.signupTime = signupTime;
	}

	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserRole() {
		return this.userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}