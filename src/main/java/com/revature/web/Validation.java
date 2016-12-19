package com.revature.web;

import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;

public class Validation {
	
	//Validation
	public static double validateAmount(String amountString) throws Exception{
		double amount;
		try{
			amount = Double.valueOf(amountString);
			if(amount > 0){
				int decimalPoint = amountString.indexOf('.');
				int numDecimals = amountString.length() - decimalPoint -1;
				if(numDecimals > 2){
					throw new Exception();
				}
			}
			return amount;
		}catch(NumberFormatException e){
			throw new Exception();
		}
	}
	
	public static ReimbType validateType(List<ReimbType> list, String typeSelected) throws Exception{
		int typeId = 0;
		String typeName=null;
		for(ReimbType type: list){
			System.out.println("Type:::: " + type.getType()+" Type select: "+typeSelected);
			if(type.getType_id()==(Integer.parseInt(typeSelected))){
				typeId = type.getType_id();
				typeName = type.getType();
			}
		}
		if(typeId == 0)
			throw new Exception();
		return new ReimbType(typeId, typeName);
	}
	public static ReimbStatus setReimbstatus(List<ReimbStatus> list, String statusSelected) throws Exception{
		System.out.println("Reached reimb status");
		int statusId= 1;
		System.out.println(new ReimbStatus(statusId, statusSelected));
		return new ReimbStatus(statusId, statusSelected);
	}
	
	/**	public static ReimbStatus validateStatus(List<ReimbStatus> list, String statusSelected) throws Exception{
		System.out.println("Reached reimb status");
		int statusId= 1;
		for(ReimbStatus status: list){
			System.out.println("Status:::: " + status.getStatus());
			if(status.getStatus_id()==(Integer.parseInt(statusSelected))){
				statusId = status.getStatus_id();
			}
		}
		System.out.println("statusSelected" +statusSelected);
		if(statusId == 0)
			throw new Exception();
		System.out.println("new ReimbStatus(statusId, statusSelected" + new ReimbStatus(statusId, statusSelected));
		return new ReimbStatus(statusId, statusSelected);
	}
	**/
}
