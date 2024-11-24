package com.ap_project.game;

import com.ap_project.game.states.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.World;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;
    public static resultState2 resultScreen2;
    private static World world;
    public static final String TITLE="angry Bird";
    public SpriteBatch batch;
    public BitmapFont font;
    public static playState playScreen;
    public static openingState openingScreen;
    public static levelState levelScreen;
    public static pauseState pauseScreen;
    public static menuState menuScreen;
    public static resultState resultScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        openingState openingState=new openingState(this);
        openingScreen=openingState;
        this.setScreen(openingState);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
