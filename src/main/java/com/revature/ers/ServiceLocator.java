package com.revature.ers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Service Locator Design Pattern:
 * Lookup and cache services. 
 * Reduces performance overhead of looking up services.
 * 
 * @author Rikki
 *
 */
public class ServiceLocator {

	private static DataSource ers;
	private static Properties env;	// environment properties
	
	// Used to initialize static variables
	// executed when the class is loaded into the JVM
	static {
		// load properties from 
		InputStream stream = 
				ServiceLocator.class.getClassLoader().getResourceAsStream("ers.properties");
		env = new Properties();
		try {
			// loads properties from file
			env.load(stream);
		} catch (IOException e) {}		
	}
	
	/**
	 * Singleton
	 * @return
	 * @throws NamingException 
	 */
	public synchronized static DataSource getERSDatabase() {
		if(ers == null)
			ers = lookupERS();
		return ers;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException 
	 */
	private static DataSource lookupERS() {
		
		Context ctxt;
		try {
			ctxt = new InitialContext(env);
			DataSource ds = (DataSource) 
					ctxt.lookup(env.getProperty("ersdb"));
			return ds;
		} catch (NamingException e) {
			e.printStackTrace();
			return null; 	// data source could not be found
		}
	}
	
}

