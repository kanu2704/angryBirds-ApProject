package com.ap_project.game.states;
import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.*;

public class menuState extends abstractState implements Screen {
    private Texture background;
    final Core game;
    OrthographicCamera camera;
    private Texture playBtn;
    private Texture levelSelectBtn;
    private Texture exitBtn;
    private Texture resumeBtn;

    protected menuState(gameStateManager gsm, Core game) {
        super(gsm);
        this.game = game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,800,800);
        //background=new Texture();
    }

    @Override
    protected void handleInput() {
        //add here according to the button pressed;
        gsm=new gameStateManager();
        playState play=new playState(gsm,game);
        game.setScreen(play);
    }

    @Override
    protected void update(float dt) {

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background,0,0,Core.WIDTH, Core.HEIGHT);
        game.batch.end();
    }
    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
