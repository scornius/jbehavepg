Scenario:  fighter is hit but survives

Given a fighter with 5 hitpoints and 0 armor
When the fighter is dealt 3 damage
Then the fighter is alive
Then the fighter has 2 hitpoints

Scenario:  fighter is hit and dies

Given a fighter with 5 hitpoints
When the fighter takes 5 damage
Then the fighter is not alive
Then the fighter has 0 hitpoints
