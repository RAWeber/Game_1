package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int money;
	private int health;
	
	public Player(){
		money=300;
		health=50;
	}
	
	public Player(int money, int health){
		this.money=money;
		this.health=health;
	}
	
	public void render(Graphics g){
		int w=Frame.WIDTH/30;
		int h=(int)(Frame.HEIGHT/22.5);
		
		g.setColor(Color.GRAY);
		g.fillRect(40, Frame.HEIGHT-190, w*4, h*2);
		g.setColor(Color.BLACK);
		g.drawRect(40, Frame.HEIGHT-190, w*4, h);
		g.drawRect(40, Frame.HEIGHT-190+h, w*4, h);
		g.drawString("Health: "+health, 50, Frame.HEIGHT-190+20);
		g.drawString("Money: "+money, 50, Frame.HEIGHT-190+h+20);
	}
	
	public int getMoney(){
		return money;
	}
	public void setMoney(int money){
		this.money=money;
	}
	public int getHealth(){
		return health;
	}
	public void setHealth(int health){
		this.health=health;
	}
}
