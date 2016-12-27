package com.revature.middle;

import javax.naming.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.beans.User;
import com.revature.data.DataFacade;

public class UserService {

	
	private static UserService INSTANCE = null;
	
	UserService(){}
	
	synchronized public static UserService getInstance(){
		if(INSTANCE == null)
			INSTANCE = new UserService();
		return INSTANCE;
	}
	
	User authenticate(String username, String password) throws AuthenticationException{
		
		DataFacade facade = new DataFacade();
		User user = null;
		String hash = facade.getHash(username);
		if(hash == null || !BCrypt.checkpw(password, hash)){ 
			System.out.println("User login failed");
			throw new AuthenticationException();
		}else{
			user = facade.getUser(username);
			System.out.println("User login successful");
		}
		return user;
	}

}
