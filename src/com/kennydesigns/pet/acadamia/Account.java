package com.kennydesigns.pet.acadamia;

/**
 * @author Alexander M. Aguilar
 * Represents individual user accounts.
 */
public class Account {
	private String username;
	private String password;
	private int id;
	private int battlesWon;
	private int battlesLost;
		
	/**
	 * @param username
	 * @param password
	 * @param id
	 * @param battlesWon
	 * @param battlesLost
	 */
	public Account(String username, String password, int id, int battlesWon, int battlesLost) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.battlesWon = battlesWon;
		this.battlesLost = battlesLost;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the battlesWon
	 */
	public int getBattlesWon() {
		return battlesWon;
	}

	/**
	 * @param battlesWon the battlesWon to set
	 */
	public void setBattlesWon(int battlesWon) {
		this.battlesWon = battlesWon;
	}

	/**
	 * @return the battlesLost
	 */
	public int getBattlesLost() {
		return battlesLost;
	}

	/**
	 * @param battlesLost the battlesLost to set
	 */
	public void setBattlesLost(int battlesLost) {
		this.battlesLost = battlesLost;
	}
}
