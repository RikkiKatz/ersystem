package com.revature.middle;

import java.sql.SQLException;

import javax.naming.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.beans.User;
import com.revature.data.DataFacade;

public class UserService {

	/**
	 * Authenticate existence of username and password combo
	 * @param username
	 * @param password
	 * @return
	 * @throws AuthenticationException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public User authenticate(String username, String password) throws AuthenticationException, SQLException {
		DataFacade dataTier = new DataFacade();
		User user = dataTier.getUserByName(username);
	
		if(user == null || ! BCrypt.checkpw(password, user.getPassword())){
			throw new AuthenticationException("Wrong password.");
		}
		
		user.setPassword(null);
		return user;
	}

}
