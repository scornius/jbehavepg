package org.nerdizin.jbehavepg.fighter;

public class Fighter {

	private int maxHitpoints;
	private int hitpoints;
	private int armor;

	public Fighter(final int hitpoints, final int armor) {
		this.hitpoints = hitpoints;
		this.maxHitpoints = hitpoints;
		this.armor = armor;
	}

	public void heal(final int amountToHeal) {
		this.hitpoints += amountToHeal;
		if (this.hitpoints > maxHitpoints) {
			this.hitpoints = maxHitpoints;
		}
	}

	public void dealDamage(final int damageToDeal) {
		this.hitpoints -= damageToDeal;
		if (this.hitpoints < 0) {
			this.hitpoints = 0;
		}
	}

	public boolean isAlive() {
		return hitpoints > 0;
	}

	public int getMaxHitpoints() {
		return maxHitpoints;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public int getArmor() {
		return armor;
	}

	@Override
	public String toString() {
		return "Fighter{" + "maxHitpoints=" + maxHitpoints + ", hitpoints=" + hitpoints + ", armor=" + armor + '}';
	}
}
