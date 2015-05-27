package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Graphics;

public class TowerStore {
	
	//private Screen screen;
	public static Tower[] towers= new Tower[20];
	private static BasicTower tower0=new BasicTower();

	public static void render(Graphics g){
		for(int x=0;x<10;x++){
			for(int y=0;y<2;y++){
				g.setColor(Color.black);
				g.drawRect((int)(Screen.TOWER_WIDTH*6+(x*Screen.TOWER_WIDTH)), Screen.TOWER_HEIGHT*17+(y*Screen.TOWER_HEIGHT), Screen.TOWER_WIDTH, Screen.TOWER_HEIGHT);
				if(towers[x*2+y]!=null){
					towers[x*2+y].render(g, (int)(Screen.TOWER_WIDTH*6+(x*Screen.TOWER_WIDTH)), Screen.TOWER_HEIGHT*17+(y*Screen.TOWER_HEIGHT),Screen.TOWER_WIDTH,Screen.TOWER_HEIGHT);
					if(towers[x*2+y].getCost()>Screen.player.getMoney()){
						g.setColor(new Color(255,0,0,100));
						g.fillRect((int)(Screen.TOWER_WIDTH*6+(x*Screen.TOWER_WIDTH)), Screen.TOWER_HEIGHT*17+(y*Screen.TOWER_HEIGHT), Screen.TOWER_WIDTH, Screen.TOWER_HEIGHT);
					}
				}
			}
		}
	}
}
