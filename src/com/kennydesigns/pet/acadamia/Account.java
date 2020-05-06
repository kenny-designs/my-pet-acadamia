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
	private int safariBattlesWon;
	private int safariBattlesLost;
	
	/**
	 * @param username
	 * @param password
	 * @param id
	 * @param battlesWon
	 * @param battlesLost
	 * @param safariBattlesWon
	 * @param safariBattlesLost
	 */
	public Account(String username, String password, int id, int battlesWon, int battlesLost, int safariBattlesWon,
			int safariBattlesLost) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
		this.battlesWon = battlesWon;
		this.battlesLost = battlesLost;
		this.safariBattlesWon = safariBattlesWon;
		this.safariBattlesLost = safariBattlesLost;
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

	/**
	 * @return the safariBattlesWon
	 */
	public int getSafariBattlesWon() {
		return safariBattlesWon;
	}

	/**
	 * @param safariBattlesWon the safariBattlesWon to set
	 */
	public void setSafariBattlesWon(int safariBattlesWon) {
		this.safariBattlesWon = safariBattlesWon;
	}

	/**
	 * @return the safariBattlesLost
	 */
	public int getSafariBattlesLost() {
		return safariBattlesLost;
	}

	/**
	 * @param safariBattlesLost the safariBattlesLost to set
	 */
	public void setSafariBattlesLost(int safariBattlesLost) {
		this.safariBattlesLost = safariBattlesLost;
	}
}
