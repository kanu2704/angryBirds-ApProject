package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


public class playState extends abstractState implements Screen {
    private final Texture background;
    final Core game;
    OrthographicCamera camera;
    private final Texture ground;
    private final Texture pauseBtn;
    private block[] blocks;
    private pig[] pigs;
    private bird[] birds;


    private final float pauseBtnX;
    private final float pauseBtnY;
    private final float pauseBtnWidth;
    private final float pauseBtnHeight;


    public playState(Core game){
        super();
        this.game = game;
        this.blocks=new block[14];
        this.pigs=new pig[5];
        this.birds=new bird[3];

        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("background.jpg");
        ground=new Texture("ground1.png");
        pauseBtn=new Texture("pauseBtn.png");
        pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        pauseBtnHeight = pauseBtn.getHeight() * 0.09f;
        blocks[0]=new woodenBlock("wb1.png");
        blocks[5]=new woodenBlock("wb6.png");
        blocks[9]=new woodenBlock("wb10.png");

        //adding the pigs and birds here
        pigs[0]=new pig1("pig1.png");
        pigs[1]=new pig2("pig2.png");
        pigs[2]=new pig3("pig3.png");
        pigs[3]=new pig1("pig1.png");
        pigs[4]=new pig2("pig2.png");

        birds[0]=new redBird("redBird.png");
        birds[1]=new chuck("chuck.png");
        birds[2]=new bomb("bomb.png");
        //
        pauseBtnX = 30;
        pauseBtnY = Core.HEIGHT - pauseBtnHeight - 30;
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= pauseBtnX && touchPos.x <= pauseBtnX + pauseBtnWidth &&
                touchPos.y >= pauseBtnY && touchPos.y <= pauseBtnY + pauseBtnHeight) {
                game.setScreen(new pauseState(game));
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
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);
        game.batch.draw(ground, 0, 0, ground.getWidth()*0.5f, ground.getHeight()*0.5f);
        game.batch.draw(pauseBtn, pauseBtnX, pauseBtnY, pauseBtnWidth, pauseBtnHeight);

        //drawing the block structure
        game.batch.draw(blocks[0].getBlockTexture(),1000,100,blocks[0].width,blocks[0].height);
        game.batch.draw(blocks[5].getBlockTexture(),1000+50+25,100,blocks[5].width,blocks[5].height);
        game.batch.draw(blocks[5].getBlockTexture(), (float) (1000+50+25), (float) (100+73),blocks[5].width,blocks[5].height);
        game.batch.draw(blocks[0].getBlockTexture(),1000+100+25+25,100,blocks[0].width,blocks[0].height);
        game.batch.draw(new TextureRegion(blocks[0].getBlockTexture()), (float) (1000-(blocks[0].height-87.5)/2+30), (float) (100F+blocks[0].height/2+10),blocks[0].width / 2f, blocks[0].height / 2f,blocks[0].width,blocks[0].height,1f,1f,90);
        game.batch.draw(new TextureRegion(blocks[0].getBlockTexture()), (float) (1087.5-(blocks[0].height-87.5)/2+90), (float) (100F+blocks[0].height/2+10),blocks[0].width / 2f, blocks[0].height / 2f,blocks[0].width,blocks[0].height,1f,1f,90);
        game.batch.draw(blocks[9].getBlockTexture(),1000,100+20+blocks[0].height,blocks[9].width,blocks[9].height);
        game.batch.draw(blocks[9].getBlockTexture(),1075,100+20+blocks[0].height,blocks[9].width,blocks[9].height);
        game.batch.draw(blocks[9].getBlockTexture(),1150,100+20+blocks[0].height,blocks[9].width,blocks[9].height);
        //block structure ends
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
        pauseBtn.dispose();
    }
}
