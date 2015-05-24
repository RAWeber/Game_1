package com.github.raweber.java.towerdefense;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageHandler {
	
	HashMap<String, ImageIcon> images;
	
	public ImageHandler(){
		images = new HashMap<String, ImageIcon>();
		
		images.put("BasicTower", new ImageIcon("res/tower/BasicTower.png"));
		
	}
	
	Image getImage(String type){
		return images.get(type).getImage();
	}
}
