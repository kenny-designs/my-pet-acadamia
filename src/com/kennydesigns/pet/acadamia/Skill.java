/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.util.Random;

/**
 * @author crowly
 * Represents an ability in the game.
 */
public class Skill {
	private String name;
	private Damage damage;
	private Debuff debuff;
	private Buff buff;

	/**
	 * @param name
	 * @param damage
	 * @param debuff
	 * @param buff
	 */
	public Skill(String name, Damage damage, Debuff debuff, Buff buff) {
		super();
		this.name = name;
		this.damage = damage;
		this.debuff = debuff;
		this.buff = buff;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the damage
	 */
	public Damage getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	/**
	 * @return the debuff
	 */
	public Debuff getDebuff() {
		return debuff;
	}

	/**
	 * @param debuff the debuff to set
	 */
	public void setDebuff(Debuff debuff) {
		this.debuff = debuff;
	}

	/**
	 * @return the buff
	 */
	public Buff getBuff() {
		return buff;
	}

	/**
	 * @param buff the buff to set
	 */
	public void setBuff(Buff buff) {
		this.buff = buff;
	}

	/** Class for handling applying damage. */
	public static class Damage {
		String damageType;
		int level;
		int lowDamage;
		int highDamage;
		
		public Damage(String damageType, int level, int lowDamage, int highDamage) {
			super();
			this.damageType = damageType;
			this.level = level;
			this.lowDamage = lowDamage;
			this.highDamage = highDamage;
		}

		/**
		 * Gets a random amount of damage
		 * 
		 * @return A random amount of damage.
		 */
		public int getDamage() {
			Random rand = new Random();
			return rand.nextInt((highDamage - lowDamage) + 1) + lowDamage;
		}
	}
	
	/** Class used to handle debuffing. */
	public static class Debuff {}
	
	/** Class used to handle buffing. */
	public static class Buff {}
}
