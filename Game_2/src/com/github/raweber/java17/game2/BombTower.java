package com.github.raweber.java17.game2;

public class BombTower extends Tower{
	
	//int id, int cost, int range, double damage, double maxAttackTime, double maxAttackDelay, int maxProjectiles
	public BombTower() {
		super("BombTower", 1, 150, 2, 5, 0, 10, 10);
	}
	
	protected void attack(int x, int y, EnemyMove target) {
		for(int i=0;i<projectiles.length;i++){
			if(projectiles[i]==null){
				projectiles[i]=new Projectile("BombProjectile", x, y, 10, 3, target);
				break;
			}
		}
	}
}
