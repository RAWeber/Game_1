package com.github.raweber.java17.game2;

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
			
			if(xPos>enemy.getRoutePosX()) enemy.setXPos(enemy.getXPos()-enemy.getEnemy().getSpeed());
			if(xPos<enemy.getRoutePosX()) enemy.setXPos(enemy.getXPos()+enemy.getEnemy().getSpeed());
			if(yPos>enemy.getRoutePosY()) enemy.setYPos(enemy.getYPos()-enemy.getEnemy().getSpeed());
			if(yPos<enemy.getRoutePosY()) enemy.setYPos(enemy.getYPos()+enemy.getEnemy().getSpeed());
		}
	}
	
	public void cantFindRoute(){
		System.out.println("Can't find route!");
	}
}
