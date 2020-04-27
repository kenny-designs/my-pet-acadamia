/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

/**
 * @author crowly
 * Manages accounts on the database.
 */
public class AccountDbUtil {
	private DataSource dataSource;

	public AccountDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	/**
	 * Attempts to add a new account to the database.
	 * 
	 * @param theStudent
	 * @throws Exception
	 */
	public void addAccount(Account theAccount) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO accounts " +
						"(username, password) " +
						"values (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, theAccount.getUsername());
			myStmt.setString(2, theAccount.getPassword());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
		try {
			if (myRs   != null)   myRs.close();
			if (myStmt != null) myStmt.close();

			// Doesn't close the connection, just puts it back in the connection pool
			if (myConn != null) myConn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
