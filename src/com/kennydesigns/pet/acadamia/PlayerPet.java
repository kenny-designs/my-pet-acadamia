/**
 * 
 */
package com.kennydesigns.pet.acadamia;

/**
 * @author crowly
 *
 * Individual pets that players own.
 */
public class PlayerPet {
	private int id;
	private int level;
	private int exp;
	private Pet pet;
	private Account account;
	
	public PlayerPet(int id, int level, int exp, Pet pet, Account account) {
		this.id = id;
		this.level = level;
		this.exp = exp;
		this.pet = pet;
		this.account = account;
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * @return the pet
	 */
	public Pet getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
}
