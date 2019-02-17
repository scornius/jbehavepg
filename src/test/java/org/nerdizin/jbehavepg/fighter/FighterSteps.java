package org.nerdizin.jbehavepg.fighter;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;


public class FighterSteps {

	private Fighter fighter;

	@Given("a fighter with $hitpoints hitpoints and $armor armor")
	public void aFighter(final int hitpoints, final int armor) {
		fighter = new Fighter(hitpoints, armor);
	}

	@When("the fighter is dealt $damage damage")
	@Alias("the fighter takes $damage damage")
	public void theFighterIsDealtDamage(final int damage) {
		fighter.dealDamage(damage);
	}

	@Then("the fighter is alive")
	public void theFighterIsAlive() {
		Assert.assertTrue(fighter.isAlive());
	}

	@Then("the fighter is not alive")
	public void theFighterIsNotAlive() {
		Assert.assertFalse(fighter.isAlive());
	}

	@Then("the fighter has $hitpoints hitpoints")
	public void theFighterHasHitpoints(final int hitpoints) {
		Assert.assertEquals(hitpoints, fighter.getHitpoints());
	}
}

