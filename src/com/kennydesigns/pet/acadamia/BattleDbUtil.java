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
			
			return false;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * Creates a new battle pet and adds it to the database.
	 * 
	 * @param playerPet
	 * @return The newly created battle pet.
	 */
	public BattlePet createBattlePet(PlayerPet playerPet) throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO battle_pets " +
						 "(hitpoints, player_pet_id) " +
						 "VALUES (?, ?)";
		
			// return the id of the insertion
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			// get the amount of hitpoints the pet should have
			int hitpoints = getHitpointTotal(playerPet);
			
			// set the param values for battle pet
			myStmt.setInt(1, hitpoints);
			myStmt.setInt(2, playerPet.getId());
			
			// execute sql insert
			myStmt.execute();
			
			// get the id of the inserted battle pet
			myRs = myStmt.getGeneratedKeys();
			
			// if failed, return null
			if (!myRs.next()) return null;
		
			// create battle pet and return
			int battlePetId = myRs.getInt(1);	
			return new BattlePet(battlePetId, hitpoints, playerPet);
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}	
	}

	/**
	 * Gets the total number of hitpoints for the given pet.
	 * 
	 * @param playerPet
	 * @return
	 */
	private int getHitpointTotal(PlayerPet playerPet) throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get the pet
			String sql = "SELECT amount " +
						 "FROM health_types " +
						 "WHERE health_type=? AND level=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			String healthType = playerPet.getPet().getHealthType();
			int level = playerPet.getLevel();
			myStmt.setString(1, healthType);
			myStmt.setInt(2, level);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if nothing was found, throw an exception
			if (!myRs.next()) {
				throw new Exception(
						"Health type " + healthType +
						" of level" + level + " not found!");
			}
			
			// return the amount of health the pet should have
			return myRs.getInt("amount");
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}
}
