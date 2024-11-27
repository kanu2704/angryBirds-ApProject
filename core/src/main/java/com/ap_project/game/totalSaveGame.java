package com.ap_project.game;
import java.io.*;

public class totalSaveGame {

    private static final String SAVE_FILE = "coreCurrentLevel.dat"; // File to store the current level

    public void saveCurrentLevel(int level) {
        String saveFilePath = getSaveFilePath();
        System.out.println(saveFilePath);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeInt(level); // Write the level to the file
            System.out.println("Current level saved: " + level);
        } catch (IOException e) {
            System.err.println("Error saving the current level: " + e.getMessage());
        }
    }

    public Integer loadCurrentLevel() {
        String saveFilePath = getSaveFilePath();
        int level = 1; // Default to level 1 if no file is found

        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
                System.out.println("New save file created.");
            } catch (IOException e) {
                System.err.println("Error creating save file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFilePath))) {
                level = ois.readInt();
                System.out.println("Current level loaded: " + level);
            } catch (EOFException e) {
                System.out.println("Corrupted save file. Starting from level 1.");
            } catch (IOException e) {
                System.err.println("Error loading the current level: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return level;
    }
    private String getSaveFilePath() {
        String userDir = System.getProperty("user.dir");
        String saveDirPath = userDir + File.separator + "saves";
        String saveFilePath = saveDirPath + File.separator + SAVE_FILE;
        // Ensure the saves directory exists
        File saveDir = new File(saveDirPath);
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveFilePath;
    }
}
