/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @author crowly
 * Handles battle database related work.
 */
public class BattleDbUtil {
	private DataSource dataSource;

	public BattleDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// TODO: make database parent class
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
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
	 * Checks if the given account currently has an ongoing safari battle.
	 * 
	 * @param theAccount
	 * @return True is the account is in a battle. False otherwise.
	 */
	public boolean isAccountInSafari(Account theAccount) {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {			
			/*
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to check if the account has a battle
			String sql = "SELECT * FROM safari_battle_instances WHERE player=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setString(1, petName);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no pet found, return null
			if (!myRs.next()) return null;
		
			// pet found, return it	
			Pet thePet = new Pet(myRs.getInt("id"),
								 myRs.getString("name"),
								 myRs.getString("health_type"),
								 myRs.getString("image"),
								 myRs.getString("description"));
			
			return thePet;
			*/
			
			return true;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
}
