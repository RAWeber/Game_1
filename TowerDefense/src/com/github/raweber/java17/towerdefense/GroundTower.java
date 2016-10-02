package com.github.raweber.java17.towerdefense;

public class GroundTower extends Tower{
	
	public GroundTower() {
		super("GroundTower", "Impact AOE", 4, 400, 1, 20, 25, 25, 1, new Upgrade("Attack X1.5", 200), new Upgrade("Range +1", 300));
	}

	protected void attack(int x, int y, EnemyMove target) {
		
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
			this.setRange(this.getRange()+1);
			upgradeTwo.setUpgrade("Maxed");
		}
	}
}
