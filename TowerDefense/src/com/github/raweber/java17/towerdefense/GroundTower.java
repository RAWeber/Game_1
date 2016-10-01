package com.github.raweber.java17.towerdefense;

public class GroundTower extends Tower{

	public GroundTower() {
		super("GroundTower", "Impact AOE", 4, 400, 1, 20, 25, 25, 1);
	}

	protected void attack(int x, int y, EnemyMove target) {
		
	}

}
