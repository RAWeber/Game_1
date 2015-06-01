package com.github.raweber.java17.game2;

public class EnemyAI {
	
	public static EnemyRoute route;
	public static EnemyAIMove moveAI;
	
	public static int basePosX;
	public static int basePosY;
	
	private int id;

	public EnemyAI(Level level){
		route = new EnemyRoute(level);
		basePosX=route.getBase().getXPos();
		basePosY=route.getBase().getYPos();
		
		moveAI = new EnemyAIMove(0);
	}
	
	public EnemyAI(int id){
		this.id=id;
	}
	
	public EnemyRoute getRoute(){
		return route;
	}
}
