package com.management.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INCMNG_REQ")
public class IncomingRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRNS_ID")
	private long transactionId;
	
	@Column(name="TRNS_STS")
	private String status;
	
	@Column(name="OPRTN_TYP")
	private String type;
	
	@Column(name="ERR_MSG")
	private String error;
	
	@Column(name="XML_REQ_STR")
	private String request;
	
	@Column(name="REQ_RCVD_DT")
	private Date requestReceivedDate;

	public long getEmpId() {
		return transactionId;
	}

	public void setEmpId(long empId) {
		this.transactionId = empId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Date getRequestReceivedDate() {
		return requestReceivedDate;
	}

	public void setRequestReceivedDate(Date requestReceivedDate) {	
		this.requestReceivedDate = requestReceivedDate;
	}
}
