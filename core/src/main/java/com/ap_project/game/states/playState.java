package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class playState extends abstractState implements Screen {
    private Texture background;
    final Core game;
    OrthographicCamera camera;
    private Texture ground;
    //private Texture pauseBtn;

    private float pauseBtnX;
    private float pauseBtnY;
    private float pauseBtnWidth;
    private float pauseBtnHeight;


    public playState(gameStateManager gsm, Core game){
        super(gsm);
        this.game = game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("playScreenbg.png");
        ground=new Texture("ground.png");
        //pauseBtn=new Texture("pauseBtn.png");

        //pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        //pauseBtnHeight = pauseBtn.getHeight() * 0.09f;


        pauseBtnX = 30;
        pauseBtnY = Core.HEIGHT - pauseBtnHeight - 30;
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

//            if (touchPos.x >= pauseBtnX && touchPos.x <= pauseBtnX + pauseBtnWidth &&
//                touchPos.y >= pauseBtnY && touchPos.y <= pauseBtnY + pauseBtnHeight) {
            game.setScreen(new pauseState(gsm,game));
//                dispose();
//            }

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
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 90, Core.WIDTH, Core.HEIGHT);
        game.batch.draw(ground, 0, 0, Core.WIDTH, ground.getHeight());
        //game.batch.draw(pauseBtn, pauseBtnX, pauseBtnY, pauseBtnWidth, pauseBtnHeight);

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
        ground.dispose();
        //pauseBtn.dispose();
    }
}
