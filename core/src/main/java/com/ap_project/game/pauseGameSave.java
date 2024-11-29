package com.ap_project.game;

import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.ap_project.game.states.playState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class pauseGameSave implements Serializable{
    private static HashMap<Integer, gameData> levelWiseGameData=new HashMap<>();
    public void saveGameState(ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs, ArrayList<bird<?>> birds, int currentBirdIndex, int playingLevel, float timeGap, float gameWinTimer, float gameLoseTimer, ArrayList<Object> bodies, List<block<?>> blocksDestroyed, List<pig<?>> pigsDestroyed) {
        ArrayList<Vector2> BirdPositions = new ArrayList<>();
        for (bird<?> b : birds) {
            BirdPositions.add(b.getPosition());
        }
        ArrayList<Vector2> BlockPositions = new ArrayList<>();
        System.out.println("block size beofr saving the game :"+blocks.size());
        for (block<?> b : blocks) {
            if(!b.isDestroyed()){
                BlockPositions.add(b.getPosition());
            }else{
                BlockPositions.add(null);
            }
        }
        ArrayList<Vector2> PigPositions = new ArrayList<>();
        for (pig<?> p : pigs) {
            if(!p.isDestroyed()){
                PigPositions.add(p.getPosition());
            }else{
                PigPositions.add(null);
            }
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
            if(!b.isDestroyed()){
                blockVelocity.add(b.getVelocity());
            }else{
                blockVelocity.add(null);
            }
        }
        System.out.println("before saving the block velocities :"+blockVelocity.size());
        ArrayList<Vector2> pigVelocity = new ArrayList<>();
        for (pig<?> p :pigs) {
            if(!p.isDestroyed()){
                pigVelocity.add(p.getVelocity());
            }else{
                pigVelocity.add(null);
            }
        }
        gameData gameData = new gameData(blocks,pigs,birds,currentBirdIndex,BlockPositions,BirdPositions,PigPositions,PigHealth,timeGap,gameWinTimer,gameLoseTimer,blockVelocity,birdVelocity,pigVelocity,bodies,blocksDestroyed,pigsDestroyed);
        saveLevelWise(playingLevel,gameData);
    }
    private static playState getPlayState(gameData gameData,Core game,int level) {
        System.out.println("im hereeee");
        playState playScreen= new playState(game, level,gameData,false);
        return playScreen;
    }
    public void saveLevelWise(int playingLevel, gameData gameData) {
        //String saveLevelFile=getSaveFilePath("level_" + playingLevel + "_data.dat");
        File levelFile=new File("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\level_"+playingLevel+"_data.dat");
        //String levelMapFilePath=getSaveFilePath("levelWiseData.dat");

        File levelMapFile=new File("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\levelWiseData.dat");
        //System.out.println(levelMapFilePath);
        try {
            FileOutputStream gameDataOutputStream = new FileOutputStream(levelFile);
            ObjectOutputStream gameDataObjectOutputStream = new ObjectOutputStream(gameDataOutputStream);
            gameDataObjectOutputStream.writeObject(gameData);
            gameDataObjectOutputStream.close();
            System.out.println("Game data for level " + playingLevel + " saved successfully.");
            levelWiseGameData.put(playingLevel, null);
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
        File levelMapFile = new File("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\levelWiseData.dat");

        try {
            if (levelMapFile.exists() && levelMapFile.length() > 0) {
                try (FileInputStream levelMapInputStream = new FileInputStream("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\levelWiseData.dat");
                     ObjectInputStream levelMapObjectInputStream = new ObjectInputStream(levelMapInputStream)) {
                    levelWiseGameData = (HashMap<Integer, gameData>) levelMapObjectInputStream.readObject();
                    System.out.println("Level-wise metadata loaded successfully.");
                } catch (EOFException e) {
                    System.out.println("File is empty: " +"C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\levelWiseData.dat");
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error reading level metadata: " + e.getMessage());
                }
            } else {
                System.out.println("No level-wise metadata found or file is empty.");
            }

            File levelSaved = new File("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\level_"+playingLevel+"_data.dat");

            if (levelSaved.exists() && levelSaved.length() > 0) {
                try (FileInputStream gameDataInputStream = new FileInputStream("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\level_"+playingLevel+"_data.dat");
                     ObjectInputStream gameDataObjectInputStream = new ObjectInputStream(gameDataInputStream)) {
                    gameData gameData = (gameData) gameDataObjectInputStream.readObject();
                    System.out.println("Game data for level " + playingLevel + " loaded successfully.");
                    Core.batch = new SpriteBatch();
                    playState playScreen = getPlayState(gameData,game,playingLevel);
                    System.out.println("After loading ....");
//                    System.out.println("birds size: "+ gameData.birds.size());
//                    System.out.println("birds velocity size: "+ gameData.birdVelocities.size());
//                    System.out.println("blocksDestroyed size: "+ gameData.blocksDestroyed.size());
//                    System.out.println("pigsDestroyed size: "+ gameData.pigsDestroyed.size());
                    clearFile("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\level_"+playingLevel+"_data.dat");
                    return playScreen;
                } catch (EOFException e) {
                    System.out.println("Game data for level " + playingLevel + " is empty or corrupted.");
                    playState playScreen = getPlayState(null,game,playingLevel);
                    clearFile("C:\\Users\\kanup\\Downloads\\school attachments\\cs\\AngryBirds\\saves\\level_"+playingLevel+"_data.dat");
                    return playScreen;

                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading game data for level " + playingLevel + ": " + e.getMessage());

                }
            } else {
                System.out.println("No game data found for level " + playingLevel + ".");
                playState playScreen = getPlayState(null,game,playingLevel);
                return playScreen;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private String getSaveFilePath(String fileName) {
//        String userDir = System.getProperty("user.dir");
//        String saveDirPath = userDir + File.separator + "saves";
//        String saveFilePath = saveDirPath + File.separator + fileName;
//        File saveDir = new File(saveDirPath);
//        if (!saveDir.exists()) {
//            saveDir.mkdirs();
//        }
//        return saveFilePath;
//    }
    private void clearFile(String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(new byte[0]);
            System.out.println("File cleared: " + filePath);
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }
    }
}
