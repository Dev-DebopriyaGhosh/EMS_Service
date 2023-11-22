package com.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REQ_TRCKR")
public class RequestTracker {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_ID")
	private long empId;
	
	@Column(name="REQ_ID")
	private long reqId;
	
	@Column(name="REQ_IDENTIFIER")
	private String requestIdentifier;
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public long getReqId() {
		return reqId;
	}
	public void setReqId(long reqId) {
		this.reqId = reqId;
	}
	public String getRequestIdentifier() {
		return requestIdentifier;
	}
	public void setRequestIdentifier(String string) {
		this.requestIdentifier = string;
	}
}
