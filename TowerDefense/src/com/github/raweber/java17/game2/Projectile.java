package com.github.raweber.java17.game2;

import java.awt.Graphics;

public class Projectile {
	
	private String type;
	private double x;
	private double y;
	private int speed;
	private double damage;
	
	private double direction;
	
	private EnemyMove target;

	public Projectile(String type, double x, double y, int speed, double damage, EnemyMove target){
		this.type=type;
		this.x=Screen.TOWER_SIZE*x+Screen.TOWER_SIZE+Screen.TOWER_SIZE/4;
		this.y=Screen.TOWER_SIZE*y+Screen.TOWER_SIZE+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/4;
		this.speed=speed;
		this.damage=damage;
		
		this.target=target;
		updateDirection();
	}
	
	public void update(){
		updateDirection();
		x+=speed*Math.cos(direction);
		y+=speed*Math.sin(direction);
		updateTarget();
	}
	
	private void updateDirection(){
		direction=Math.atan2(target.getYPos()+Screen.TOWER_SIZE*3/2+Screen.SCREEN_BORDER-(y+Screen.TOWER_SIZE/4),target.getXPos()+Screen.TOWER_SIZE*3/2-(x+Screen.TOWER_SIZE/4));
	}
	
	private void updateTarget(){
		int dX=(int)(target.getYPos()+Screen.TOWER_SIZE*3/2+Screen.SCREEN_BORDER-(y+Screen.TOWER_SIZE/4));
		int dY=(int)(target.getXPos()+Screen.TOWER_SIZE*3/2-(x+Screen.TOWER_SIZE/4));
		
		int dRadius=(Screen.TOWER_SIZE/4)*Screen.speed*speed;
		
		if(dX*dX+dY*dY<dRadius){
			target.setHealth(target.getHealth()-damage);
			target=null;
		}
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(ImageHandler.getIcon(type), x, y, null);
	}

	public void render(Graphics g, int x, int y, int w, int h) {
		g.drawImage(ImageHandler.getIcon(type), x, y, w, h, null);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public EnemyMove getTarget(){
		return target;
	}
	public double getDirection(){
		return direction;
	}
}
