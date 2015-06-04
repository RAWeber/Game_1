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
	private static AcidTower tower2=new AcidTower();

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
					}
				}
			}
		}
	}
}
