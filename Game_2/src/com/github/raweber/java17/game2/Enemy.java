package com.github.raweber.java17.game2;


public class Enemy {

	public static final Enemy[] enemyList = new Enemy[10];
	
	public static final Enemy basicEnemy = new BasicEnemy(0, 1, 10, 16, 15, 3, 15);
	public static final Enemy strongEnemy = new StrongEnemy(1, 3, 20, 8, 10, 4, 30);
	public static final Enemy fastEnemy = new FastEnemy(2, 2, 15, 32, 25, 1, 10);
	
	private String type;
	private int reward;
	private double speed;
	private double attackSpeed;
	private int damage;
	private int health;
	private int value;
	private int id;
	
	public Enemy(String type, int id, int value, int reward, double speed, double attackSpeed, int damage, int health){
		if(enemyList[id]==null){
			enemyList[id]=this;
		}
		this.type=type;
		this.id=id;
		this.reward=reward;
		this.speed=(double)Screen.TOWER_SIZE/speed;
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



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
}
