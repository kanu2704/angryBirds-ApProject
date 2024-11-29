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
    //final private Texture soundOffBtn;

    boolean soundOn=true;

    //  play button position attributes
    private float playBtnX;
    private float playBtnY;
    private float playWidth;
    private float playHeight;

    private float soundOnBtnX;
    private float soundOnBtnY;
    private float soundOnBtnWidth;
    private float soundOnBtnHeight;
//
//    private float soundOffBtnX;
//    private float soundOffBtnY;
//    private float soundOffBtnWidth;
//    private float soundOffBtnHeight;

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
        this.totalSaveGame=new totalSaveGame();
        background = new Texture("background.jpg");
        heading = new Texture("heading.png");
        playBtn = new Texture("playBtn.png");
        playWidth = playBtn.getWidth() * 0.2f;
        playHeight = playBtn.getHeight() * 0.2f;
        playBtnX = (Core.WIDTH - playWidth) / 2 - 20;
        playBtnY = (Core.HEIGHT - playHeight) / 2;


        soundOnBtn=new Texture("soundOnBtn.png");
        //soundOffBtn=new Texture("soundOffBtn.png");
        soundOnBtnX=30;
        soundOnBtnY=30;
        soundOnBtnWidth=soundOnBtn.getWidth()*0.2f;
        soundOnBtnHeight=soundOnBtn.getHeight()*0.2f;
//
//        soundOffBtnX=30;
//        soundOffBtnY=30;
//        soundOffBtnWidth=soundOffBtn.getWidth()*0.4f;
//        soundOffBtnHeight=soundOffBtn.getHeight()*0.4f;
        //createBtn();
        exitButton=new Texture("exitButton.png");
        exitWidth=exitButton.getWidth()*0.25f;
        exitHeight=exitButton.getHeight()*0.25f;
        exitBtnX=Core.WIDTH-50f-exitWidth;
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
                Gdx.app.exit();
            }else if(touchPos.x >= soundOnBtnX && touchPos.x<= (soundOnBtnX + soundOnBtnWidth) &&
                touchPos.y >= soundOnBtnY && touchPos.y <= (soundOnBtnY + soundOnBtnHeight)){
                if(soundOn){
                    game.backgroundMusic.pause();
                    soundOnBtn=new Texture("soundOffBtn.png");
                    soundOn=false;
                }else{
                    game.backgroundMusic.play();
                    soundOnBtn=new Texture("soundOnBtn.png");
                    soundOn=true;
                }
            }
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }
    public void createBtn(){
        soundBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(soundOnBtn)));
        soundBtn.setSize(soundOnBtnWidth, soundOnBtnHeight);
        soundBtn.setPosition(soundOnBtnX,soundOnBtnY);
        soundBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
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
        game.batch.draw(exitButton,exitBtnX,exitBtnY,exitWidth,exitHeight);
        //game.batch.draw(soundOnBtn,soundOnBtnX,soundOnBtnY,soundOnBtnWidth,soundOnBtnHeight);
//        soundBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(soundOnBtn)));
//        soundBtn.setSize(soundOnBtnWidth, soundOnBtnHeight);
//        soundBtn.setPosition(soundOnBtnX,soundOnBtnY);
//        soundBtn.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//
//            }
//        });
        createBtn();
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
        exitButton.dispose();
        soundOnBtn.dispose();
    }
}
