package com.github.raweber.java17.game2;


public class Enemy {

	public static final Enemy[] enemyList = new Enemy[10];
	
	private static final Enemy basicEnemy = new BasicEnemy(0, 1, 15, 6, 15, 3, 10);
	private static final Enemy strongEnemy = new StrongEnemy(1, 2, 25, 4, 10, 4, 30);
	private static final Enemy fastEnemy = new FastEnemy(2, 1, 10, 15, 15, 1, 15);
	
	private String type;
	private int reward;
	private double speed;
	private double attackSpeed;
	private int damage;
	private double health;
	private int value;
	private int id;
	
	public Enemy(String type, int id, int value, int reward, double speed, double attackSpeed, int damage, double health){
		if(enemyList[id]==null){
			enemyList[id]=this;
		}
		this.type=type;
		this.id=id;
		this.reward=reward;
		this.speed=speed*Screen.TOWER_SIZE/50;
		this.attackSpeed=attackSpeed;
		this.damage=damage;
		this.health=health;
		this.value=value;
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

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}
}
