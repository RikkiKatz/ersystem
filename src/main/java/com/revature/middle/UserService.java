package com.revature.middle;

import java.sql.SQLException;

import javax.naming.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.beans.User;
import com.revature.data.DataFacade;

public class UserService {

	public User authenticate(String username, String password) throws AuthenticationException, SQLException {
		DataFacade dataTier = new DataFacade();
		User user = dataTier.getUserLoginInfo(username, password);
	
		if(user == null || ! BCrypt.checkpw(password, user.getPassword())){
			throw new AuthenticationException("Unable to sign in.");
			// redirect to login page
		}
		
		user.setPassword(null);
		return user;
	}

}
