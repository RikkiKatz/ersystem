package com.revature.beans;
/**
 * ERS Reimbursement Type Bean
 * @author Rikki
 *
 */
public class ReimbType {
	public int type_id;
	public String type;
	
	@Override
	public String toString() {
		return "ReimbType [type_id=" + type_id + ", type=" + type + "]";
	}
	
	public ReimbType(int type_id, String type) {
		super();
		this.type_id = type_id;
		this.type = type;
	}
	
	public ReimbType() {
		super();
	}
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}