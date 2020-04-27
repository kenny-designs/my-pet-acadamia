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

	/**
	 * Attempts to log the user into their account.
	 * 
	 * @param theAccount
	 * @return True if successful. False otherwise.
	 * @throws Exception
	 */
	public boolean loginAccount(Account theAccount) throws Exception {	
		Connection myConn 		 = null;
		PreparedStatement myStmt = null;
		ResultSet myRs 			 = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "SELECT * FROM accounts WHERE username=? AND password=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setString(1, theAccount.getUsername());
			myStmt.setString(2, theAccount.getPassword());
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no student found, return false
			if (!myRs.next()) return false;
		
			// student found, get their id and return true
			int id = myRs.getInt("id");
			theAccount.setId(id);
			return true;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
}
