package com.github.raweber.java17.towerdefense;

public class EnemyAIMove extends EnemyAI{

	public EnemyAIMove(int id) {
		super(id);
	}
	
	public void move(EnemyMove enemy){
		if(enemy.getRoutePosX()==enemy.getXPos()/Screen.TOWER_SIZE && enemy.getRoutePosY()==enemy.getYPos()/Screen.TOWER_SIZE && enemy.getXPos()%Screen.TOWER_SIZE==0 && enemy.getYPos()%Screen.TOWER_SIZE==0){
			if(enemy.getRoutePosX()==basePosX && enemy.getRoutePosY()==basePosY){
				enemy.setAttack(true);
			}else{
				if(route.getRoute()[enemy.getRoutePosX()][enemy.getRoutePosY()]==EnemyRoute.UP){
					enemy.setRoutePosY(enemy.getRoutePosY()-1);
				}else if(route.getRoute()[enemy.getRoutePosX()][enemy.getRoutePosY()]==EnemyRoute.RIGHT){
					enemy.setRoutePosX(enemy.getRoutePosX()+1);
				}else if(route.getRoute()[enemy.getRoutePosX()][enemy.getRoutePosY()]==EnemyRoute.DOWN){
					enemy.setRoutePosY(enemy.getRoutePosY()+1);
				}else if(route.getRoute()[enemy.getRoutePosX()][enemy.getRoutePosY()]==EnemyRoute.LEFT){
					enemy.setRoutePosX(enemy.getRoutePosX()-1);
				}else{
					cantFindRoute();
				}
			}
		}else{
			double xPos=enemy.getXPos()/Screen.TOWER_SIZE;
			double yPos=enemy.getYPos()/Screen.TOWER_SIZE;
			//move left
			if(xPos>enemy.getRoutePosX()){
				enemy.setXPos(enemy.getXPos()-enemy.getEnemy().getSpeed());
				enemy.setDistance(enemy.getDistance()+enemy.getEnemy().getSpeed());
				if(enemy.getXPos()<enemy.getRoutePosX()*Screen.TOWER_SIZE){
					enemy.setXPos(enemy.getRoutePosX()*Screen.TOWER_SIZE);
				}
			}
			//move right
			if(xPos<enemy.getRoutePosX()){
				enemy.setXPos(enemy.getXPos()+enemy.getEnemy().getSpeed());
				enemy.setDistance(enemy.getDistance()+enemy.getEnemy().getSpeed());
				if(enemy.getXPos()>enemy.getRoutePosX()*Screen.TOWER_SIZE){
					enemy.setXPos(enemy.getRoutePosX()*Screen.TOWER_SIZE);
				}
			}
			//move up
			if(yPos>enemy.getRoutePosY()){
				enemy.setYPos(enemy.getYPos()-enemy.getEnemy().getSpeed());
				enemy.setDistance(enemy.getDistance()+enemy.getEnemy().getSpeed());
				if(enemy.getYPos()<enemy.getRoutePosY()*Screen.TOWER_SIZE){
					enemy.setYPos(enemy.getRoutePosY()*Screen.TOWER_SIZE);
				}
			}
			//move down
			if(yPos<enemy.getRoutePosY()){
				enemy.setYPos(enemy.getYPos()+enemy.getEnemy().getSpeed());
				enemy.setDistance(enemy.getDistance()+enemy.getEnemy().getSpeed());
				if(enemy.getYPos()>enemy.getRoutePosY()*Screen.TOWER_SIZE){
					enemy.setYPos(enemy.getRoutePosY()*Screen.TOWER_SIZE);
				}
			}
		}
	}
	
	public void cantFindRoute(){
		System.out.println("Can't find route!");
	}
}
