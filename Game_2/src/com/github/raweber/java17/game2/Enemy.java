package com.github.raweber.java17.game2;

import java.awt.Graphics;

public class Enemy {

	public static final Enemy[] enemyList = new Enemy[10];
	
	public static final Enemy basicEnemy = new BasicEnemy(0, 10, 0.25, 10, 3, 10);
	
	private String type;
	private int reward;
	private double speed;
	private double attackSpeed;
	private int damage;
	private int health;
	
	public Enemy(String type, int id, int reward, double speed, double attackSpeed, int damage, int health){
		if(enemyList[id]==null){
			enemyList[id]=this;
		}
		this.setReward(reward);
		this.setSpeed(speed);
		this.setAttackSpeed(attackSpeed);
		this.setDamage(damage);
		this.setHealth(health);
	}
	


	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
