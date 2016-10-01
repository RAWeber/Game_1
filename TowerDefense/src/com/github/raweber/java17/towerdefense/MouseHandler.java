package com.github.raweber.java17.towerdefense;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler {

	public static int holding=0;
	public static boolean clicked=false;
	private static int mouseX=0;
	private static int mouseY=0;
	private static boolean mouseDown=false;
	
	public static ArrayList<Tower> boughtTowers = new ArrayList<Tower>();
	
	public static void mouseDown(MouseEvent e){
		mouseDown=true;
		clicked=false;
		updateMouse(e);
		if(holding==0){
			selectTower(e.getX(),e.getY());
		}
	}
	
	public static void mouseUp(MouseEvent e) {
		mouseDown=true;
		if(holding!=0){
			placeTower(e.getX(),e.getY());
		}
	}
	
	public static void mouseClicked(MouseEvent e){
		mouseDown=true;
		clicked=true;
		if(holding!=0){
			placeTower(e.getX(),e.getY());
		}else{
			selectTower(e.getX(),e.getY());
			setStrategy(e.getX(),e.getY());
			sellButton(e.getX(),e.getY());
			if(e.getX()>Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2 && e.getX()<Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2+Screen.TOWER_SIZE*8+Screen.TOWER_SIZE/2){
				if(e.getY()>Screen.TOWER_SIZE*16+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER && e.getY()<Screen.TOWER_SIZE*19+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER){
					startButton();
				}
			}
		}
		updateMouse(e);
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
		
		if((xPos>=0 && xPos<25 && yPos>=0 && yPos<15) && (Screen.towerMap[xPos][yPos]==null && Screen.map[xPos][yPos]==0)){
				Screen.player.setMoney(Screen.player.getMoney()-TowerStore.towers[holding-1].getCost());
				System.out.println("You bought a "+TowerStore.towers[holding-1]+" for "+TowerStore.towers[holding-1].getCost()+" coins!");
				Screen.towerMap[xPos][yPos]=(Tower)TowerStore.towers[holding-1].clone();
				Screen.selectedTower=Screen.towerMap[xPos][yPos];
				boughtTowers.add(Screen.selectedTower);
		}else{
			Screen.selectedTower=null;
		}
		holding=0;
	}
	
	private static void selectTower(int x, int y){
		int xPos=x/Screen.TOWER_SIZE-1;
		int yPos=(y-Screen.SCREEN_BORDER)/Screen.TOWER_SIZE-1;
		
		if(x>=Screen.TOWER_SIZE && x<Screen.TOWER_SIZE*26 && y>=Screen.TOWER_SIZE+Screen.SCREEN_BORDER && y<Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER){
			Screen.selectedTower=Screen.towerMap[xPos][yPos];
		}else{
			if(!((x>=Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2 && x<Screen.TOWER_SIZE*35 && y>=Screen.TOWER_SIZE+Screen.SCREEN_BORDER && y<Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER)||(x>=Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2 && x<Screen.TOWER_SIZE*35 && y>=Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/2 && y<Screen.TOWER_SIZE*19+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/2))){
				Screen.selectedTower=null;
			}
		}
	}
	
	private static void setStrategy(int x, int y){
		if(Screen.selectedTower!=null){
			if(x>=Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2 && x<Screen.TOWER_SIZE*35 && y>=Screen.TOWER_SIZE+Screen.SCREEN_BORDER && y<Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER){
				if(x>=Screen.TOWER_SIZE*32 && x<=Screen.TOWER_SIZE*32+Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4 && y>=Screen.TOWER_SIZE+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER && y<= Screen.TOWER_SIZE*2+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER) Screen.selectedTower.setAttackStrategy(Tower.STRATEGY.First);
				if(x>=Screen.TOWER_SIZE*32 && x<=Screen.TOWER_SIZE*32+Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4 && y>=Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*7/12+Screen.SCREEN_BORDER && y<= Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*7/12+Screen.SCREEN_BORDER) Screen.selectedTower.setAttackStrategy(Tower.STRATEGY.Last);
				if(x>=Screen.TOWER_SIZE*32 && x<=Screen.TOWER_SIZE*32+Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4 && y>=Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*11/12+Screen.SCREEN_BORDER && y<=Screen.TOWER_SIZE*4+Screen.TOWER_SIZE*11/12+Screen.SCREEN_BORDER) Screen.selectedTower.setAttackStrategy(Tower.STRATEGY.Strongest);
				if(x>=Screen.TOWER_SIZE*32 && x<=Screen.TOWER_SIZE*32+Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4 && y>=Screen.TOWER_SIZE*5+Screen.TOWER_SIZE*3/12+Screen.SCREEN_BORDER && y<=Screen.TOWER_SIZE*6+Screen.TOWER_SIZE*3/12+Screen.SCREEN_BORDER) Screen.selectedTower.setAttackStrategy(Tower.STRATEGY.Fastest);
			}
		}
	}
	
	private static void sellButton(int x, int y){
		if(x>=Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4 && x<= Screen.TOWER_SIZE*34+Screen.TOWER_SIZE*3/4 &&  y>=Screen.TOWER_SIZE*13+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER && y<=Screen.TOWER_SIZE*15+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER){	
			if(Screen.selectedTower!=null){
				for(int i=0;i<Screen.towerMap.length;i++){
					for(int j=0;j<Screen.towerMap[i].length;j++){
						if(Screen.towerMap[i][j]==Screen.selectedTower){
							if(boughtTowers.contains(Screen.selectedTower)){
								Screen.player.setMoney(Screen.player.getMoney()+Screen.selectedTower.getCost());
							}else{
								Screen.player.setMoney(Screen.player.getMoney()+Screen.selectedTower.getCost()/2);
							}
							Screen.towerMap[i][j]=null;
						}
					}
				}
			}
			Screen.selectedTower=null;
		}
	}
	
	public static void startButton(){
		if(Screen.enemyMap.size()==0){
			Screen.wave.nextWave();
			boughtTowers.clear();
		}else if(Screen.speed==1){
			Screen.speed=2;
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
