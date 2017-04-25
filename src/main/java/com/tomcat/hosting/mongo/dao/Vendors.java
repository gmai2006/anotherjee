package com.tomcat.hosting.mongo.dao;


import java.util.List;

import net.sf.jsefa.csv.annotation.CsvField;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity(value = "vendors", noClassnameStored = true)
public class Vendors  {
	@Id 
	private ObjectId id;
	
	@CsvField(pos = 2)
	private String name;
	

	@Property("email_address")
	@CsvField(pos = 6)
	private String emailAddress;

	
	@Property("consumer_rating")
	@CsvField(pos = 10)
	private int consumerRating;

	
	@CsvField(pos = 12)
	private int status;

	@Embedded
	private List<Address> addresses;
	
	private ObjectId clientId;

	public Vendors() {
	}

	
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public int getConsumerRating() {
		return consumerRating;
	}

	public void setConsumerRating(int consumerRating) {
		this.consumerRating = consumerRating;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Vendors [id=" + id + ", name=" + name + ", emailAddress="
				+ emailAddress + ", consumerRating=" + consumerRating
				+ ", status=" + status + ", addresses=" + addresses + "]";
	}


	public ObjectId getClientId() {
		return clientId;
	}


	public void setClientId(ObjectId clientId) {
		this.clientId = clientId;
	}

	
}