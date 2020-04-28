/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @author crowly
 * Manages accounts on the database.
 */
public class AccountDbUtil {
	private DataSource dataSource;

	public AccountDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
		try {
			if (myRs   != null)   myRs.close();
			if (myStmt != null) myStmt.close();
			if (myConn != null) myConn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Attempt to log the user in.
	 * 
	 * @param username
	 * @param password
	 * @return Account with the given username and password. Null if there is none.
	 * @throws Exception
	 */
	public Account loginAccount(String username, String password) throws Exception {
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
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no account found, return null
			if (!myRs.next()) return null;
		
			// account found, return it	
			Account theAccount = new Account(myRs.getString("username"),
											 myRs.getString("password"),
											 myRs.getInt("id"),
											 myRs.getInt("battles_won"),
											 myRs.getInt("battles_lost"));			
			
			return theAccount;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	/**
	 * Retrieves the cookie associated with the logged in user.
	 * 
	 * @param request
	 * @return Cookie for the logged in account. Null if none found.
	 */
	public Cookie getLoggedAccountCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();	
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("LOGGED_USER")) return cookie;
		}
	
		return null;
	}

	public boolean addAccount(String username, String password) throws Exception {
		Connection myConn 		 = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO accounts " +
						 "(username, password) " +
						 "VALUES (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			// execute sql insert
			myStmt.execute();
			
			return true;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}	
	}

	/**
	 * Returns an account object based on the given username.
	 * 
	 * @param username
	 * @return Account object based on the given username. Null if not found.
	 */
	public Account getAccount(String username) throws Exception {
		Connection myConn 		 = null;
		PreparedStatement myStmt = null;
		ResultSet myRs 			 = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "SELECT * FROM accounts WHERE username=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setString(1, username);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no account found, return null
			if (!myRs.next()) return null;
		
			// account found, return it	
			Account theAccount = new Account(myRs.getString("username"),
											 null,
											 -1,
											 myRs.getInt("battles_won"),
											 myRs.getInt("battles_lost"));			
			
			return theAccount;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
}
