package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.ap_project.game.pauseGameSave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class levelState extends abstractState implements Screen {
    final private Texture background;
    final Core game;
    final private OrthographicCamera camera;
    final private Texture backButton;
    final private Texture level1Btn;
    final private Texture level2Btn;
    final private Texture level3Btn;
    final private float backWidth;
    final private float backHeight;
    final private float levelBtnWidth;
    final private float levelBtnHeight;
    private pauseGameSave pauseGameSave;

    public levelState(Core game){;
        this.game = game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        this.pauseGameSave=new pauseGameSave();
        background = new Texture("levelSelectbg2.png");
        backButton = new Texture("backButton.png");
        level1Btn = new Texture("level1btn.png");
        level2Btn = new Texture("level2btn.png");
        level3Btn = new Texture("level3btn.png");
        backWidth = backButton.getWidth() * 0.5f;
        backHeight = backButton.getHeight() * 0.5f;
        levelBtnWidth = level1Btn.getWidth() * 0.7f;
        levelBtnHeight = level1Btn.getHeight() * 0.7f;
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);
            if(touchPos.x>=300 && touchPos.x<=300+level1Btn.getWidth() && touchPos.y>=300 && touchPos.y<=400+level1Btn.getHeight()){
                playState playScreen=pauseGameSave.loadLevelWiseGameData(1,game);
                game.setScreen(playScreen);
                dispose();
            }
            else if(touchPos.x>=420+levelBtnWidth+10f && touchPos.x<=420+levelBtnWidth+10f +levelBtnWidth && touchPos.y>=300 && touchPos.y<=400+level2Btn.getHeight()){
                if(Core.currentLevel>1){
                    System.out.println("clicked here...");
                    playState playScreen=pauseGameSave.loadLevelWiseGameData(2,game);
                    game.setScreen(playScreen);
                    dispose();
                }else{
                    System.out.println("previous levels not cleared......");
                }
            }
            else if(touchPos.x>=540 + 2 * (levelBtnWidth + 10f) && touchPos.x<=540 + 2 * (levelBtnWidth + 10f) +levelBtnWidth && touchPos.y>=300 && touchPos.y<=400+level2Btn.getHeight()){
                if(Core.currentLevel>2){
                    playState playScreen=pauseGameSave.loadLevelWiseGameData(3,game);
                    game.setScreen(playScreen);
                    dispose();
                }else{
                    System.out.println("previous levels not cleared.....");
                }
            }
            else if(touchPos.x >= 20 && touchPos.x <= 20 + backWidth && touchPos.y >= 720 - backHeight - 20 && touchPos.y <= 720 - 20){
                game.setScreen(new menuState(game));
                dispose();
            }
        }
    }

    @Override
    protected void update(float dt) {
    }

    @Override
    public void create() {

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
        game.batch.draw(level1Btn, 300, 300, levelBtnWidth, levelBtnHeight);
        game.batch.draw(level2Btn, 420 + levelBtnWidth + spacing, 300, levelBtnWidth, levelBtnHeight);
        game.batch.draw(level3Btn, 540 + 2 * (levelBtnWidth + spacing), 300, levelBtnWidth, levelBtnHeight);
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

