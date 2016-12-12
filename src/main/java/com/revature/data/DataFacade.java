package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.ers.ServiceLocator;
/**
 * Facade 
 * @author Rikki
 *
 */
public class DataFacade {

	/**
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public User getUserByName(String username) throws SQLException {
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		UserDAO dao = new UserDAO(conn);
		
		conn.close();
		return dao.getByUsername(username);
	}

	/**
	 * Get Reimb Types
	 * @return
	 * @throws SQLException
	 */
	public List<String> getTypes() throws SQLException {
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		conn.close();
		return new ReimbursementDAO(conn).getTypes();
	}

	/**
	 * Insert new Reimbursement
	 * @param reimb
	 * @throws SQLException
	 */
	public void insertReimbursement(Reimbursement reimb) throws SQLException{
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		dao.insert(reimb);
		conn.close();
	}

	public void updateReimbursement(Reimbursement reimb) throws SQLException {
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		dao.update(reimb);
		conn.close();
	}
	
}
