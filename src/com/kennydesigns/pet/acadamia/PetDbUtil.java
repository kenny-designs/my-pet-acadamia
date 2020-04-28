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
									  myRs.getString("image"),
									  myRs.getString("description"));
				
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
								 myRs.getString("image"),
								 myRs.getString("description"));
			
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

	/**
	 * Return a list of all of the pets belonging to the currently logged in account.
	 * 
	 * @param theAccount
	 * @return
	 */
	public List<PlayerPet> getAccountPlayerPets(Account theAccount) 
			throws Exception {
		List<PlayerPet> playerPets = new ArrayList<>();

		Connection myConn        = null;
		PreparedStatement myStmt = null;
		ResultSet myRs           = null;

		try {
			myConn = dataSource.getConnection();

			// selects all pets that the account owns
			String sql = "SELECT player_pets.*, pets.name " +
						 "FROM player_pets " +
						 "	INNER JOIN accounts " +
						 "	ON player_pets.account_id = accounts.id " +
						 "	INNER JOIN pets " +
						 "	ON player_pets.pet_id = pets.id " +
						 "WHERE accounts.id = ? " +
						 "ORDER BY pets.id";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setInt(1, theAccount.getId());

			// execute statement
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				// get the associated pet
				Pet tempPet = getPetFromName(myRs.getString("name"));

				// create a new player pet object
				PlayerPet tempPlayerPet = new PlayerPet(myRs.getInt("id"),
													    myRs.getInt("level"),
													    myRs.getInt("exp"),
													    myRs.getBoolean("is_team"),
													    tempPet,
													    theAccount);
					
				// add the pet to the list of the account's pets
				playerPets.add(tempPlayerPet);
			}

			return playerPets;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Delete the player's pet based on the given id.
	 * 
	 * @param playerPetId
	 */
	public void deletePlayerPet(int playerPetId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {	
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete the player's pet
			String sql = "DELETE FROM player_pets WHERE id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, playerPetId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}
}
