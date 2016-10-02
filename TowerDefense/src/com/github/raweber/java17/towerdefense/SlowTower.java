package com.github.raweber.java17.towerdefense;

public class SlowTower extends Tower{
	
	//String type, String attackType, int id, int cost, int range, double damage, double maxAttackTime, double maxAttackDelay, int maxProjectiles, Upgrade upgradeOne, Upgrade upgradeTwo
	public SlowTower() {
		super("SlowTower", "Slow AEO", 3, 400, 1, 1, 3, 50, 1, new Upgrade("Slow X1.5", 150), new Upgrade("Range +1", 200));
	}

	protected void attack(int x, int y, EnemyMove target) {

	}
	
	public void upgradeOne(){
		if(upgradeOne.isUpgraded()==false){
			this.setDamage(this.getDamage()*1.5);
			upgradeOne.setCost(upgradeOne.getCost()*2);
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
			this.setRange(this.getRange()+1);
			upgradeTwo.setUpgrade("Maxed");
		}
	}
}
