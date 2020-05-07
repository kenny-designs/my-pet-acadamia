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
public class AccountDbUtil extends DbUtil {
	public AccountDbUtil(DataSource dataSource) {
		super(dataSource);
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
											 myRs.getInt("battles_lost"),
											 myRs.getInt("safari_battles_won"),
											 myRs.getInt("safari_battles_lost"));			
			
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
											 myRs.getInt("battles_lost"),
											 myRs.getInt("safari_battles_won"),
											 myRs.getInt("safari_battles_lost"));			
			
			return theAccount;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * Returns an Account object based on the given id.
	 * 
	 * @param id
	 * @return Account from id.
	 */
	public Account getAccountFromId(int id) throws Exception {
		Connection myConn 		 = null;
		PreparedStatement myStmt = null;
		ResultSet myRs 			 = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected user
			String sql = "SELECT * FROM accounts WHERE id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no account found, throw exception
			if (!myRs.next()) {
				throw new Exception("No account with id " + id + " found!");
			};
		
			// account found, return it	
			Account theAccount = new Account(myRs.getString("username"),
											 null,
											 id,
											 myRs.getInt("battles_won"),
											 myRs.getInt("battles_lost"),
											 myRs.getInt("safari_battles_won"),
											 myRs.getInt("safari_battles_lost"));			
			
			return theAccount;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * The account to update.
	 * 
	 * @param theAccount
	 */
	public void updateAccountStats(Account theAccount) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete the player's pet
			String sql = "UPDATE accounts " +
						 "SET battles_won=?, " +
						 "    battles_lost=?, " +
						 "    safari_battles_won=?, " +
						 "    safari_battles_lost=? " +
						 "WHERE id=?";
						
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, theAccount.getBattlesWon());
			myStmt.setInt(2, theAccount.getBattlesLost());
			myStmt.setInt(3, theAccount.getSafariBattlesWon());
			myStmt.setInt(4, theAccount.getSafariBattlesLost());
			myStmt.setInt(5, theAccount.getId());
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}
}
