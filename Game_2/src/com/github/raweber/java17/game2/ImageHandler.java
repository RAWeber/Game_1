package com.github.raweber.java17.game2;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageHandler {
	
	private static HashMap<String, ImageIcon> images = new HashMap<String, ImageIcon>();
		
	public static void addImages(){
		images.put("BasicTower", new ImageIcon("res/BasicTower.png"));
		images.put("Terrain", new ImageIcon("res/Terrain.png"));
	}
	
	public static Image getIcon(String type){
		return images.get(type).getImage();
	}
}
