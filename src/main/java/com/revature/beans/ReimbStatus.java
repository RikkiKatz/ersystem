package com.revature.beans;
/**
 * ERS Reimbursement Status Bean
 * @author Rikki
 *
 */
public class ReimbStatus {
	
	public int status_id;
	public String status;
	
	@Override
	public String toString() {
		return "ReimbStatus [status_id=" + status_id + ", status=" + status + "]";
	}
	
	public ReimbStatus(int status_id, String status) {
		super();
		this.status_id = status_id;
		this.status = status;
	}
	
	public ReimbStatus() {
		super();
	}
	
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}