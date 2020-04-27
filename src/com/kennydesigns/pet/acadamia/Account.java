package com.kennydesigns.pet.acadamia;

/**
 * @author Alexander M. Aguilar
 * Represents individual user accounts.
 */
public class Account {
	private String username;
	private String password;
	private int id;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
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
}
