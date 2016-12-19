package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.beans.UserRole;

/**
 * Methods: insert(), update(), delete(), getAll()
 * getReimbByStatus(), getTypes(), getStatus(), mapRows()
 * TODO: getByUser()
 * @author Rikki
 *
 */
public class ReimbursementDAO {

	private Connection conn;
	
	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public Reimbursement insertReimb(User author, double amount, 
			ReimbType type, ReimbStatus status, String description) throws SQLException {	
		System.out.println("ReimbDao: reached insertReimb()");
		
		String sql = "INSERT INTO ERS_REIMBURSEMENT("
				+ " REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED,"
				+ " REIMB_DESCRIPTION, REIMB_AUTHOR,"
				+ " REIMB_STATUS_ID, REIMB_TYPE_ID)"
				+ " VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"REIMB_ID"});
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		int reimb_id = 0;
		
		reimb_id = new ReimbursementDAO(conn).getAllReimbs().size()+1;
		stmt.setInt(1, reimb_id);
		stmt.setDouble(2, amount);
		stmt.setTimestamp(3, ts);
		stmt.setString(4, description);
		stmt.setInt(5, author.getUser_id());
		stmt.setInt(6, 1);
		stmt.setInt(7, type.getType_id());
		stmt.executeQuery();
		
		System.out.println("ReimbDao: insertReimb: do we get here before execute?");		
		
		Reimbursement reimb = new Reimbursement(reimb_id, amount, 
				ts, null, description, author, null, status, type);
		System.out.println("ReimbDao: insertReimb(): " + reimb);
		return reimb;
	}
	
	
	public List<Reimbursement> getAllReimbs() throws SQLException {
		List<Reimbursement> list = new ArrayList<>();
			String sql = "SELECT REIMB_ID, REIMB_AMOUNT,"
					 + " s.REIMB_STATUS_ID, s.REIMB_STATUS, t.REIMB_TYPE_ID, t.REIMB_TYPE,"
					 + " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
					 + " u.ERS_USERS_ID, u.ERS_USERNAME, u.USER_FIRST_NAME, u.USER_LAST_NAME, u.USER_EMAIL"
					+ " FROM ERS_REIMBURSEMENT r"
					  + " JOIN ERS_REIMBURSEMENT_TYPE t"
					  + " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID"
					    + " JOIN ERS_REIMBURSEMENT_STATUS s"
					    + " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID"
					      + " LEFT JOIN ERS_USERS u"
					      + " ON r.REIMB_RESOLVER = u.ERS_USERS_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapReimbs(rs, list, false);
		System.out.println("getAllReimbs(): List: " +list);
		System.out.println("List Size: " +list.size());
		return list;
	}
	
	public List<Reimbursement> getReimbByAuthor(int author_id) throws SQLException {
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT,"
					+ " s.REIMB_STATUS_ID, s.REIMB_STATUS, t.REIMB_TYPE_ID, t.REIMB_TYPE,"
					+ " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
					+ " u.ERS_USERS_ID AS RESOLVER_USERS_ID,"
					+ " u.ERS_USERNAME AS RESOLVER_USERNAME,"
					+ " u.USER_FIRST_NAME AS RESOLVER_FIRST_NAME,"
					+ " u.USER_LAST_NAME AS RESOLVER_LAST_NAME,"
					+ " u.USER_EMAIL AS RESOLVER_EMAIL"
				+ " FROM ERS_REIMBURSEMENT r"
					+ " JOIN ERS_REIMBURSEMENT_TYPE t"
					+ " ON r.REIMB_TYPE_ID = t.REIMB_TYPE_ID"
						+ " JOIN ERS_REIMBURSEMENT_STATUS s"
						+ " ON r.REIMB_STATUS_ID = s.REIMB_STATUS_ID"
							+ " Left JOIN ERS_USERS u"
							+ " ON r.REIMB_RESOLVER = u.ERS_USERS_ID"
				+ " WHERE r.REIMB_AUTHOR = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, author_id);
		ResultSet rs = stmt.executeQuery();
		mapReimbs(rs, results, true);
		System.out.println(results);
		return results;
	}
	
	private void mapReimbs(ResultSet rs, List<Reimbursement> list, boolean isAuthor) throws SQLException{
		int id; 
		double amount;
		User author;
		User resolver;
		ReimbStatus status;
		ReimbType type;
		String description;
		Timestamp submitted, resolved;
		Reimbursement reimb;
		UserDAO userDao = new UserDAO(conn);
		System.out.println("Map reimbs(): isAuthor?: "+ isAuthor);
		if(isAuthor){
			while(rs.next()){
				System.out.println("Reached mapReimbs() yes author?");
				id = rs.getInt("REIMB_ID");
				amount = rs.getDouble("REIMB_AMOUNT");
				author = null;
				System.out.println("Is resolver the prob?");
				resolver = userDao.setUser(rs, false);
				System.out.println("Is resolver not the prob?");
				status = new ReimbStatus(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
				type = new ReimbType(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
				description = rs.getString("REIMB_DESCRIPTION");
				submitted = rs.getTimestamp("REIMB_SUBMITTED");
				resolved = rs.getTimestamp("REIMB_RESOLVED");
				reimb = new Reimbursement(id, amount, submitted, resolved, description, author, resolver, status, type);
				System.out.println("Is author: " +reimb);
				list.add(reimb);
			}
		}else{
			while(rs.next()){

				System.out.println("Reached mapReimbs() not author?");
				id = rs.getInt("REIMB_ID");
				amount = rs.getDouble("REIMB_AMOUNT");
				System.out.println("Is set author the prob?");
				author = userDao.setUser(rs, true);
				System.out.println("Is set resolver the prob?");
				resolver = userDao.setUser(rs, false);
				System.out.println("Not the prob?");
				status = new ReimbStatus(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
				type = new ReimbType(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
				description = rs.getString("REIMB_DESCRIPTION");
				submitted = rs.getTimestamp("REIMB_SUBMITTED");
				resolved = rs.getTimestamp("REIMB_RESOLVED");
				reimb = new Reimbursement(id, amount, submitted, resolved, description, author, resolver, status, type);
				System.out.println("Is author: " +reimb);
				list.add(reimb);
			} 
		}
	}

	
	
/**
	public List<Reimbursement> getReimbForResolver() throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = ("select"
				+ " ERS_REIMBURSEMENT.REIMB_AMOUNT,"
				+ " ERS_REIMBURSEMENT.REIMB_DESCRIPTION,"
				+ " ERS_REIMBURSEMENT.REIMB_ID,"
				+ " ERS_REIMBURSEMENT.REIMB_RESOLVED,"
				+ " ERS_REIMBURSEMENT.REIMB_SUBMITTED,"
					+ " users.ERS_USERS_ID,"
					+ " users.ERS_USERNAME,"
					+ " users.ERS_PASSWORD,"
				    + " users.USER_FIRST_NAME,"
				    + " users.USER_LAST_NAME,"
					+ " users.USER_EMAIL,"
					+ " users.USER_ROLE_ID,"
						+ " resolvers.ERS_USERS_ID,"
						+ " resolvers.ERS_USERNAME,"
						+ " resolvers.ERS_PASSWORD,"
						+ " resolvers.USER_FIRST_NAME,"
						+ " resolvers.USER_LAST_NAME,"
						+ " resolvers.USER_EMAIL,"
						+ " resolvers.USER_ROLE_ID,"
							+ " s.REIMB_STATUS_ID,"
							+ " s.REIMB_STATUS,"
					           + " t.REIMB_TYPE_ID,"
					           + " t.REIMB_TYPE,"
									+ " u.ERS_USER_ROLE_ID,"
									+ " u.USER_ROLE,"
							          + " r.ERS_USER_ROLE_ID,"
							            + " r.USER_ROLE"
				+ " from ERS_REIMBURSEMENT"
				  + " inner join ERS_USERS users"
				  + " on users.ERS_USERS_ID = ERS_REIMBURSEMENT.REIMB_AUTHOR"
				    + " left join ERS_USERS resolvers"
				    + " on resolvers.ERS_USERS_ID = ERS_REIMBURSEMENT.REIMB_RESOLVER"
					    + " inner join ERS_REIMBURSEMENT_STATUS s"
					    + " on s.REIMB_STATUS_ID = ERS_REIMBURSEMENT.REIMB_STATUS_ID"
						    + " inner join ERS_REIMBURSEMENT_TYPE t"
						    + " on t.REIMB_TYPE_ID = ERS_REIMBURSEMENT.REIMB_TYPE_ID"
							      + " inner join ERS_USER_ROLES u"
							      + " on u.ERS_USER_ROLE_ID = users.USER_ROLE_ID"
									+ " left join ERS_USER_ROLES r"
									+ " on r.ERS_USER_ROLE_ID = resolvers.USER_ROLE_ID");
								
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapResolver(rs,results);
		return results;	
	}
	
	
	private void mapResolver(ResultSet rs, List<Reimbursement> results) throws SQLException {
		while(rs.next()){
			UserRole authorRole = new UserRole(
					rs.getInt("userroleID"), rs.getString("userRole"));
			UserRole resolverRole = new UserRole(
					rs.getInt("resolverroleID"), rs.getString("resolverRole"));
			User author = new User(
					rs.getInt("AuthorID"), rs.getString("AuthorUn"), rs.getString("AuthoerPw"), 
					rs.getString("AuthorFn"), rs.getString("AuthorLn"), rs.getString("AuthorEm"), authorRole);
			User resolver = new User(
					rs.getInt("ResolverID"), rs.getString("ResolverUn"), rs.getString("ResolverPw"), 
					rs.getString("ResolverFn"), rs.getString("ResolverLn"), rs.getString("ResolverEm"), resolverRole);
			ReimbStatus authorStatus = new ReimbStatus(
					rs.getInt("statusID"), rs.getString("status"));	
			ReimbType type = new ReimbType(
					rs.getInt("typeID"), rs.getString("type"));
	
			Reimbursement obj = new Reimbursement(rs.getInt("REIMB_ID"), 
												rs.getInt("REIMB_AMOUNT"), 
												rs.getTimestamp("REIMB_SUBMITTED"), 
												rs.getTimestamp("REIMB_RESOLVED"), 
												rs.getString("REIMB_DESCRIPTIONS"), 
												author, resolver, authorStatus, type);
			results.add(obj);
		
		}
		
	}
**/
	public void update(Reimbursement reimb) throws SQLException {	
		String sql = "UPDATE REIMBURSMENT"
					+ " SET REIMB_STATUS_ID = ?, REIMB_RESOLVED = ?, REIMB_RESOLVER = ?"
					+ " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getStatus_id().status_id);
		stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
		stmt.setInt(3, reimb.getReimb_id());
		stmt.setInt(4, reimb.getReimb_id());
		stmt.executeUpdate();
	}

	private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {
		while(rs.next()) {
			int reimb_id = rs.getInt("reimb_id");
			Double amount = rs.getDouble("reimb_amount");
			Date date_submitted = rs.getDate("reimb_submitted");
			Date date_resolved = rs.getDate("reimb_resolved");
			String description = rs.getString("reimb_decription");
			
			User author = new User();
			author.setUser_id(rs.getInt("reimb_author"));
			
			User resolver = new User();
			resolver.setUser_id(rs.getInt("reimb_resolver"));
			
			ReimbStatus status_id = new ReimbStatus();
			status_id.setStatus_id(rs.getInt("reimb_status_id"));
			
			ReimbType type_id = new ReimbType();
			type_id.setType_id(rs.getInt("reimb_type_id"));
			
			Reimbursement obj = new Reimbursement(reimb_id, amount, date_submitted, date_resolved, 
									description, author, resolver, status_id, type_id);
			results.add(obj);
		}
	}
	
	public List<Reimbursement> getReimbByStatus(String reimb_status) throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = "select ers_reimbursement.reimb_status_id"
					+ " from ers_reimbursement_status"
						+ " inner join ers_reimbursement"
						+ " on ers_reimbursement_status.reimb_status_id = ers_reimbursement.reimb_status_id"
					+ " where reimb_status like '%" + reimb_status + "%'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}

	public List<ReimbStatus> getStatus() throws SQLException {
		List<ReimbStatus> results = new ArrayList<>();
		String sql="SELECT REIMB_STATUS_ID, REIMB_STATUS"
					+ " FROM ERS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			int status_id = rs.getInt("REIMB_STATUS_ID");
			String status = rs.getString("REIMB_STATUS");
			ReimbStatus obj = new ReimbStatus(status_id, status);
			results.add(obj);
		}
		System.out.println(results);
		return results;
	}

	public List<ReimbType> getTypes() throws SQLException{
		List<ReimbType> results = new ArrayList<>();
		String sql= "SELECT REIMB_TYPE_ID, REIMB_TYPE"
					+ " FROM ERS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();		
		while(rs.next()) {
			int type_id = rs.getInt("REIMB_TYPE_ID");
			String type = rs.getString("REIMB_TYPE");
			ReimbType obj = new ReimbType(type_id, type);
			results.add(obj);
		}
		System.out.println("ReimbDAO: getTypes: " + results);
		return results;
	}

	public ReimbType getTypeByID(int type_id) throws SQLException {
	
		String reimb_type=null;
		String sql =  "SELECT REIMB_TYPE"
				+ " FROM ERS_REIMBURSEMENT_TYPE"
				+ " WHERE REIMB_TYPE_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
	
		stmt.setInt(1, type_id);
		ResultSet rs = stmt.executeQuery();		
		if (rs.next()) {
			reimb_type=rs.getString("REIMB_TYPE");
		}
		ReimbType type = new ReimbType(type_id, reimb_type);
		System.out.println("ReimbDAO: getTypeByID(): " + type);
		return type;
	}
	
	public ReimbStatus getStatusByID(int status_id) throws SQLException {
		String reimb_status=null;
		
		String sql =  "SELECT REIMB_STATUS"
				+ " FROM ERS_REIMBURSEMENT_STATUS"
				+ " WHERE REIMB_STATUS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
	
		stmt.setInt(1, status_id);
		ResultSet rs = stmt.executeQuery();		
		if (rs.next()) {
			reimb_status=rs.getString("REIMB_STATUS");
		}
		ReimbStatus status = new ReimbStatus(status_id, reimb_status);
		System.out.println("ReimbDAO: getTypeByID(): " + status);
		return status;
	}
	
	/**
	 * Delete a reimbursement record
	 * @param reimb
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void delete (Reimbursement reimb) throws SQLException {		
		String sql = "DELETE FROM REIMBURSMENT WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getReimb_id());
		stmt.executeUpdate();
	}
}
