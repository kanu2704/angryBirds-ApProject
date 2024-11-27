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

public class resultState extends abstractState implements Screen {
    private final Core game;
    final private OrthographicCamera camera;
    private final Texture background;
    private final Texture stars;
    private final Texture homeBtn;
    private final Texture playAgain;
    private final Texture nextLevelBtn;
    private int level;
    private pauseGameSave pauseGameSave;


    public resultState(Core game,int level){
        super();
        this.game = game;
        this.level=level;
        pauseGameSave=new pauseGameSave();
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background = new Texture("winbg.png");
        stars = new Texture("stars.png");
        homeBtn = new Texture("homeBtn.png");
        playAgain = new Texture("playAgain.png");
        nextLevelBtn = new Texture("nextLevelBtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);
            float homeX = 450;
            float homeY = 310;
            float homeWidth = homeBtn.getWidth() * 0.2f;
            float homeHeight = homeBtn.getHeight() * 0.2f;
            if (touchPos.x >= homeX && touchPos.x <= homeX + homeWidth &&
                touchPos.y >= homeY && touchPos.y <= homeY + homeHeight) {
                game.setScreen(new menuState(game));
                dispose();
            }
            //for next level
            float playAgainX = 750;
            float playAgainY = 300;
            float playAgainWidth = playAgain.getWidth() * 0.3f;
            float playAgainHeight = playAgain.getHeight() * 0.3f;
            if (touchPos.x >= playAgainX && touchPos.x <= playAgainX + playAgainWidth &&
                touchPos.y >= playAgainY && touchPos.y <= playAgainY + playAgainHeight) {
                if(Core.currentLevel==level){
                    Core.currentLevel++;
                }
                playState newPlayScreen=new playState(game,level,null);
                game.setScreen(newPlayScreen);
                dispose();
            }
            //for resuming the same Level
            float nextLevelX = 600;
            float nextLevelY = 300;
            float nextLevelWidth = nextLevelBtn.getWidth() * 0.3f;
            float nextLevelHeight = nextLevelBtn.getHeight() * 0.3f;
            if (touchPos.x >= nextLevelX && touchPos.x <= nextLevelX + nextLevelWidth &&
                touchPos.y >= nextLevelY && touchPos.y <= nextLevelY + nextLevelHeight) {
                if(Core.currentLevel==level){
                    Core.currentLevel++;
                }
                level++;
                playState newPlayScreen=pauseGameSave.loadLevelWiseGameData(level,game);
                //playState newPlayScreen=new playState(game,level,null);
                game.setScreen(newPlayScreen);
                dispose();
            }
        }
    }


    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void create() {

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(stars, 300, 300, stars.getWidth() * 0.5f, stars.getHeight() * 0.5f);
        game.batch.draw(homeBtn, 450, 310, homeBtn.getWidth() * 0.2f, homeBtn.getHeight() * 0.2f);
        game.batch.draw(nextLevelBtn, 600, 300, nextLevelBtn.getWidth() * 0.3f, nextLevelBtn.getHeight() * 0.3f);
        game.batch.draw(playAgain, 750, 300, playAgain.getWidth() * 0.3f, playAgain.getHeight() * 0.3f);
        game.batch.end();
        update(delta);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        stars.dispose();
        homeBtn.dispose();
        playAgain.dispose();
        nextLevelBtn.dispose();
    }
}

