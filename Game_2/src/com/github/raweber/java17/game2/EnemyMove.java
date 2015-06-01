package com.github.raweber.java17.game2;

import java.awt.Graphics;

public class EnemyMove {

	private Enemy enemy;
	
	private boolean attacking;
	private int health;
	
	private double xPos;
	private double yPos;
	private int routePosX;
	private int routePosY;
	
	public EnemyMove(Enemy enemy, SpawnPoint spawnPoint){
		this.setEnemy(enemy);
		
		this.setRoutePosX(spawnPoint.getX());
		this.setRoutePosY(spawnPoint.getY());
		
		this.setXPos(spawnPoint.getX()*Screen.TOWER_SIZE);
		this.setYPos(spawnPoint.getY()*Screen.TOWER_SIZE);
		
		this.setAttack(false);
		this.health=enemy.getHealth();
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(ImageHandler.getIcon(enemy.getType()), x, y, null);
	}

	public void render(Graphics g, int x, int y, int w, int h) {
		g.drawImage(ImageHandler.getIcon(enemy.getType()), x, y, w, h, null);
	}
	
	public EnemyMove update(){
		EnemyMove currentEnemy = this;
		if(currentEnemy.health<=0){
			return null;
		}
		return currentEnemy;
	}

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	public int getRoutePosX() {
		return routePosX;
	}

	public void setRoutePosX(int routePosX) {
		this.routePosX = routePosX;
	}

	public int getRoutePosY() {
		return routePosY;
	}

	public void setRoutePosY(int routePosY) {
		this.routePosY = routePosY;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}
}
