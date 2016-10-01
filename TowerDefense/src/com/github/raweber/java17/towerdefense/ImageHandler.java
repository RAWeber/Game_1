package com.github.raweber.java17.towerdefense;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageHandler {
	
	private static HashMap<String, ImageIcon> images = new HashMap<String, ImageIcon>();
		
	public static void addImages(){
		images.put("BasicTower", new ImageIcon("res/BasicTower.png"));
		images.put("BombTower", new ImageIcon("res/BombTower.png"));
		images.put("LaserTower", new ImageIcon("res/LaserTower.png"));
		images.put("FlameTower", new ImageIcon("res/FlameTower.png"));
		images.put("SlowTower", new ImageIcon("res/SlowTower.png"));
		images.put("GroundTower", new ImageIcon("res/GroundTower.png"));
		images.put("BasicEnemy", new ImageIcon("res/BasicEnemy.png"));
		images.put("StrongEnemy", new ImageIcon("res/StrongEnemy.png"));
		images.put("FastEnemy", new ImageIcon("res/FastEnemy.png"));
		images.put("BombProjectile", new ImageIcon("res/BombProjectile.png"));
		images.put("BasicProjectile", new ImageIcon("res/BasicProjectile.png"));
		images.put("Terrain", new ImageIcon("res/Terrain.png"));
	}
	
	public static Image getIcon(String type){
		return images.get(type).getImage();
	}
}
