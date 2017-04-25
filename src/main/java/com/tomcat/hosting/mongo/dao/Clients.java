package com.tomcat.hosting.mongo.dao;


import net.sf.jsefa.csv.annotation.CsvField;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity(value = "clients", noClassnameStored = true)
public class Clients  {
	@Id 
	private ObjectId id;
	
	@CsvField(pos = 2)
	private String name;
	
	
	@CsvField(pos = 12)
	private int status;

	@Embedded
	private Address addresses;

	private ObjectId vendorId;
	
	public Clients() {
	}

	
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Clients [id=" + id + ", name=" + name
				+ ", status=" + status + ", addresses=" + addresses + "]";
	}


	public Address getAddresses() {
		return addresses;
	}


	public void setAddresses(Address addresses) {
		this.addresses = addresses;
	}


	public ObjectId getVendorId() {
		return vendorId;
	}


	public void setVendorId(ObjectId vendorId) {
		this.vendorId = vendorId;
	}

	
}