package com.ap_project.game.states;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import java.util.*;

public class menuState extends abstractState implements Screen{
    private Texture background;
    private Texture playBtn;

    public menuState(){
        background=new Texture("bg.png");
        playBtn=new Texture("playbtn.png");
    }
    //later
    @Override
    protected void handleInput() {

    }
    //later
    @Override
    protected void update(float dt) {

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float v) {

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
