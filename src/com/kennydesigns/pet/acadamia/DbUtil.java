/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @author crowly
 *
 * Parent class for all database utility classes.
 */
public class DbUtil {	
	public DataSource dataSource;
	
	public DbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs   != null)   myRs.close();
			if (myStmt != null) myStmt.close();
			if (myConn != null) myConn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
