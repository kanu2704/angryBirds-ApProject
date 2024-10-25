package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class levelState extends abstractState implements Screen {
    private Texture background;
    final Core game;
    private Texture backButton;
    //the issue with the buttons is that their size is a problem its covering a blank area for no reason we need to remove that
    private Texture level1Btn;
    private Texture level2Btn;
    private Texture level3Btn;

    final private float backWidth;
    final private float backHeight;
    final private float levelBtnWidth;
    final private float levelBtnHeight;
    public levelState(Core game){
        super();
        this.game = game;
        background=new Texture("levelSelectbg.png");
        backButton=new Texture("backButton.png");
        level1Btn = new Texture("level1btn.png");
        level2Btn = new Texture("level2btn.png");
        level3Btn = new Texture("level3btn.png");
        backWidth = backButton.getWidth() * 0.5f;
        backHeight = backButton.getHeight() * 0.5f;
        levelBtnHeight= level1Btn.getHeight();
        levelBtnWidth=level1Btn.getWidth();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            game.setScreen(new playState(game));
        }

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(backButton, 20, 720 - backHeight - 20, backWidth, backHeight);
        float spacing = 10f;
        //game.batch.draw(level1Btn,100,100, levelBtnWidth, levelBtnHeight);
        game.batch.draw(level2Btn, 100 + levelBtnWidth + spacing, 100, levelBtnWidth, levelBtnHeight);
        //game.batch.draw(level3Btn, 100 + 2 * (levelBtnWidth + spacing), 100, levelBtnWidth, levelBtnHeight);
        game.batch.end();
        handleInput();
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
        background.dispose();
        backButton.dispose();
        level1Btn.dispose();
        level2Btn.dispose();
        level3Btn.dispose();
    }
}
