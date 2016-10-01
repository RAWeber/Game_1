package com.github.raweber.java17.towerdefense;

public class EnemyRoute {

	private Level level;
	private int[][] route = new int[25][15];
	
	private final int baseBlock=3;
	
	public static final int UP=1;
	public static final int RIGHT=2;
	public static final int DOWN=3;
	public static final int LEFT=4;
	
	private int lastPos=-1;
	private int xPos;
	private int yPos;
	
	private Base base;
	
	public EnemyRoute(Level level){
		this.level=level;
		this.xPos=this.level.getSpawnPoint().getX();
		this.yPos=this.level.getSpawnPoint().getY();
		
		calculateRoute();
	}
	
	private void calculateRoute(){
		while(base==null){
			calculateNextPos();
		}
	}

	private void calculateNextPos() {
		for(int i=1;i<5;i++){
			if(i!=lastPos){
				if(yPos>0 && i==UP){
					if(level.getMap()[xPos][yPos-1]==1){
						lastPos=DOWN;
						route[xPos][yPos]=UP;
						yPos--;
						break;
					}else if(level.getMap()[xPos][yPos-1]==baseBlock){
						base=new Base(xPos,yPos);
						break;
					}
				}else if(xPos<24 && i==RIGHT){
					if(level.getMap()[xPos+1][yPos]==1){
						lastPos=LEFT;
						route[xPos][yPos]=RIGHT;
						xPos++;
						break;
					}else if(level.getMap()[xPos+1][yPos]==baseBlock){
						base=new Base(xPos,yPos);
						break;
					}
				}else if(yPos<14 && i==DOWN){
					if(level.getMap()[xPos][yPos+1]==1){
						lastPos=UP;
						route[xPos][yPos]=DOWN;
						yPos++;
						break;
					}else if(level.getMap()[xPos][yPos+1]==baseBlock){
						base=new Base(xPos,yPos);
						break;
					}
				}else if(xPos>0 && i==LEFT){
					if(level.getMap()[xPos-1][yPos]==1){
						lastPos=RIGHT;
						route[xPos][yPos]=LEFT;
						xPos--;
						break;
					}else if(level.getMap()[xPos-1][yPos]==baseBlock){
						base=new Base(xPos,yPos);
						break;
					}
				}
			}
		}
	}
	public Base getBase(){
		return base;
	}
	public int[][] getRoute(){
		return route;
	}
	public void printRoute(){
		for(int y=0;y<route[0].length;y++){
			for(int x=0;x<route.length;x++){
				System.out.print(route[x][y]);
			}
			System.out.println("");
		}
	}
}
