package com.ap_project.game;

import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.ap_project.game.sprites.slingshot;
import com.ap_project.game.states.levelManager;
import com.ap_project.game.states.playState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class pauseGameSave implements Serializable{
    private static HashMap<Integer, gameData> levelWiseGameData=new HashMap<>();
    public void saveGameState(ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs, ArrayList<bird<?>> birds, int currentBirdIndex,int playingLevel,float timeGap,float gameWinTimer,float gameLoseTimer,ArrayList<Body> bodies) {
        ArrayList<Vector2> BirdPositions = new ArrayList<>();
        for (bird<?> b : birds) {
            BirdPositions.add(b.getPosition());
        }
        ArrayList<Vector2> BlockPositions = new ArrayList<>();
        System.out.println("block size beofr saving the game :"+blocks.size());
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
        for (block<?> b : blocks) {
            blockVelocity.add(b.getVelocity());
        }
        System.out.println("before saving the block velocities :"+blockVelocity.size());
        ArrayList<Vector2> pigVelocity = new ArrayList<>();
        for (pig<?> p :pigs) {
            pigVelocity.add(p.getVelocity());
        }
        gameData gameData = new gameData(blocks,pigs,birds,currentBirdIndex,BlockPositions,BirdPositions,PigPositions,PigHealth,timeGap,gameWinTimer,gameLoseTimer,bodies,blockVelocity,birdVelocity,pigVelocity);
        saveLevelWise(playingLevel,gameData);
    }

    private static playState getPlayState(gameData gameData,Core game,int level) {
        System.out.println("im hereeee");
        playState playScreen= new playState(game, level,gameData);
        return playScreen;
    }

    public void saveLevelWise(int playingLevel, gameData gameData) {
        String saveLevelFile=getSaveFilePath("level_" + playingLevel + "_data.dat");
        File levelFile=new File(saveLevelFile);
        String levelMapFilePath=getSaveFilePath("levelWiseData.dat");
        File levelMapFile=new File(levelMapFilePath);
        try {

            // Save gameData to a separate file for the level

            FileOutputStream gameDataOutputStream = new FileOutputStream(levelFile);
            ObjectOutputStream gameDataObjectOutputStream = new ObjectOutputStream(gameDataOutputStream);
            gameDataObjectOutputStream.writeObject(gameData);
            gameDataObjectOutputStream.close();
            System.out.println("Game data for level " + playingLevel + " saved successfully.");

            levelWiseGameData.put(playingLevel, null); // Use null to avoid storing the actual gameData in memory
            FileOutputStream levelMapOutputStream = new FileOutputStream(levelMapFile);
            ObjectOutputStream levelMapObjectOutputStream = new ObjectOutputStream(levelMapOutputStream);
            levelMapObjectOutputStream.writeObject(levelWiseGameData);
            levelMapObjectOutputStream.close();
            System.out.println("Level-wise metadata saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public playState loadLevelWiseGameData(int playingLevel,Core game) {
        String saveLevelDataPath = getSaveFilePath("levelWiseData.dat");
        File levelMapFile = new File(saveLevelDataPath);

        try {
            // Check if the level data file exists but is empty
            if (levelMapFile.exists() && levelMapFile.length() > 0) {
                try (FileInputStream levelMapInputStream = new FileInputStream(saveLevelDataPath);
                     ObjectInputStream levelMapObjectInputStream = new ObjectInputStream(levelMapInputStream)) {
                    levelWiseGameData = (HashMap<Integer, gameData>) levelMapObjectInputStream.readObject();
                    System.out.println("Level-wise metadata loaded successfully.");
                } catch (EOFException e) {
                    System.out.println("File is empty: " + saveLevelDataPath);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error reading level metadata: " + e.getMessage());
                }
            } else {
                System.out.println("No level-wise metadata found or file is empty.");
            }

            String savePATH = getSaveFilePath("level_" + playingLevel + "_data.dat");
            File levelSaved = new File(savePATH);

            if (levelSaved.exists() && levelSaved.length() > 0) {
                try (FileInputStream gameDataInputStream = new FileInputStream(savePATH);
                     ObjectInputStream gameDataObjectInputStream = new ObjectInputStream(gameDataInputStream)) {

                    gameData gameData = (gameData) gameDataObjectInputStream.readObject();
                    System.out.println("Game data for level " + playingLevel + " loaded successfully.");
                    playState playScreen = getPlayState(gameData,game,playingLevel);
                    Core.batch = new SpriteBatch();
                    // Log game data sizes
                    System.out.println("After loading ....");
                    System.out.println("birds size: " + gameData.birds.size());
                    System.out.println("birds velocity size: " + gameData.birdVelocities.size());
                    System.out.println("blocks size: " + gameData.blocks.size());
                    System.out.println("pigs size: " + gameData.pigs.size());
                    clearFile(savePATH);
                    return playScreen;
                } catch (EOFException e) {
                    System.out.println("Game data for level " + playingLevel + " is empty or corrupted.");
                    playState playScreen = getPlayState(null,game,playingLevel);
                    clearFile(savePATH);
                    return playScreen;

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading game data for level " + playingLevel + ": " + e.getMessage());

                }
            } else {
                System.out.println("No game data found for level " + playingLevel + ".");
                gameData gameData=null;
                playState playScreen = getPlayState(gameData,game,playingLevel);
                return playScreen;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSaveFilePath(String fileName) {
        String userDir = System.getProperty("user.dir");
        String saveDirPath = userDir + File.separator + "saves";
        String saveFilePath = saveDirPath + File.separator + fileName;
        File saveDir = new File(saveDirPath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveFilePath;
    }
    private void clearFile(String filePath) {
        // Open the file in write mode and truncate it to clear the contents
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(new byte[0]);  // Writing an empty byte array to clear the file
            System.out.println("File cleared: " + filePath);
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }
    }
}
