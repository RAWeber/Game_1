package com.github.raweber.java17.towerdefense;

public class LaserTower extends Tower{
	
	public LaserTower() {
		super("LaserTower", "Laser", 2, 350, 4, 5, 10, 25, 0);
	}
	
	protected void attack(int x, int y, EnemyMove target) {
		target.setHealth(target.getHealth()-damage);
	}
}
