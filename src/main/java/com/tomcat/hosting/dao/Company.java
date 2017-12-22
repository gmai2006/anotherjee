package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="COMPANY")

@NamedQueries({
@NamedQuery(name="Company.findAll", query="SELECT n FROM Company n"),
@NamedQuery(name="Company.findByType", query="SELECT n FROM Company n where companyType= :type"), 
@NamedQuery(name = "Company.findById", query = "SELECT n FROM Company n where id= :id") 
})
@CsvDataType()
public class Company implements Serializable {
	public static int CLIENT = 1;
	public static int VENDOR = 2;

	private static final long serialVersionUID = 13L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "ein", unique = true, nullable = false)
	@CsvField(pos = 1)
	private int ein;
	
	@Column(name = "name", unique = true, nullable = false)
	@CsvField(pos = 2)
	private String name;
	
	@CsvField(pos = 3)
	private String address;

	@CsvField(pos = 4)
	private String city;

	@CsvField(pos = 5)
	private String country;

	@Column(name="email_address", nullable = false)
	@CsvField(pos = 6)
	private String emailAddress;

	@CsvField(pos = 7)
	private String phone;

	@CsvField(pos = 8)
	private int zipcode;
	
	@CsvField(pos = 9)
	private String state;
	
	@Column(name="consumer_rating")
	@CsvField(pos = 10)
	private int consumerRating;

	@Column(name="company_type")
	@CsvField(pos = 11)
	private Integer companyType;
	
	@CsvField(pos = 12)
	private int status;


	public Company() {
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

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEin() {
		return ein;
	}

	public void setEin(int ein) {
		this.ein = ein;
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

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}

}