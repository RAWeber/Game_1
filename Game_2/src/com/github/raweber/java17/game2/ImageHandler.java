package com.github.raweber.java17.game2;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageHandler {
	
	private HashMap<String, ImageIcon> images;
	
	public ImageHandler(){
		images = new HashMap<String, ImageIcon>();
		
		images.put("BasicTower", new ImageIcon("res/BasicTower.png"));
		
	}
	
	public Image getIcon(String type){
		return images.get(type).getImage();
	}
}
