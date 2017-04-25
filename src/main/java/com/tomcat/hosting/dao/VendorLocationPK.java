package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the CONFIGURATION_COMPONENT database table.
 * 
 */
@Embeddable
public class VendorLocationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", unique=true, nullable=false)
	private int userId;

	@Column(name="zipcode", unique=true, nullable=false)
	private int zipcode;

	public VendorLocationPK() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		result = prime * result + zipcode;
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
		VendorLocationPK other = (VendorLocationPK) obj;
		if (userId != other.userId)
			return false;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}

	
}