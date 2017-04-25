package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
/**
 * The persistent class for the VENDOR_LOCATION database table.
 * 
 */
@Entity
@Table(name="VENDOR_LOCATION")

@NamedQueries({
@NamedQuery(name="Location.findAll", query="SELECT v FROM VendorLocation v"),
@NamedQuery(name="Location.findByUser", query="SELECT v FROM VendorLocation v where id.userId= :id") 
})
public class VendorLocation implements Serializable {
	private static final long serialVersionUID = 11L;

	@Id
	@EmbeddedId
	private VendorLocationPK id;

	private String address;
	private String city;
	private String country;
	private String state;
	

	public VendorLocation() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
}