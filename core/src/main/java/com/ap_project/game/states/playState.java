package com.ap_project.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class playState extends abstractState implements Screen {
    private Texture background;
    public playState(gameStateManager gsm){
        super(gsm);
        //background=new Texture();
    }
    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta){
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