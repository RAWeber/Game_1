package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;

public class Player{
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
		g.setColor(Color.GRAY);
		g.fillRect(Screen.TOWER_SIZE, Screen.TOWER_SIZE*17+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*4, Screen.TOWER_SIZE*2);
		g.setColor(Color.BLACK);
		g.drawRect(Screen.TOWER_SIZE, Screen.TOWER_SIZE*17+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*4, Screen.TOWER_SIZE);
		g.drawRect(Screen.TOWER_SIZE, Screen.TOWER_SIZE*18+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*4, Screen.TOWER_SIZE);
		g.drawString("Health: "+health, Screen.TOWER_SIZE+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*17+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER);
		g.drawString("Money: "+money, Screen.TOWER_SIZE+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*18+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER);
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
