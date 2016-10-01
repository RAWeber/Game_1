package com.github.raweber.java17.towerdefense;

public class Level {

	private int[][] map;
	
	private SpawnPoint spawnPoint;
	
	public void findSpawnPoint(){
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;y++){
				if(map[x][y]==2){
					setSpawnPoint(new SpawnPoint(x,y));
				}
			}
		}
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public void setSpot(int x, int y, int value){
		map[x][y]=value;
	}
	
	public void printMap(){
		for (int y=0;y<15;y++){
			for(int x=0;x<25;x++){
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}

	public SpawnPoint getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(SpawnPoint spawnPoint) {
		this.spawnPoint = spawnPoint;
	}
}
