package com.tomcat.hosting.dao;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the CONFIGURATION_COMPONENT database table.
 * 
 */
@Embeddable
public class VendorClientPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 155L;

	@Column(name="vendor_id", unique=true, nullable=false)
	private int vendorId;

	@Column(name="client_id", unique=true, nullable=false)
	private int clientId;

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + vendorId;
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
		VendorClientPK other = (VendorClientPK) obj;
		if (clientId != other.clientId)
			return false;
		if (vendorId != other.vendorId)
			return false;
		return true;
	}

	
}