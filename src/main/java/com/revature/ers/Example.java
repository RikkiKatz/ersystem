package com.revature.ers;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.data.ReimbursementDAO;
import com.revature.data.UserDAO;

public class Example {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		
		ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
		UserDAO user = new UserDAO(conn);
		user.getById(1);
		
		
		//Reimbursement reimb = new Reimbursement(5, 100.00,null,null,null,,null,null,null);
		//reimbDao.insert(reimb);
		

		//reimbDao.getTypes();
		
		conn.close();
		System.out.println("Finished");
	}

}
