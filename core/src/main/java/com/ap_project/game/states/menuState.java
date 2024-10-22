package com.ap_project.game.states;
import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class menuState extends abstractState implements Screen {
    final private Texture background;
    final Core game;
    OrthographicCamera camera;
    final private Texture playBtn;
    private Texture levelSelectBtn;
    private Texture exitBtn;
    private Texture resumeBtn;
    private Texture heading;
    public playState play;

    // Add play button position attributes
    final private float playBtnX;
    final private float playBtnY;
    final private float playWidth;
    final private float playHeight;

    protected menuState(gameStateManager gsm, Core game) {
        super(gsm);
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        background = new Texture("background.png");
        heading = new Texture("heading.png");
        playBtn = new Texture("playBtn.png");

        // Set up the width and height of the play button (scaled down by 40%)
        playWidth = playBtn.getWidth() * 0.2f;
        playHeight = playBtn.getHeight() * 0.2f;

        // Set up the position of the play button (centered on screen)
        playBtnX = (Core.WIDTH - playWidth) / 2 - 20;
        playBtnY = (Core.HEIGHT - playHeight) / 2;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);


            if (touchPos.x >= playBtnX && touchPos.x <= playBtnX + playWidth &&
                touchPos.y >= playBtnY && touchPos.y <= playBtnY + playHeight) {
                playState play = new playState(gsm, game);
                game.setScreen(play);
                dispose();
            }

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
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);

        // Set the desired width and height for the heading
        float headingWidth = heading.getWidth() * 0.4f;
        float headingHeight = heading.getHeight() * 0.4f;

        // Draw the heading at the top, with the new size
        game.batch.draw(heading,
            ((Core.WIDTH - headingWidth) / 2)-20,
            Core.HEIGHT - headingHeight - 50,
            headingWidth, headingHeight);

        // Draw the play button
        game.batch.draw(playBtn, playBtnX, playBtnY, playWidth, playHeight);

        game.batch.end();
        update(delta);
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
        heading.dispose();
        playBtn.dispose(); // Dispose of the play button texture
    }
}
