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
}
