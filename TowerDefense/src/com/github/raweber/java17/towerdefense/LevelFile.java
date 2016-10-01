package com.github.raweber.java17.towerdefense;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LevelFile {

	FileInputStream file;
	InputStreamReader reader;
	Scanner scanner;
	
	Level level = new Level();
	
	public Level getLevel(String fileName){
		try{
			file = new FileInputStream("Levels/"+fileName+".level");
			reader = new InputStreamReader(file);
			scanner=new Scanner(reader);
			
			level.setMap(new int[25][15]);
			
			while(scanner.hasNext()){
				for(int y=0; y<15;y++){
					for(int x=0;x<25;x++){
						level.setSpot(x,y,scanner.nextInt());
					}
				}
			}
			return level;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
}
