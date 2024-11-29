package com.ap_project.game;

import com.ap_project.game.states.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serial;
import java.io.Serializable;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game implements Serializable {
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;
    public static resultState2 resultScreen2;
    private transient static World world;
    public static final String TITLE="angry Bird";
    public static transient SpriteBatch batch;
    public static int currentLevel;
    private totalSaveGame totalSaveGame;
    public Music backgroundMusic;


    @Override
    public void create() {
        totalSaveGame=new totalSaveGame();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
        Integer level=totalSaveGame.loadCurrentLevel();
        if(level==null){
            currentLevel=1;
        }else{
            currentLevel=(int) level;
        }
        batch = new SpriteBatch();
        openingState openingState=new openingState(this);
        this.setScreen(openingState);
    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
