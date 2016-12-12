package com.revature.ers;

import org.mindrot.jbcrypt.BCrypt;

public class Authentication {

	public static void main(String[] args) {

		String password="test";
		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
	
		String candidate="test1";
		// Check that an unencrypted password matches one that has
		// previously been hashed
		if (BCrypt.checkpw(candidate, hashed))
		    System.out.println("It matches");
		else
		    System.out.println("It does not match");	
	}
}
