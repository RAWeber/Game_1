package com.github.raweber.java17.game2;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Tower implements Cloneable{

	private String type;
	private int cost;
	private int range;
	
	private int damage;
	private int attackTime=0;
	private int attackDelay=0;
	
	private double maxAttackTime;
	private double maxAttackDelay;
	
	private EnemyMove target;
	
	private enum STRATEGY {
		First, Last, Nearest, Farthest, Random;
	};
	
	private STRATEGY attackStrategy=STRATEGY.Random;
	
	public Tower(String type, int id, int cost, int range, int damage, double maxAttackTime, double maxAttackDelay){
		this.type=type;
		if(TowerStore.towers[id]==null){
			TowerStore.towers[id]=this;
		}
		this.cost=cost;
		this.range=range;
		this.damage=damage;
		this.maxAttackTime=maxAttackTime;
		this.maxAttackDelay=maxAttackDelay;
	}
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(ImageHandler.getIcon(type), x, y, null);
	}

	public void render(Graphics g, int x, int y, int w, int h) {
		g.drawImage(ImageHandler.getIcon(type), x, y, w, h, null);
	}
	
	public void towerAttack(EnemyMove[] enemies, int x, int y) {
		if(target==null){
			if(attackDelay>maxAttackDelay){
				target=calculateEnemy(enemies,x,y);
				if(target!=null){
					target.setHealth(target.getHealth()-damage);
					attackTime=0;
					attackDelay=0;
					System.out.println(type+" attacked enemy! Health: "+target.getHealth());
				}
			}else{
				attackDelay+=1;
			}
		}else{
			if(attackTime<maxAttackTime){
				attackTime+=1;
			}else{
				target=null;
			}
		}
		
	}
	
	private EnemyMove calculateEnemy(EnemyMove[] enemies, int x, int y){
		ArrayList<EnemyMove> inRange=new ArrayList<EnemyMove>();
		int towerX=x;
		int towerY=y;
		
		int towerRadius=range;
		int enemyRadius=1;
		
		int enemyX;
		int enemyY;
		
		for(int i=0; i<enemies.length;i++){
			if(enemies[i]!=null){
				enemyX=(int)(enemies[i].getXPos()/Screen.TOWER_SIZE);
				enemyY=(int)(enemies[i].getYPos()/Screen.TOWER_SIZE);
				
				int dX=enemyX-towerX;
				int dY=enemyY-towerY;
				
				int dRadius=towerRadius+enemyRadius;
				if((dX*dX)+(dY*dY)<(dRadius*dRadius)){
					inRange.add(enemies[i]);
				}
			}
		}
		
		if(attackStrategy==STRATEGY.Random){	
			if(inRange.size()>0){
				int index = new Random().nextInt(inRange.size());
				return inRange.get(index);
			}
		}
		return null;
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
	public String getType(){
		return type;
	}
	
	public String toString(){
		return type;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAttackTime() {
		return attackTime;
	}

	public void setAttackTime(int attackTime) {
		this.attackTime = attackTime;
	}

	public int getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(int attackDelay) {
		this.attackDelay = attackDelay;
	}

	public double getMaxAttackTime() {
		return maxAttackTime;
	}

	public void setMaxAttackTime(double maxAttackTime) {
		this.maxAttackTime = maxAttackTime;
	}

	public double getMaxAttackDelay() {
		return maxAttackDelay;
	}

	public void setMaxAttackDelay(double maxAttackDelay) {
		this.maxAttackDelay = maxAttackDelay;
	}

	public EnemyMove getTarget() {
		return target;
	}

	public void setTarget(EnemyMove target) {
		this.target = target;
	}
}
