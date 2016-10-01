package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;

public class TowerStore {
	
	//private Screen screen;
	public static Tower[] towers= new Tower[20];
	@SuppressWarnings("unused")
	private static BasicTower tower0=new BasicTower();
	@SuppressWarnings("unused")
	private static BombTower tower1=new BombTower();
	@SuppressWarnings("unused")
	private static LaserTower tower2=new LaserTower();
	@SuppressWarnings("unused")
	private static SlowTower tower3=new SlowTower();
	@SuppressWarnings("unused")
	private static GroundTower tower4=new GroundTower();

	public static void render(Graphics g){
		for(int x=0;x<10;x++){
			for(int y=0;y<2;y++){
				g.setColor(Color.black);
				g.drawRect((int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE)), Screen.TOWER_SIZE*17+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER, Screen.TOWER_SIZE, Screen.TOWER_SIZE);
				if(towers[x*2+y]!=null){
					towers[x*2+y].render(g, (int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE)), Screen.TOWER_SIZE*17+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER,Screen.TOWER_SIZE,Screen.TOWER_SIZE);
					if(towers[x*2+y].getCost()>Screen.player.getMoney()){
						g.setColor(new Color(255,0,0,100));
						g.fillRect((int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE)), Screen.TOWER_SIZE*17+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER, Screen.TOWER_SIZE, Screen.TOWER_SIZE);
					}else if(x*2+y==MouseHandler.holding-1){
						if(MouseHandler.clicked==true){
							g.setColor(new Color(0,255,0,100));
							g.fillRect((int)(Screen.TOWER_SIZE*6+(x*Screen.TOWER_SIZE)), Screen.TOWER_SIZE*17+(y*Screen.TOWER_SIZE)+Screen.SCREEN_BORDER, Screen.TOWER_SIZE, Screen.TOWER_SIZE);
						}
					}
				}
			}
		}
		if(MouseHandler.holding!=0 && towers[MouseHandler.holding-1]!=null){
			int i=MouseHandler.holding-1;
			if(MouseHandler.clicked==false){
				towers[i].render(g, MouseHandler.getMouseX()-(int)(Screen.TOWER_SIZE/2), MouseHandler.getMouseY()-(int)(Screen.TOWER_SIZE/2),Screen.TOWER_SIZE,Screen.TOWER_SIZE);	
				if(MouseHandler.getMouseX()>Screen.TOWER_SIZE && MouseHandler.getMouseX()<Screen.TOWER_SIZE*26 && MouseHandler.getMouseY()>Screen.TOWER_SIZE+Screen.SCREEN_BORDER && MouseHandler.getMouseY()<Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER){
					g.setColor(Color.gray);
					g.drawOval((MouseHandler.getMouseX())-(Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE)/2, (MouseHandler.getMouseY())-(Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE)/2, Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE, Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE);
				}
			}else{
				if(MouseHandler.getMouseX()>Screen.TOWER_SIZE && MouseHandler.getMouseX()<Screen.TOWER_SIZE*26 && MouseHandler.getMouseY()>Screen.TOWER_SIZE+Screen.SCREEN_BORDER && MouseHandler.getMouseY()<Screen.TOWER_SIZE*16+Screen.SCREEN_BORDER){
					g.setColor(Color.gray);
					g.drawOval((MouseHandler.getMouseX())-(Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE)/2, (MouseHandler.getMouseY())-(Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE)/2, Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE, Screen.TOWER_SIZE*towers[i].getRange()*2+Screen.TOWER_SIZE);
					towers[i].render(g, MouseHandler.getMouseX()-(int)(Screen.TOWER_SIZE/2), MouseHandler.getMouseY()-(int)(Screen.TOWER_SIZE/2),Screen.TOWER_SIZE,Screen.TOWER_SIZE);	
				}
			}
		}
	}
}
