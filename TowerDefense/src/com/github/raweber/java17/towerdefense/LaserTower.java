package com.github.raweber.java17.towerdefense;

public class LaserTower extends Tower{
	
	//String type, String attackType, int id, int cost, int range, double damage, double maxAttackTime, double maxAttackDelay, int maxProjectiles, Upgrade upgradeOne, Upgrade upgradeTwo
	public LaserTower() {
		super("LaserTower", "Laser", 2, 350, 4, 5, 10, 25, 0, new Upgrade("Attack X1.5", 150), new Upgrade("Range +1", 250));
	}
	
	protected void attack(int x, int y, EnemyMove target) {
		target.setHealth(target.getHealth()-damage);
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
			this.setRange(this.getRange()+1);
			upgradeTwo.setCost(upgradeTwo.getCost()*2);
			upgradeTwo.setUpgraded(true);
		}else{
			this.setDamage(this.getRange()+1);
			upgradeTwo.setUpgrade("Maxed");
		}
	}
}
