package com.github.raweber.java17.game2;

public class BasicTower extends Tower {

	public BasicTower() {
		super("BasicTower", 0, 100, 3, 0.5, 0, 0, 25);

	}

	protected void attack(int x, int y, EnemyMove target) {
		for(int i=0;i<projectiles.length;i++){
			if(projectiles[i]==null){
				projectiles[i]=new Projectile("BasicProjectile", x, y, 10, 0.5, target);
				break;
			}
		}
	}
}
