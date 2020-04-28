/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author crowly
 *
 */
public class PetDbUtil {
	private DataSource dataSource;

	public PetDbUtil(DataSource dataSource) {
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
	 * Returns a list of all available pets in the game.
	 * 
	 * @return List of all pets. Null if none (it would be really weird
	 * if this returns null...)
	 */
	public List<Pet> getListAllPets() throws Exception {
		List<Pet> pets = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt  = null;
		ResultSet myRs    = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * FROM pets";
			myStmt = myConn.createStatement();	
			myRs = myStmt.executeQuery(sql);
		
			while (myRs.next()) {				
				Pet tempPet = new Pet(myRs.getInt("id"),
									  myRs.getString("name"),
									  myRs.getString("health_type"),
									  myRs.getString("image"));
				
				pets.add(tempPet);
			}
			
			return pets;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Returns the pet associated with the given name.
	 * 
	 * @param petName
	 * @return Pet object if it is found. False otherwise.
	 * @throws Exception
	 */
	public Pet getPetFromName(String petName) throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get the pet
			String sql = "SELECT * FROM pets WHERE name=?";
			
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
								 myRs.getString("image"));
			
			return thePet;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * Adds a fresh level 1 pet of the given type to the given player account.
	 * 
	 * @param theAccount
	 * @param thePet
	 * @throws Exception
	 */
	public void addPetToAccount(Account theAccount, Pet thePet) throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO player_pets " +
						 "(pet_id, account_id) " +
						 "VALUES (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, thePet.getId());
			myStmt.setInt(2, theAccount.getId());
			
			// execute sql insert
			myStmt.execute();	
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}	
	}
}
