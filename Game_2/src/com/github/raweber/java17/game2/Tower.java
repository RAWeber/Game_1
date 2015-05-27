package com.github.raweber.java17.game2;

import java.awt.Graphics;

public class Tower {

	private String type;
	private int cost;
	private int range;
	
	public Tower(String type, int id, int cost, int range){
		this.type=type;
		if(TowerStore.towers[id]==null){
			TowerStore.towers[id]=this;
		}
		this.cost=cost;
		this.setRange(range);
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(ImageHandler.getIcon(type), x, y, null);
	}

	public void render(Graphics g, int x, int y, int w, int h) {
		g.drawImage(ImageHandler.getIcon(type), x, y, w, h, null);
	}
	
	public int getCost(){
		return cost;
	}
	public void setCost(int cost){
		this.cost=cost;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
}
