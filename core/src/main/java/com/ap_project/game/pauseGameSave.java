package com.ap_project.game;

import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.ap_project.game.sprites.slingshot;
import com.ap_project.game.states.playState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class pauseGameSave{
    public void saveGameState(ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs, ArrayList<bird<?>> birds, int currentBirdIndex,Core game,int playingLevel,float timeGap,float gameWinTimer,float gameLoseTimer,Array<Body> bodies) {
        ArrayList<Vector2> BirdPositions = new ArrayList<>();
        for (bird<?> b : birds) {
            BirdPositions.add(b.getPosition());
        }
        ArrayList<Vector2> BlockPositions = new ArrayList<>();
        for (block<?> b : blocks) {
            BlockPositions.add(b.getPosition());
        }
        ArrayList<Vector2> PigPositions = new ArrayList<>();
        for (pig<?> p : pigs) {
            PigPositions.add(p.getPosition());
        }
        ArrayList<Integer> PigHealth = new ArrayList<>();
        for (pig<?> p : pigs) {
            PigHealth.add(p.getHits());
        }
        ArrayList<Vector2> birdVelocity = new ArrayList<>();
        for (bird<?> p : birds) {
            birdVelocity.add(p.getVelocity());
        }
        ArrayList<Vector2> blockVelocity = new ArrayList<>();
        for (block<?> p : blocks) {
            blockVelocity.add(p.getVelocity());
        }
        ArrayList<Vector2> pigVelocity = new ArrayList<>();
        for (pig<?> p :pigs) {
            pigVelocity.add(p.getVelocity());
        }
        gameData gameData = new gameData(blocks,pigs,birds,currentBirdIndex,BlockPositions,BirdPositions,PigPositions,PigHealth,playingLevel,game,timeGap,gameWinTimer,gameLoseTimer,bodies,birdVelocity,blockVelocity,pigVelocity);
        try {
            FileHandle file = Gdx.files.local("pauseData.dat");
            FileOutputStream fileOutputStream = new FileOutputStream(file.file());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(gameData);
            System.out.println("data saved...");
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public playState loadGameState() {
        // Load game data from the saved file
        try {
            FileHandle file = Gdx.files.local("pauseData.dat");
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file.file());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                gameData gameData = (gameData) objectInputStream.readObject();
                System.out.println("data loading....");
                objectInputStream.close();
                playState playScreen = getPlayState(gameData);
                for (int i = 0; i < gameData.birds.size(); i++) {
                    playScreen.getBirds().get(i).setPosition(gameData.birdPositions.get(i));
                    playScreen.getBirds().get(i).setVelocity(gameData.birdVelocities.get(i));
                }
                for (int i = 0; i < gameData.blocks.size(); i++) {
                    playScreen.getBlocks().get(i).setPosition(gameData.blockPositions.get(i));
                    playScreen.getBlocks().get(i).setVelocity(gameData.blockVelocities.get(i));
                }
                for (int i = 0; i < gameData.pigs.size(); i++) {
                    playScreen.getPigs().get(i).setPosition(gameData.pigPositions.get(i));
                    playScreen.getPigs().get(i).setHits(gameData.pigHealth.get(i));
                    playScreen.getPigs().get(i).setVelocity(gameData.pigVelocities.get(i));
                }
                return playScreen;
            } else {
                System.out.println("No saved game found. Starting a new game.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static playState getPlayState(gameData gameData) {
        playState playScreen= new playState(gameData.game, gameData.playingLevel);
        playScreen.setCurrentBirdIndex(gameData.currentBirdIndex);
        playScreen.setGameWinTimer(gameData.gameWinTimer);
        playScreen.setGameLoseTimer(gameData.gameLoseTimer);
        playScreen.setTimeGap(gameData.timeGap);
        playScreen.setBirds(gameData.birds);
        playScreen.setBlocks(gameData.blocks);
        playScreen.setPigs(gameData.pigs);
        playScreen.setBodiesToDestroy(gameData.bodiesToDestroy);
        playScreen.setBlockPositions(gameData.blockPositions);
        playScreen.setBirdGroundPositions(gameData.birdPositions);
        playScreen.setPigPositions(gameData.pigPositions);
        return playScreen;
    }

}
