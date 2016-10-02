package com.github.raweber.java17.towerdefense;

public class Upgrade {

	private String upgrade;
	private int cost;
	private boolean upgraded;
	
	public Upgrade(String upgrade, int cost){
		this.upgrade=upgrade;
		this.cost=cost;
		upgraded=false;
	}

	public String getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isUpgraded() {
		return upgraded;
	}

	public void setUpgraded(boolean upgraded) {
		this.upgraded = upgraded;
	}
}