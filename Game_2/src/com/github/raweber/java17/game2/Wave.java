package com.github.raweber.java17.game2;

import java.util.ArrayList;
import java.util.Random;

public class Wave {

	private int waveNumber=0;
	private int waveEnemies=0;
	private int currentPoints;
	private int pointsPerWave=10;
	//private long lastSpawnTime=System.currentTimeMillis();
	
	private boolean waveSpawning=false;
	
	private int currentDelay=0;
	private int spawnRate=25;
	
	public void nextWave(){
		waveNumber++;
		waveEnemies=0;
		currentPoints=0;
		setWaveSpawning(true);
		
		System.out.println("Wave "+waveNumber+" incoming!");
		
		for(int i = 0; i < Screen.enemyMap.length;i++){
			Screen.enemyMap[i]=null;
		}
	}
	
	public void spawnEnemies(){
		if(currentPoints<waveNumber*pointsPerWave){
			//if(System.currentTimeMillis()-1000>=lastSpawnTime){
			if(currentDelay<spawnRate){
				currentDelay++;
			}else{
				currentDelay=0;
				//lastSpawnTime=System.currentTimeMillis();
				waveEnemies++;
				ArrayList<Integer> spawnableIDs = new ArrayList<Integer>();
				for(int i=0;i<Enemy.enemyList.length;i++){
					if(Enemy.enemyList[i]!=null){
						if(Enemy.enemyList[i].getId()<=waveNumber-1){
							if(Enemy.enemyList[i].getValue()+currentPoints<=waveNumber*pointsPerWave){
							spawnableIDs.add(Enemy.enemyList[i].getId());
							}
						}
					}
				}
				spawnEnemy(spawnableIDs.get(new Random().nextInt(spawnableIDs.size())));
				
			}
		}else{
			setWaveSpawning(false);
		}
	}
	
	private void spawnEnemy(int id){
		for(int i = 0; i<Screen.enemyMap.length;i++){
			if(Screen.enemyMap[i]==null){
				Screen.enemyMap[i]=new EnemyMove(Enemy.enemyList[id],Screen.level.getSpawnPoint());
				currentPoints+=Enemy.enemyList[id].getValue();
				System.out.println(Enemy.enemyList[id].getType()+" spawned!");
				break;
			}
		}
	}

	public boolean isWaveSpawning() {
		return waveSpawning;
	}

	public void setWaveSpawning(boolean waveSpawning) {
		this.waveSpawning = waveSpawning;
	}
}