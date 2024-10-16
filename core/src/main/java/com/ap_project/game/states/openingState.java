package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class openingState extends abstractState implements Screen{
    private Texture background;
    final Core game;
    OrthographicCamera camera;
    //FitViewport viewport;


    public openingState(gameStateManager gsm, Core game){
        super(gsm);
        this.game = game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,800,800);


        background=new Texture("angry-birds-image.png");
        //playBtn=new Texture("playbtn.png");
    }
    //later
    @Override
    protected void handleInput() {
        //if(Gdx.input.justTouched()){
        //gsm.set(new playState(gsm));
        //}

    }
    //later
    @Override
    protected void update(float dt) {
        //handleInput();

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background,0,0,Core.WIDTH, Core.HEIGHT);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);

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
