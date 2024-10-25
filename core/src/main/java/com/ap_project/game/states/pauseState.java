package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class pauseState extends abstractState implements Screen {
    private Texture background;
    private OrthographicCamera camera;  // Initialize this camera
    final Core game;

    public pauseState(Core game) {
        super();
        this.game = game;
        background = new Texture("pauseScreen.jpg");

        // Initialize the camera and set its projection
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Core.WIDTH, Core.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            // Capture the touch position
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            // Add logic to handle touch on pause screen (e.g., resume game or exit)
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);  // Draw the background
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
    }
}

