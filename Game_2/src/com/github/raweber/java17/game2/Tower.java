package com.github.raweber.java17.game2;

import java.awt.Graphics;

public class Tower {

	private String type;
	private int cost;
	
	public Tower(String type, int id, int cost){
		this.type=type;
		if(TowerStore.towers[id]==null){
			TowerStore.towers[id]=this;
		}
		this.cost=cost;
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
}
