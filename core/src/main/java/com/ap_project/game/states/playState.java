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
    private slingshot slingShot;

    //temperory buttons
    private final Texture winBtn;
    private final Texture loseBtn;

    private final float pauseBtnX;
    private final float pauseBtnY;
    private final float pauseBtnWidth;
    private final float pauseBtnHeight;
    private final float winBtnX;
    private final float winBtnY;
    private final float winBtnWidth;
    private final float winBtnHeight;
    private final float loseBtnX;
    private final float loseBtnY;
    private final float loseBtnWidth;
    private final float loseBtnHeight;


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
        slingShot=new slingshot(400f,105f);
        pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        pauseBtnHeight = pauseBtn.getHeight() * 0.09f;
        blocks[0]=new woodenBlock("wb1.png");
        blocks[5]=new woodenBlock("wb6.png");
        blocks[9]=new woodenBlock("wb10.png");

        //adding the pigs and birds here
        pigs[0]=new pig1("pig1a.png");
        pigs[1]=new pig2("pig2a.png");
        pigs[2]=new pig3("pig3a.png");
        pigs[3]=new pig1("pig1a.png");
        pigs[4]=new pig2("pig2a.png");

        birds[0]=new redBird("redBird.png");
        birds[1]=new chuck("chuck.png");
        birds[2]=new bomb("bomb.png");
        //
        winBtn=new Texture("winBtn.png");
        loseBtn=new Texture("loseBtn.png");
        pauseBtnX = 30;

        pauseBtnY = Core.HEIGHT - pauseBtnHeight - 30;
        winBtnWidth = winBtn.getWidth() * 0.2f;
        winBtnHeight = winBtn.getHeight() * 0.2f;
        winBtnX = pauseBtnX-5;
        winBtnY = pauseBtnY - winBtnHeight - 5;

        loseBtnWidth = loseBtn.getWidth() * 0.2f;
        loseBtnHeight = loseBtn.getHeight() * 0.2f;
        loseBtnX = pauseBtnX;
        loseBtnY = winBtnY - loseBtnHeight - 5;

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
        game.batch.draw(winBtn,winBtnX,winBtnY,winBtn.getWidth()*0.21f,winBtn.getHeight()*0.21f);
        game.batch.draw(loseBtn,loseBtnX,loseBtnY,loseBtn.getWidth()*0.21f,loseBtn.getHeight()*0.21f);

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
        //drawing pigs
        game.batch.draw(pigs[0].getPigTexture(),1025,100,pigs[0].width,pigs[0].height);
        game.batch.draw(pigs[4].getPigTexture(),1100,100,pigs[4].width,pigs[4].height);
        game.batch.draw(pigs[2].getPigTexture(),1000-20,115+blocks[0].height+blocks[9].height,pigs[2].width,pigs[2].height);
        game.batch.draw(pigs[1].getPigTexture(),1075-10,115+blocks[0].height+blocks[9].height,pigs[1].width,pigs[1].height);
        game.batch.draw(pigs[3].getPigTexture(),1150,115+blocks[0].height+blocks[9].height,pigs[3].width,pigs[3].height);
        //
        //drawing birds
        game.batch.draw(birds[0].getBirdTexture(),170,105,birds[0].width,birds[0].height);
        game.batch.draw(birds[1].getBirdTexture(),130,105,birds[1].width,birds[1].height);
        game.batch.draw(birds[2].getBirdTexture(),90,105,birds[2].width,birds[2].height);

        //drawing slingshot
        game.batch.draw(slingshot.getslingFrontTexture(),200,100,slingshot.slingFront.getWidth()*0.3f,slingshot.slingFront.getHeight()*0.3f);
        game.batch.draw(slingshot.getslingBackTexture(),185,150,slingshot.slingBack.getWidth()*0.3f,slingshot.slingBack.getHeight()*0.3f);

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
