package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.ers.ServiceLocator;
/**
 * Facade 
 * @author Rikki
 *
 */
public class DataFacade {
	
	private static Connection getConnection() throws SQLException {
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public User getUserLoginInfo(String username) throws SQLException {
		Connection conn = getConnection();
		User user = new UserDAO(conn).getUserLoginInfo(username);
		conn.close();
		return user;
	}

	public List<ReimbType> getTypes() throws SQLException {
		Connection conn = getConnection();
		List<ReimbType> list = new ReimbursementDAO(conn).getTypes();
		conn.close();
		System.out.println("DataFacade: getTypes(): " + list);
		return list;
	}
	
	public List<ReimbStatus> getStatus() throws SQLException {
		Connection conn = getConnection();
		List<ReimbStatus> list = new ReimbursementDAO(conn).getStatus();
		conn.close();
		return list;
	}

	public Reimbursement insertReimb(User author, double amount, 
					ReimbType type,ReimbStatus status, String description){
		Connection conn = null;
		try {
			conn = getConnection();
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			Reimbursement reimb = dao.insertReimb(author, amount, type, status, description);
			conn.commit();
			return reimb;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateReimbursement(Reimbursement reimb) throws SQLException {
		Connection conn = null;
		try{	
			conn = getConnection();
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			dao.update(reimb);
			conn.commit();
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Reimbursement> getAllReimbs() throws Exception{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
			List<Reimbursement> list = reimbDao.getAllReimbs();
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new Exception();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<Reimbursement> getReimbByAuthor(int author_id) throws Exception{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimbursementDAO reimbDao = new ReimbursementDAO(conn);
			List<Reimbursement> list = reimbDao.getReimbByAuthor(author_id);
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new Exception();			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
