package com.ap_project.game.states;
import com.badlogic.gdx.physics.box2d.World;
import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;


public class openingState extends abstractState implements Screen{
    private Texture background;
    final Core game;
    private OrthographicCamera camera;
    //private Music backgroundMusic;
    private float elapsedTime = 0; // Timer to track time
    private float fadeAlpha = 1f;  // Alpha for fading effect
    private float fadeDuration = 2f;  // Duration of the fade-out in seconds
    private float showTime = 2f;

    public openingState(Core game){
        super();
        this.game = game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("angryBirds opening.png");
//        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.ogg"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.setVolume(0.5f);
//        backgroundMusic.play();

    }

    @Override
    protected void handleInput() {
        menuState menu=new menuState(game);
        if (fadeAlpha <= 0) {
            game.setScreen(menu);
            dispose();
        }
    }

    //later
    @Override
    protected void update(float dt) {
        elapsedTime += dt;
        if (elapsedTime >= showTime) {
            fadeAlpha -= dt / fadeDuration;
            if (fadeAlpha < 0) {
                fadeAlpha = 0;
            }
            handleInput();
        }
    }

    @Override
    public void create() {

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.setColor(1, 1, 1, fadeAlpha);
        game.batch.draw(background,0,0,Core.WIDTH,Core.HEIGHT);
        game.batch.setColor(1, 1, 1, 1);
        game.batch.end();
        update(v);

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

    }

    @Override
    public void dispose() {
        if (background != null) {
            background.dispose();
        }
    }

}
