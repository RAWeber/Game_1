package com.github.raweber.java17.towerdefense;

public class BasicTower extends Tower {
	
	//String type, String attackType, int id, int cost, int range, double damage, double maxAttackTime, double maxAttackDelay, int maxProjectiles, Upgrade upgradeOne, Upgrade upgradeTwo
	public BasicTower(){
		super("BasicTower", "Projectiles", 0, 100, 3, 0.5, 0.0, 1.0, 25, new Upgrade("Damage X1.5", 150), new Upgrade("Range +1", 250));
	}

	protected void attack(int x, int y, EnemyMove target) {
		for(int i=0;i<projectiles.length;i++){
			if(projectiles[i]==null){
				projectiles[i]=new Projectile("BasicProjectile", x, y, 10, damage, target);
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
			upgradeOne.setCost(0);
		}
	}
	
	public void upgradeTwo(){
		if(upgradeTwo.isUpgraded()==false){
			this.setRange(this.getRange()+1);
			upgradeTwo.setCost(upgradeTwo.getCost()*2);
			upgradeTwo.setUpgraded(true);
		}else{
			this.setRange(this.getRange()+1);
			upgradeTwo.setUpgrade("Maxed");
			upgradeOne.setCost(0);
		}
	}
}
