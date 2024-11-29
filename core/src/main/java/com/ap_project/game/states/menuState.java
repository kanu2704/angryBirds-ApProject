package com.ap_project.game.states;

import com.ap_project.game.totalSaveGame;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class menuState extends abstractState implements Screen {

    final private Texture background;
    final Core game;
    OrthographicCamera camera;
    final private Texture playBtn;
    private Texture heading;
    final private Texture exitButton;
    private Texture soundOnBtn;
    private ImageButton soundBtn;

    boolean soundOn = true;

    // Play button position attributes
    private float playBtnX;
    private float playBtnY;
    private float playWidth;
    private float playHeight;

    private float soundOnBtnX;
    private float soundOnBtnY;
    private float soundOnBtnWidth;
    private float soundOnBtnHeight;

    private float exitBtnX;
    private float exitBtnY;
    private float exitWidth;
    private float exitHeight;

    private totalSaveGame totalSaveGame;

    protected menuState(Core game) {
        super();
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Core.WIDTH, Core.HEIGHT);
        this.totalSaveGame = new totalSaveGame();

        background = new Texture("background.jpg");
        heading = new Texture("heading.png");
        playBtn = new Texture("playBtn.png");
        playWidth = playBtn.getWidth() * 0.2f;
        playHeight = playBtn.getHeight() * 0.2f;
        playBtnX = (Core.WIDTH - playWidth) / 2 - 20;
        playBtnY = (Core.HEIGHT - playHeight) / 2;

        soundOnBtn = new Texture("soundOnBtn.png");
        soundOnBtnX = 30;
        soundOnBtnY = 30;
        soundOnBtnWidth = soundOnBtn.getWidth() * 0.2f;
        soundOnBtnHeight = soundOnBtn.getHeight() * 0.2f;

        exitButton = new Texture("exitButton.png");
        exitWidth = exitButton.getWidth() * 0.25f;
        exitHeight = exitButton.getHeight() * 0.25f;
        exitBtnX = Core.WIDTH - 50f - exitWidth;
        exitBtnY = 20;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            // Handle Play Button Click
            if (touchPos.x >= playBtnX && touchPos.x <= (playBtnX + playWidth) &&
                touchPos.y >= playBtnY && touchPos.y <= (playBtnY + playHeight)) {
                levelState levelState = new levelState(game);
                game.setScreen(levelState);
                dispose();
            }
            // Handle Exit Button Click
            else if (touchPos.x > exitBtnX && touchPos.x <= exitBtnX + exitWidth &&
                touchPos.y >= exitBtnY && touchPos.y <= exitBtnY + exitHeight) {
                totalSaveGame.saveCurrentLevel(Core.currentLevel);
                dispose();
                Gdx.app.exit();
            }
            // Handle Sound Button Click
            else if (touchPos.x >= soundOnBtnX && touchPos.x <= (soundOnBtnX + soundOnBtnWidth) &&
                touchPos.y >= soundOnBtnY && touchPos.y <= (soundOnBtnY + soundOnBtnHeight)) {
                toggleSound();
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

    private void toggleSound() {
        if (soundOn) {
            game.backgroundMusic.pause();  // Pause music
            soundOnBtn = new Texture("soundOffBtn.png");  // Switch to sound off button texture
            soundOn = false;  // Set sound state to off
        } else {
            game.backgroundMusic.play();  // Play music
            soundOnBtn = new Texture("soundOnBtn.png");  // Switch to sound on button texture
            soundOn = true;  // Set sound state to on
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);  // Clear the screen with a color

        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);  // Draw background

        // Draw heading
        float headingWidth = heading.getWidth() * 0.4f;
        float headingHeight = heading.getHeight() * 0.4f;
        game.batch.draw(heading,
            ((Core.WIDTH - headingWidth) / 2) - 15,
            Core.HEIGHT - headingHeight - 50,
            headingWidth, headingHeight);

        // Draw the play, exit, and sound buttons
        game.batch.draw(playBtn, playBtnX, playBtnY, playWidth, playHeight);
        game.batch.draw(exitButton, exitBtnX, exitBtnY, exitWidth, exitHeight);
        game.batch.draw(soundOnBtn, soundOnBtnX, soundOnBtnY, soundOnBtnWidth, soundOnBtnHeight);

        game.batch.end();

        handleInput();  // Handle input events such as clicks
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing (optional)
    }

    @Override
    public void pause() {
        // Handle pause (optional)
    }

    @Override
    public void resume() {
        // Handle resume (optional)
    }

    @Override
    public void hide() {
        // Handle hide (optional)
    }

    @Override
    public void dispose() {
        background.dispose();
        heading.dispose();
        playBtn.dispose();
        exitButton.dispose();
        soundOnBtn.dispose();
    }
}
