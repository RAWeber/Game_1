package com.github.raweber.java17.game2;

import java.awt.event.MouseEvent;

public class MouseHandler {

	public static int holding=0;
	private static int mouseX=0;
	private static int mouseY=0;
	private static boolean mouseDown=false;
	
	public static void mouseDown(MouseEvent e){
		mouseDown=true;
		if(holding!=0){
			placeTower(e.getX(),e.getY());
			holding=0;
		}else{
			selectTower(e.getX(),e.getY());
			if(e.getX()>Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2 && e.getX()<Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2+Screen.TOWER_SIZE*8+Screen.TOWER_SIZE/2){
				if(e.getY()>Screen.TOWER_SIZE*16+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER && e.getY()<Screen.TOWER_SIZE*19+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER){
					startButton();
				}
			}
		}
		updateMouse(e);
	}
	
	public static void mouseClicked(MouseEvent e){
		
	}
	
	public static void mouseMoved(MouseEvent e){
		mouseX=e.getX();
		mouseY=e.getY();
	}
	
	private static void updateMouse(MouseEvent e){

		if(mouseDown && holding==0){
			if(e.getX()>=(int)(Screen.TOWER_SIZE*6) && e.getX()<=(int)(Screen.TOWER_SIZE*6+(10*Screen.TOWER_SIZE))){
				if(e.getY()>=Screen.TOWER_SIZE*17+Screen.SCREEN_BORDER && e.getY()<=Screen.TOWER_SIZE*19+Screen.SCREEN_BORDER){
					for(int x=0;x<10;x++){
						for(int y=0;y<2;y++){
							if(TowerStore.towers[x*2+y]!=null){
								if(e.getX()>=(int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE)) && e.getX()<=(int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE))+Screen.TOWER_SIZE){
									if(e.getY()>=Screen.TOWER_SIZE*17+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER && e.getY()<=Screen.TOWER_SIZE*19+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER){
										if(Screen.player.getMoney()>=TowerStore.towers[x*2+y].getCost()){
											holding=x*2+y+1;
											Screen.selectedTower=TowerStore.towers[holding-1];
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static void placeTower(int x, int y){
		int xPos=x/Screen.TOWER_SIZE-1;
		int yPos=(y-Screen.SCREEN_BORDER)/Screen.TOWER_SIZE-1;
		
		if(xPos>=0 && xPos<25 && yPos>=0 && yPos<15){

			if(Screen.towerMap[xPos][yPos]==null && Screen.map[xPos][yPos]==0){
				Screen.player.setMoney(Screen.player.getMoney()-TowerStore.towers[holding-1].getCost());
				System.out.println("You bought a "+TowerStore.towers[holding-1]+" for "+TowerStore.towers[holding-1].getCost()+" coins!");
				Screen.towerMap[xPos][yPos]=(Tower)TowerStore.towers[holding-1].clone();
				Screen.selectedTower=Screen.towerMap[xPos][yPos];
			}
		}
	}
	
	private static void selectTower(int x, int y){
		int xPos=x/Screen.TOWER_SIZE-1;
		int yPos=(y-Screen.SCREEN_BORDER)/Screen.TOWER_SIZE-1;
		
		if(xPos>=0 && xPos<25 && yPos>=0 && yPos<15){
			Screen.selectedTower=Screen.towerMap[xPos][yPos];
		}else{
			Screen.selectedTower=null;
		}
	}
	
	public static void startButton(){
		if(Screen.enemyMap.size()==0){
			Screen.wave.nextWave();
		}else if(Screen.speed==1){
			Screen.speed=4;
		}else{
			Screen.speed=1;
		}
			
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static int getMouseX() {
		return mouseX;
	}
}
