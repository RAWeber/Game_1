package com.github.raweber.java17.towerdefense;

public class KeyHandler {
	public static void keyEscape(){
		System.exit(1);
	}
	public static void keySpace(){
		Screen.gameState=Screen.STATE.Game;
	}
	public static void keyEnter(){
		MouseHandler.startButton();
	}
}