package com.github.raweber.java17.towerdefense;

public class BombTower extends Tower{
	
	//String type, String attackType, int id, int cost, int range, double damage, double maxAttackTime, double maxAttackDelay, int maxProjectiles, Upgrade upgradeOne, Upgrade upgradeTwo
	public BombTower() {
		super("BombTower", "Bombs", 1, 150, 2, 5, 0, 15, 10, new Upgrade("Attack X1.5", 200), new Upgrade("Att Spd++", 300));
	}
	
	protected void attack(int x, int y, EnemyMove target) {
		for(int i=0;i<projectiles.length;i++){
			if(projectiles[i]==null){
				projectiles[i]=new Projectile("BombProjectile", x, y, 10, damage, target);
				break;
			}
		}
	}
	
	public void upgradeOne(){
		if(upgradeOne.isUpgraded()==false){
			this.setDamage(this.getDamage()*1.5);
			upgradeOne.setCost(upgradeOne.getCost()*2);;
			upgradeOne.setUpgraded(true);
		}else{
			this.setDamage(this.getDamage()*1.5);
			upgradeOne.setUpgrade("Maxed");
		}
	}
	
	public void upgradeTwo(){
		if(upgradeTwo.isUpgraded()==false){
			this.setMaxAttackDelay(this.getMaxAttackDelay()-5);
			upgradeTwo.setCost(upgradeTwo.getCost()*2);
			upgradeTwo.setUpgraded(true);
		}else{
			this.setDamage(this.getDamage()*1.5);
			upgradeTwo.setUpgrade("Maxed");
		}
	}
}