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
		}
		updateMouse(e);
	}
	
	public static void mouseMoved(MouseEvent e){
		mouseX=e.getX();
		mouseY=e.getY();
	}
	
	public static void updateMouse(MouseEvent e){

		if(mouseDown && holding==0){
			if(e.getX()>=(int)(Screen.TOWER_WIDTH*6) && e.getX()<=(int)(Screen.TOWER_WIDTH*6+(10*Screen.TOWER_WIDTH))){
				if(e.getY()>=Screen.TOWER_HEIGHT*17 && e.getY()<=Screen.TOWER_HEIGHT*19){
					for(int x=0;x<10;x++){
						for(int y=0;y<2;y++){
							if(TowerStore.towers[x*2+y]!=null){
								if(e.getX()>=(int)(Screen.TOWER_WIDTH*6+(x*Screen.TOWER_WIDTH)) && e.getX()<=(int)(Screen.TOWER_WIDTH*6+(x*Screen.TOWER_WIDTH))+Screen.TOWER_WIDTH){
									if(e.getY()>=Screen.TOWER_HEIGHT*17+(y*Screen.TOWER_HEIGHT) && e.getY()<=Screen.TOWER_HEIGHT*19+(y*Screen.TOWER_HEIGHT)){
										if(Screen.player.getMoney()>=TowerStore.towers[x*2+y].getCost()){
											holding=x*2+y+1;
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
	
	public static void placeTower(int x, int y){
		int xPos=x/Screen.TOWER_WIDTH-1;
		int yPos=y/Screen.TOWER_HEIGHT-1;
		
		if(xPos>=0 && xPos<25 && yPos>=0 && yPos<15){

			if(Screen.towerMap[xPos][yPos]==null && Screen.map[xPos][yPos]==0){
				Screen.player.setMoney(Screen.player.getMoney()-TowerStore.towers[holding-1].getCost());
				System.out.println("You bought a "+TowerStore.towers[holding-1]+" for "+TowerStore.towers[holding-1].getCost()+" coins!");
				Screen.towerMap[xPos][yPos]=TowerStore.towers[MouseHandler.holding-1];
			}
		}
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static int getMouseX() {
		return mouseX;
	}
}
