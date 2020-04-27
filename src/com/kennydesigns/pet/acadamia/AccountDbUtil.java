/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
											 myRs.getInt("id"));			
			
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
						 "values (?, ?)";
			
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
}
