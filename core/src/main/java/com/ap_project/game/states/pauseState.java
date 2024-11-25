package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class pauseState extends abstractState implements Screen {
    private Texture background;
    private OrthographicCamera camera;
    private final Core game;
    private final Texture crossBtn;
    private final Texture resumeBtn;
    private final Texture saveAndExitBtn;
    private final Texture exitBtn;

    public pauseState(Core game) {
        super();
        this.game = game;
        background = new Texture("pausebg.png");
        crossBtn = new Texture("crossBtn.png");
        resumeBtn = new Texture("resumeBtn.png");
        saveAndExitBtn = new Texture("saveAndExitBtn.png");
        exitBtn = new Texture("exitBtn.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Core.WIDTH, Core.HEIGHT);
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            float resumeBtnX = 550, resumeBtnY = 400;
            float saveAndExitBtnX = 525, saveAndExitBtnY = 250;
            float exitBtnX = 550, exitBtnY = 150;
            float crossBtnX = 910, crossBtnY = 610;

            if (touchPos.x >= resumeBtnX && touchPos.x <= resumeBtnX + resumeBtn.getWidth() * 0.4f &&
                touchPos.y >= resumeBtnY && touchPos.y <= resumeBtnY + resumeBtn.getHeight() * 0.4f) {
                game.setScreen(Core.playScreen);
                dispose();
            }
            if (touchPos.x >= saveAndExitBtnX && touchPos.x <= saveAndExitBtnX + saveAndExitBtn.getWidth() * 0.4f &&
                touchPos.y >= saveAndExitBtnY && touchPos.y <= saveAndExitBtnY + saveAndExitBtn.getHeight() * 0.4f) {
                game.setScreen(new menuState(game));
                dispose();
            }
            if (touchPos.x >= exitBtnX && touchPos.x <= exitBtnX + exitBtn.getWidth() * 0.4f &&
                touchPos.y >= exitBtnY && touchPos.y <= exitBtnY + exitBtn.getHeight() * 0.4f) {
                game.setScreen(new menuState(game));
                dispose();
            }
            if (touchPos.x >= crossBtnX && touchPos.x <= crossBtnX + crossBtn.getWidth() * 0.4f &&
                touchPos.y >= crossBtnY && touchPos.y <= crossBtnY + crossBtn.getHeight() * 0.4f) {
                game.setScreen(Core.playScreen);
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
    public void show() {
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);
        game.batch.draw(resumeBtn, 550, 400, resumeBtn.getWidth() * 0.4f, resumeBtn.getHeight() * 0.4f);
        game.batch.draw(saveAndExitBtn, 525, 250, saveAndExitBtn.getWidth() * 0.4f, saveAndExitBtn.getHeight() * 0.4f);
        game.batch.draw(exitBtn, 550, 150, exitBtn.getWidth() * 0.4f, exitBtn.getHeight() * 0.4f);
        game.batch.draw(crossBtn, 910, 610, crossBtn.getWidth() * 0.4f, crossBtn.getHeight() * 0.4f);
        game.batch.end();
        update(delta);
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {
        if (background != null) background.dispose();
        if (resumeBtn != null) resumeBtn.dispose();
        if (saveAndExitBtn != null) saveAndExitBtn.dispose();
        if (exitBtn != null) exitBtn.dispose();
        if (crossBtn != null) crossBtn.dispose();
    }
}
