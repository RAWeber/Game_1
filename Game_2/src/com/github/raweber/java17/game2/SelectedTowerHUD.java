package com.github.raweber.java17.game2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SelectedTowerHUD {

	public static void render(Graphics g){
		//Screen.selectedTower hud
		g.setColor(Color.gray);
		g.fillRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2,Screen.TOWER_SIZE+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*8+Screen.TOWER_SIZE/2, Screen.TOWER_SIZE*15);
		g.setColor(Color.black);
		g.drawRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE/2,Screen.TOWER_SIZE+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*8+Screen.TOWER_SIZE/2, Screen.TOWER_SIZE*15);
		
		//Screen.selectedTower
		g.setColor(Color.black);
		g.drawImage(Screen.terrain[Screen.map[0][0]], Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*5, Screen.TOWER_SIZE*5, null);
		if(Screen.selectedTower!=null){
			Screen.selectedTower.render(g, Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*5, Screen.TOWER_SIZE*5);
		}
		g.drawRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE+Screen.SCREEN_BORDER+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*5, Screen.TOWER_SIZE*5);
		
		//strategies
		Font playerFont = new Font(g.getFont().getName(), Font.PLAIN, Screen.TOWER_SIZE/2);
		g.setFont(playerFont);
		g.setColor(Color.black);
		g.drawRect(Screen.TOWER_SIZE*32, Screen.TOWER_SIZE+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE);
		g.drawString("FIRST", Screen.TOWER_SIZE*32+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE+Screen.TOWER_SIZE/4+Screen.TOWER_SIZE*2/3+Screen.SCREEN_BORDER);
		g.drawRect(Screen.TOWER_SIZE*32, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*7/12+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE);
		g.drawString("LAST", Screen.TOWER_SIZE*32+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*7/12+Screen.TOWER_SIZE*2/3+Screen.SCREEN_BORDER);
		g.drawRect(Screen.TOWER_SIZE*32, Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*11/12+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE);
		g.drawString("STRONG", Screen.TOWER_SIZE*32+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*11/12+Screen.TOWER_SIZE*2/3+Screen.SCREEN_BORDER);
		g.drawRect(Screen.TOWER_SIZE*32, Screen.TOWER_SIZE*5+Screen.TOWER_SIZE*3/12+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE);
		g.drawString("FAST", Screen.TOWER_SIZE*32+Screen.TOWER_SIZE/4, Screen.TOWER_SIZE*5+Screen.TOWER_SIZE*3/12+Screen.TOWER_SIZE*2/3+Screen.SCREEN_BORDER);
		if(Screen.selectedTower!=null){
			g.setColor(new Color(0,255,0,100));
			int yStrat = 0;
			if(Screen.selectedTower.getAttackStrategy()==Tower.STRATEGY.First) yStrat=Screen.TOWER_SIZE+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER;
			if(Screen.selectedTower.getAttackStrategy()==Tower.STRATEGY.Last) yStrat=Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*7/12+Screen.SCREEN_BORDER;
			if(Screen.selectedTower.getAttackStrategy()==Tower.STRATEGY.Strongest) yStrat=Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*11/12+Screen.SCREEN_BORDER;
			if(Screen.selectedTower.getAttackStrategy()==Tower.STRATEGY.Fastest) yStrat=Screen.TOWER_SIZE*5+Screen.TOWER_SIZE*3/12+Screen.SCREEN_BORDER;
			g.fillRect(Screen.TOWER_SIZE*32, yStrat, Screen.TOWER_SIZE*2+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE);
		}
		
		//upgrades
		g.setColor(Color.black);
		g.drawRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE*6+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*7/8, Screen.TOWER_SIZE*2);
		g.drawRect(Screen.TOWER_SIZE*30+Screen.TOWER_SIZE*7/8, Screen.TOWER_SIZE*6+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*3+Screen.TOWER_SIZE*7/8, Screen.TOWER_SIZE*2);
		
		//stats
		g.setColor(Color.black);
		g.drawRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE*9+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*8, Screen.TOWER_SIZE*4+Screen.TOWER_SIZE/2);
		if(Screen.selectedTower!=null){
			g.drawString("Damage: "+Screen.selectedTower.getDamage(), Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*9+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER);
			g.drawString("Range: "+Screen.selectedTower.getRange(), Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*10+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER);
			g.drawString("Attacks Per Second: "+(Math.round(25/(Screen.selectedTower.getMaxAttackTime()+Screen.selectedTower.getMaxAttackDelay()) * 100.0) / 100.0), Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*11+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER);
			g.drawString("Attack Type: "+Screen.selectedTower.getAttackType(), Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*12+Screen.SCREEN_BORDER);
			g.drawString("Kills: "+Screen.selectedTower.getKills(), Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*13+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER);
		}else{
			g.drawString("Damage: ", Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*9+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER);
			g.drawString("Range: ", Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*10+Screen.TOWER_SIZE/2+Screen.SCREEN_BORDER);
			g.drawString("Attacks Per Second: ", Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*11+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER);
			g.drawString("Attack Type: ", Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*12+Screen.SCREEN_BORDER);
			g.drawString("Kills: ", Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*13+Screen.TOWER_SIZE/4+Screen.SCREEN_BORDER);
		}
		
		//sellButton
		playerFont = new Font(g.getFont().getName(), Font.PLAIN, Screen.TOWER_SIZE);
		g.setFont(playerFont);
		g.setColor(Color.black);
		g.drawRect(Screen.TOWER_SIZE*26+Screen.TOWER_SIZE*3/4, Screen.TOWER_SIZE*13+Screen.TOWER_SIZE*3/4+Screen.SCREEN_BORDER, Screen.TOWER_SIZE*8, Screen.TOWER_SIZE*2);
		if(Screen.selectedTower!=null){
			if(MouseHandler.holding!=0){
				g.drawString("SELL FOR: ",Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*15+Screen.SCREEN_BORDER);
			}else if(!MouseHandler.boughtTowers.contains(Screen.selectedTower)){
				g.drawString("SELL FOR: "+Screen.selectedTower.getCost()/2,Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*15+Screen.SCREEN_BORDER);
			}
			else{
				g.drawString("SELL FOR: "+Screen.selectedTower.getCost(),Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*15+Screen.SCREEN_BORDER);
			}
		}else{
			g.drawString("SELL FOR: ",Screen.TOWER_SIZE*27, Screen.TOWER_SIZE*15+Screen.SCREEN_BORDER);
		}
	}
}
