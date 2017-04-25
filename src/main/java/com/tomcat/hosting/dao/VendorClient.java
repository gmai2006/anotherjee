package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * The persistent class for the VENDOR_LOCATION database table.
 * 
 */
@Entity
@Table(name="VENDOR_CLIENT")

@NamedQueries({
@NamedQuery(name="VendorClient.findAll", query="SELECT v FROM VendorClient v"),
@NamedQuery(name="VendorClient.findByClient", query="SELECT v FROM VendorClient v where id.clientId= :id"),
@NamedQuery(name="VendorClient.findByVendor", query="SELECT v FROM VendorClient v where id.vendorId= :id")
})
public class VendorClient implements Serializable {
	private static final long serialVersionUID = 11L;

	@Id
	@EmbeddedId
	private VendorClientPK id;
	@Column(name = "assignment_date", nullable = false)
	private Date assignmentDate;
	@Column(name = "job_type", length = 64)
	private String jobType;
	
	private String comment;

	public VendorClient() {
	}

	public VendorClientPK getId() {
		return id;
	}

	public void setId(VendorClientPK id) {
		this.id = id;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}