package com.revature.middle;

import java.sql.SQLException;

import javax.naming.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.beans.User;
import com.revature.data.DataFacade;

public class UserService {

	public User authenticate(String username, String password) throws AuthenticationException, SQLException {
		DataFacade dataTier = new DataFacade();
		User user = dataTier.getUserLoginInfo(username);
		String hashedPassword = null;
		
		if(user.getPassword()!=null){
			hashedPassword = user.getPassword();
		}
		
		if(user == null || ! BCrypt.checkpw(password, hashedPassword)){
			throw new AuthenticationException("Unable to sign in.");
		}

		System.out.println(username+ " " +user.getPassword());
		user.setPassword(null);
		return user;
	}

}
