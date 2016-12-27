package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.ers.ServiceLocator;
/**
 * Test ReimbursementDAO methods
 * @author Rikki
 *
 */
public class ReimbDAOImpl {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// get connection
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		
		// Test ReimbursementDAO
		ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
		
		/* Insert new Reimbursement
		Reimbursement reimb = new Reimbursement(5, 100.00,null,null,null,,null,null,null);
		*/
		//Reimbursement reimb = new Reimbursement(2, 5, null, null,null,null,null,null,null);
		/*UserDAO userDao = new UserDAO(conn);
		User user= new User();
		user = userDao.getUserLoginInfo("jane");
		System.out.println("ReimbDaoImpl: User Id for getUserLoginInfo" + user.getUser_id());
		
		ReimbType type = reimbDao.getTypeByID(1);
		ReimbStatus status = reimbDao.getStatusByID(1);
		
		reimbDao.insertReimb(user, 100.0, type, status, "testing");*/
		
		
		//reimbDao.getAllReimbs();
		//System.out.println("All reimbs size: " +reimbDao.getAllReimbs().size());
		//reimbDao.getReimbByAuthor(1);
		reimbDao.getReimbByStatus("Approved");
		
		// Test that statuses and types are being retrieved from the database
		//reimbDao.getStatus();
		//reimbDao.getTypes();
	

		//Close connection, check that entire main method runs
		conn.close();
		System.out.println("Finished");
	}
}
