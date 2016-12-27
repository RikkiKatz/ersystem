package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public static Reimbursement getReimbById(int reimb_id) throws SQLException {
		Connection conn = getConnection();
		Reimbursement reimb = new ReimbursementDAO(conn).getReimbById(reimb_id);
		conn.close();
		System.out.println("DataFacade: getReimbById(): " + reimb);
		return reimb;
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
		System.out.println("DataFacade: getStatus(): " + list);
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

	public void updateStatus(Reimbursement reimb, User user, ReimbStatus status) throws Exception{
		if(!user.getRole_id().getUser_role().equals("Manager")){
			throw new Exception();
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		updateStatus(reimb.getId(), user.getUser_id(), status.getStatus_id(), ts);
		reimb.setStatus_id(new ReimbStatus(status.getStatus_id(), status.getStatus()));
		reimb.setDate_resolved(ts);
		reimb.setResolver_id(user);
		
	}

	private void updateStatus(int reimb_id, int resolver, int status_id, Timestamp ts) {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			dao.updateStatus(reimb_id, resolver, status_id, ts);
			conn.commit();		
		}catch(SQLException e) {
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
	public String getHash(String username) {
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			return hashedPassword;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User getUser(String username){
		User user = null;
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			user = dao.getUser(username);
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
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
