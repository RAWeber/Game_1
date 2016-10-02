package com.github.raweber.java17.towerdefense;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ESCAPE) KeyHandler.keyEscape();
		if(key == KeyEvent.VK_SPACE) KeyHandler.keySpace();
		if(key == KeyEvent.VK_ENTER)KeyHandler.keyEnter();
	}
	
	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent arg0) {
		
	}
}
