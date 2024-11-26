package com.ap_project.game.states;
import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;


public class resultState2 extends abstractState implements Screen {
    final private Texture background;
    final Core game;
    final private OrthographicCamera camera;
    final private Texture homeBtn;
    final private Texture playAgain;


    public resultState2(Core game){
        super();
        this.game=game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("losebg.png");
        homeBtn=new Texture("homeBtn.png");
        playAgain=new Texture("playAgain.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);
            System.out.println(touchPos.x+" "+touchPos.y);
            float homeX = 450;
            float homeY = 330;
            float homeWidth = homeBtn.getWidth() * 0.2f;
            float homeHeight = homeBtn.getHeight() * 0.2f;

            if (touchPos.x >= homeX && touchPos.x <= homeX + homeWidth &&
                touchPos.y >= homeY && touchPos.y <= homeY + homeHeight) {
                game.setScreen(new menuState(game));
            }

            float playAgainX = 750;
            float playAgainY = 320;
            float playAgainWidth = playAgain.getWidth() * 0.3f;
            float playAgainHeight = playAgain.getHeight() * 0.3f;

            if (touchPos.x >= playAgainX && touchPos.x <= playAgainX + playAgainWidth &&
                touchPos.y >= playAgainY && touchPos.y <= playAgainY + playAgainHeight) {
                int l=Core.playScreen.getPlayingLevel();
                playState newPlayScreen=new playState(game,l);
                Core.playScreen.dispose();
                Core.playScreen=newPlayScreen;
                game.setScreen(newPlayScreen);
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
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        game.batch.draw(homeBtn, 450, 330, homeBtn.getWidth() * 0.2f, homeBtn.getHeight() * 0.2f);
        game.batch.draw(playAgain, 750, 320, playAgain.getWidth() * 0.3f, playAgain.getHeight() * 0.3f);
        game.batch.end();

        update(v);

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
        homeBtn.dispose();
        playAgain.dispose();

    }
}

