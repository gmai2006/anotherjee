package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
/**
 * The persistent class for the ZIPCODEGEO database table.
 * 
 */
@Entity
@Table(name="ZIPCODEGEO")

@NamedQueries({
@NamedQuery(name="ZipcodeGeo.findAll", query="SELECT z FROM ZipcodeGeo z"),
@NamedQuery(name="ZipcodeGeo.findByZipcode", query="SELECT v FROM ZipcodeGeo v where zipcode= :zipcode") 
})
public class ZipcodeGeo implements Serializable {
	private static final long serialVersionUID = 12L;

	@Id
	@Column(name = "zipcode", unique = true, nullable = false)
	private int zipcode;
	private double lat;
	private double lng;
	private String address;
	public ZipcodeGeo() {
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ZipcodeGeo other = (ZipcodeGeo) obj;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}



	public int getZipcode() {
		return zipcode;
	}



	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
}