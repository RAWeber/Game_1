package com.github.raweber.java17.game2;

public class KeyHandler {
	public static void keyEscape(){
		System.exit(1);
	}
	public static void keySpace(){
		Screen.gameState=Screen.STATE.Game;
	}
	public static void keyEnter(){
		Screen.wave.nextWave();
	}
}