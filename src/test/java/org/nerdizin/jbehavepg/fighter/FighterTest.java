package org.nerdizin.jbehavepg.fighter;

import org.junit.Test;

import static org.junit.Assert.*;

public class FighterTest {

	@Test
	public void testFighterIsHitButSurvives() {

		final Fighter fighter = new Fighter(5, 0);
		fighter.dealDamage(3);
		assertTrue(fighter.isAlive());
		assertEquals(2, fighter.getHitpoints());
	}

	@Test
	public void testFighterIsHitAndDies() {

		final Fighter fighter = new Fighter(5, 0);
		fighter.dealDamage(5);
		assertFalse(fighter.isAlive());
		assertEquals(0, fighter.getHitpoints());
	}

}
