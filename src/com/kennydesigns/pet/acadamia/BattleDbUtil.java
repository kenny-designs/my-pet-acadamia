/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
	 * Creates a new battle pet.
	 * 
	 * @param level
	 * @param hitpoints
	 * @param pet
	 * @return
	 * @throws Exception
	 */
	private BattlePet createBattlePet(int level, int hitpoints, Pet pet)
		throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO battle_pets " +
						 "(hitpoints, level, pet_id) " +
						 "VALUES (?, ?, ?)";
		
			// return the id of the insertion
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set the param values for battle pet
			myStmt.setInt(1, hitpoints);
			myStmt.setInt(2, level);
			myStmt.setInt(3, pet.getId());
			
			// execute sql insert
			myStmt.execute();
			
			// get the id of the inserted battle pet
			myRs = myStmt.getGeneratedKeys();
			
			// if failed, return null
			if (!myRs.next()) {
				throw new Exception("Failed to create new battle pet!");
			};
		
			// create battle pet and return
			int battlePetId = myRs.getInt(1);	
			return new BattlePet(battlePetId, hitpoints, level, pet);
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
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
						 "(hitpoints, player_pet_id, level, pet_id) " +
						 "VALUES (?, ?, ?, ?)";
		
			// return the id of the insertion
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			// get the amount of hitpoints the pet should have
			int hitpoints = getHitpointTotal(playerPet.getPet(), playerPet.getLevel());
			
			// set the param values for battle pet
			myStmt.setInt(1, hitpoints);
			myStmt.setInt(2, playerPet.getId());
			myStmt.setInt(3, playerPet.getLevel());
			myStmt.setInt(4, playerPet.getPet().getId());
			
			// execute sql insert
			myStmt.execute();
			
			// get the id of the inserted battle pet
			myRs = myStmt.getGeneratedKeys();
			
			// if failed, return null
			if (!myRs.next()) {
				throw new Exception("Failed to create new battle pet!");
			};
		
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
	 * @param pet
	 * @param level
	 * @return total hitpoints
	 */
	private int getHitpointTotal(Pet pet, int level) throws Exception {
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
			String healthType = pet.getHealthType();
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

	/**
	 * Adds a new team_instance to the database based on the given battle pets
	 * 
	 * @param battlePets
	 * @return ID of the newly created team.
	 */
	public int createBattlePetTeam(List<BattlePet> battlePets) throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get the size of the team
			int teamSize = battlePets.size();
	
			// throw exception if team size is wrong
			if (teamSize < 1 || teamSize > 3) {
				throw new Exception("Team cannot be of size " + teamSize + "!");
			}
	
			// get db connection
			myConn = dataSource.getConnection();
			
			// build the query
			StringBuilder sqlParams = new StringBuilder("INSERT INTO team_instances (");
			StringBuilder sqlValues = new StringBuilder("VALUES (");
			for (int i = 1; i <= battlePets.size(); i++) {
				sqlParams.append("battle_pet_" + i + "_id, ");
				sqlValues.append("?, ");
			}
			
			sqlParams.setCharAt(sqlParams.lastIndexOf(","), ')');
			sqlValues.setCharAt(sqlValues.lastIndexOf(","), ')');
			
			String sql = sqlParams.append(sqlValues).toString();
				
			// return the id of the insertion
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// set the param values for team
			for (int i = 0; i < teamSize; i++) {				
				myStmt.setInt(i+1, battlePets.get(i).getId());
			}
						
			// execute sql insert
			myStmt.execute();
			
			// get the id of the inserted battle pet
			myRs = myStmt.getGeneratedKeys();
			
			// if no id found, throw an exception
			if (!myRs.next()) {
				throw new Exception("Could not create a new battle pet team!");
			}
			
			// return id of the team instance
			return myRs.getInt(1);		
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}		
	}

	/**
	 * Creates a random safari battle pet of the given level.
	 * 
	 * @param level
	 * @param petDbUtil 
	 * @return Safari battle pet of given level.
	 */
	public BattlePet createSafariBattlePet(int level, PetDbUtil petDbUtil)
			throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get a random safari pet of the given level
			Pet pet = getRandomSafariPet(level, petDbUtil);
			
			// get the appropriate number of hitpoints for the pet
			int hitpoints = getHitpointTotal(pet, level);
			
			// create a battle pet
			return createBattlePet(level, hitpoints, pet);
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Returns a random safari pet with the given level from the safari_pets
	 * table.
	 * 
	 * @param level
	 * @param petDbUtil 
	 * @return Random pet of the given level
	 */
	public Pet getRandomSafariPet(int level, PetDbUtil petDbUtil)
			throws Exception {		
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get a random safari pet of the given level
			String sql = "SELECT * FROM safari_pets " +
						 "WHERE level=? " +
						 "ORDER BY RAND() " +
						 "LIMIT 1";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setInt(1, level);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no pet found, throw exception
			if (!myRs.next()) {
				throw new Exception(
						"Cannot randomly find safari pet of level " + level + "!");
			}
			
			return petDbUtil.getPetFromId(myRs.getInt("pet_id"));
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Adds a safari_battle_instance to the database for the given player and safari
	 * team id's.
	 * 
	 * @param playerTeamId
	 * @param safariTeamId
	 */
	public void createSafariBattleInstance(int playerTeamId, int safariTeamId)
		throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO safari_battle_instances " +
						 "(player_team_id, safari_team_id) " +
						 "VALUES (?, ?)";
		
			// insert
			myStmt = myConn.prepareStatement(sql);
					
			// set the param values for battle pet
			myStmt.setInt(1, playerTeamId);
			myStmt.setInt(2, safariTeamId);
			
			// execute sql insert
			myStmt.execute();			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}		
	}
}
