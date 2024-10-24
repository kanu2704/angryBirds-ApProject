package com.ap_project.game;

import com.ap_project.game.states.gameStateManager;
import com.ap_project.game.states.menuState;
import com.ap_project.game.states.openingState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
    public static final int WIDTH=1280;
    public static final int HEIGHT=720;

    public static final String TITLE="angry Bird";
    public SpriteBatch batch;
    public BitmapFont font;
    private gameStateManager gsm;


    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        //setting to the main menu screen here;
        gsm=new gameStateManager();
        openingState openingState=new openingState(gsm,this);
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
