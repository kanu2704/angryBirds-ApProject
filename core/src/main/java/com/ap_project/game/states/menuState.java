package com.ap_project.game.states;
import com.ap_project.game.totalSaveGame;
import com.badlogic.gdx.physics.box2d.World;
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
    private Texture heading;
    final private Texture exitButton;

    //  play button position attributes
    private float playBtnX;
    private float playBtnY;
    private float playWidth;
    private float playHeight;

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

        background = new Texture("background.jpg");
        heading = new Texture("heading.png");
        playBtn = new Texture("playBtn.png");
        playWidth = playBtn.getWidth() * 0.2f;
        playHeight = playBtn.getHeight() * 0.2f;
        playBtnX = (Core.WIDTH - playWidth) / 2 - 20;
        playBtnY = (Core.HEIGHT - playHeight) / 2;

        exitButton=new Texture("exitButton.png");
        exitWidth=exitButton.getWidth()*0.5f;
        exitHeight=exitButton.getHeight()*0.5f;
        exitBtnX=Core.WIDTH-50f;
        exitBtnY=20;
    }



    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= playBtnX && touchPos.x<= (playBtnX + playWidth) &&
                touchPos.y >= playBtnY && touchPos.y <= (playBtnY + playHeight)) {
                levelState levelState=new levelState(game);
                game.setScreen(levelState);
                dispose();
            }else if(touchPos.x>exitBtnX && touchPos.x<=exitBtnX+exitWidth && touchPos.y>=exitBtnY && touchPos.y<=exitBtnY+exitHeight){
                totalSaveGame.saveCurrentLevel(Core.currentLevel);
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
        game.batch.begin();
        game.batch.draw(background, 0, 0, 1280, 720);
        float headingWidth = heading.getWidth() * 0.4f;
        float headingHeight = heading.getHeight() * 0.4f;
        game.batch.draw(heading,
            ((Core.WIDTH - headingWidth) / 2)-15,
            Core.HEIGHT - headingHeight - 50,
            headingWidth, headingHeight);
        game.batch.draw(playBtn, playBtnX, playBtnY, playWidth, playHeight);
        game.batch.end();
        handleInput();
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
        background.dispose();
        heading.dispose();
        playBtn.dispose();
    }
}
