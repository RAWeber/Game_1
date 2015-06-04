package com.github.raweber.java17.game2;

public class AcidTower extends Tower{
	
	public AcidTower() {
		super("AcidTower", 2, 200, 5, 10, 25, 25, 0);
	}
	
	protected void attack(int x, int y, EnemyMove target) {
		target.setHealth(target.getHealth()-damage);
	}
}
