package com.ap_project.game;

import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.ap_project.game.sprites.slingshot;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.ap_project.game.Core.currentLevel;

public class gameData implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<block<?>> blocks;
    public ArrayList<pig<?>> pigs;
    public ArrayList<bird<?>> birds;
    public int currentBirdIndex;
    public List<Vector2> blockPositions;
    public List<Vector2> pigPositions;
    public List<Vector2> birdPositions;
    public ArrayList<Object> bodiesToDestroy ;
    public ArrayList<Integer> pigHealth;
    public float timeGap=0;
    public float gameWinTimer=0;
    public float gameLoseTimer=0;
    public int currentGameLevel;//figure this out.
    public List<Vector2> blockVelocities;
    public List<Vector2> pigVelocities;
    public List<Vector2> birdVelocities;
    public gameData(ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs, ArrayList<bird<?>> birds, int currentBirdIndex, List<Vector2> blockPositions,List<Vector2> birdPositions,
                    List<Vector2> pigPositions,ArrayList<Integer> pigHealth,float timeGap,float gameWinTimer,float gameLoseTimer,ArrayList<Object> bodies,List<Vector2> blockVelocity,List<Vector2> birdVelocity,
                    List<Vector2> pigVelocity) {
        this.blocks = blocks != null ? blocks : new ArrayList<>();
        this.pigs = pigs != null ? pigs : new ArrayList<>();
        this.birds = birds != null ? birds : new ArrayList<>();
        this.currentBirdIndex = currentBirdIndex;
        this.blockPositions = blockPositions != null ? blockPositions : new ArrayList<>();
        this.pigPositions = pigPositions != null ? pigPositions : new ArrayList<>();
        this.birdPositions= birdPositions != null ? birdPositions : new ArrayList<>();
        this.bodiesToDestroy = bodies;
        this.timeGap = timeGap;
        this.gameWinTimer = gameWinTimer;
        this.gameLoseTimer = gameLoseTimer;
        this.pigHealth=pigHealth;
        this.currentGameLevel=currentLevel;
        this.blockVelocities=blockVelocity;
        System.out.println("when block velocity set the  block velocity size is "+blockVelocities.size());
        this.pigVelocities=pigVelocity;
        this.birdVelocities=birdVelocity;

    }
}
