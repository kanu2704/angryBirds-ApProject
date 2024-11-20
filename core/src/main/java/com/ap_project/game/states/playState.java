package com.ap_project.game.states;
import com.ap_project.game.Core;
import com.ap_project.game.physicsEngine;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;


public class playState extends abstractState implements Screen {
    private final Texture ground;
    private final Texture pauseBtn;
    private final Texture background;
    final Core game;
    OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private physicsEngine engine;
    private World world; // Box2D world
    private Array<Body> bodies;
    private Array<block> blocks;
    private Array<pig> pigs;
    private Array<bird> birds;
    private slingshot slingShot;
    private int currentBirdIndex;
    private final Vector2 slingPerchPosition = new Vector2(200, 209);
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
    private boolean isSlingEmpty;
    private boolean isMovingToPerch;

    public playState(Core game){
        super();
        this.game = game;
        this.world = new World(new Vector2(0, -9.8f), true);//
        this.blocks = new Array<>();
        this.pigs = new Array<>();
        this.birds = new Array<>();
        shapeRenderer = new ShapeRenderer();
        engine = new physicsEngine();
        currentBirdIndex = -1;
        this.isSlingEmpty=true;
        this.isMovingToPerch=false;

        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("background.jpg");
        ground=new Texture("ground1.png");
        pauseBtn=new Texture("pauseBtn.png");
        slingShot=new slingshot(400f,105f);
        pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        pauseBtnHeight = pauseBtn.getHeight() * 0.09f;
        bodies = new Array<>();
        //
        blocks.add(new woodenBlock("wb1.png",world));
        blocks.add(new woodenBlock("wb2.png",world));
        blocks.add(new woodenBlock("wb3.png",world));
        blocks.add(new woodenBlock("wb4.png",world));
        blocks.add(new woodenBlock("wb5.png",world));
        blocks.add(new woodenBlock("wb6.png",world));
        blocks.add(new woodenBlock("wb7.png",world));
        blocks.add(new woodenBlock("wb8.png",world));
        blocks.add(new woodenBlock("wb9.png",world));
        blocks.add(new woodenBlock("wb10.png",world));
        blocks.add(new woodenBlock("wb11.png",world));
        blocks.add(new woodenBlock("wb12.png",world));
        blocks.add(new woodenBlock("wb13.png",world));
        blocks.add(new woodenBlock("wb14.png",world));
        pigs.add(new pig1("pig1a.png",world));
        pigs.add(new pig2("pig2a.png",world));
        pigs.add(new pig3("pig3a.png",world));
//        pigs.add(new pig1("pig1a.png",world));
//        pigs.add(new pig2("pig2a.png",world));
        birds.add(new redBird("redBird.png",world));
        birds.add(new chuck("chuck.png",world));
        birds.add(new bomb("bomb.png",world));

        Vector2[] birdGroundPositions = new Vector2[]{
            new Vector2(170, 105), // First bird
            new Vector2(130, 105),
            new Vector2(90, 105)
        };
        for (int i = 0; i < birds.size; i++) {
            birds.get(i).setPosition(birdGroundPositions[i]);
        }
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
            if (touchPos.x >= winBtnX && touchPos.x <= winBtnX + winBtnWidth &&
                touchPos.y >= winBtnY && touchPos.y <= winBtnY + winBtnHeight) {
                game.setScreen(new resultState(game));
                dispose();
            }
            if (touchPos.x >= loseBtnX && touchPos.x <= loseBtnX + loseBtnWidth &&
                touchPos.y >= loseBtnY && touchPos.y <= loseBtnY + loseBtnHeight) {
                game.setScreen(new resultState2(game));
                dispose();
            }
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();

    }
//

    @Override
    public void create() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);
        game.batch.draw(ground, 0, 0, ground.getWidth()*0.5f, ground.getHeight()*0.5f);
        game.batch.draw(pauseBtn, pauseBtnX, pauseBtnY, pauseBtnWidth, pauseBtnHeight);
        game.batch.draw(winBtn,winBtnX,winBtnY,winBtn.getWidth()*0.21f,winBtn.getHeight()*0.21f);
        game.batch.draw(loseBtn,loseBtnX,loseBtnY,loseBtn.getWidth()*0.21f,loseBtn.getHeight()*0.21f);

        game.batch.draw(blocks.get(0).getBlockTexture(),1000,100,blocks.get(0).width,blocks.get(0).height);
        game.batch.draw(blocks.get(5).getBlockTexture(),1000+50+25,100,blocks.get(5).width,blocks.get(5).height);
        game.batch.draw(blocks.get(5).getBlockTexture(), (float) (1000+50+25), (float) (100+73),blocks.get(5).width,blocks.get(5).height);
        game.batch.draw(blocks.get(0).getBlockTexture(),1000+100+25+25,100,blocks.get(0).width,blocks.get(0).height);
        game.batch.draw(new TextureRegion(blocks.get(0).getBlockTexture()), (float) (1000-(blocks.get(0).height-87.5)/2+30), (float) (100F+blocks.get(0).height/2+10),blocks.get(0).width / 2f, blocks.get(0).height / 2f,blocks.get(0).width,blocks.get(0).height,1f,1f,90);
        game.batch.draw(new TextureRegion(blocks.get(0).getBlockTexture()), (float) (1087.5-(blocks.get(0).height-87.5)/2+90), (float) (100F+blocks.get(0).height/2+10),blocks.get(0).width / 2f, blocks.get(0).height / 2f,blocks.get(0).width,blocks.get(0).height,1f,1f,90);
        game.batch.draw(blocks.get(9).getBlockTexture(),1000,100+20+blocks.get(9).height,blocks.get(9).width,blocks.get(9).height);
        game.batch.draw(blocks.get(9).getBlockTexture(),1075,100+20+blocks.get(9).height,blocks.get(9).width,blocks.get(9).height);
        game.batch.draw(blocks.get(9).getBlockTexture(),1150,100+20+blocks.get(0).height,blocks.get(9).width,blocks.get(9).height);

        pigs.get(0).setPosition(new Vector2(1025, 100));
        //pigs.get(4).setPosition(new Vector2(1100, 100));
        pigs.get(2).setPosition(new Vector2(1000 - 20, 115 + blocks.get(0).height + blocks.get(9).height));
        pigs.get(1).setPosition(new Vector2(1075 - 10, 115 + blocks.get(0).height + blocks.get(9).height));
        //pigs.get(3).setPosition(new Vector2(1150, 115 + blocks.get(0).height + blocks.get(9).height));

        game.batch.draw(pigs.get(0).getPigTexture(), pigs.get(0).getPosition().x, pigs.get(0).getPosition().y, pigs.get(0).width, pigs.get(0).height);
        //game.batch.draw(pigs.get(4).getPigTexture(), pigs.get(4).getPosition().x, pigs.get(4).getPosition().y, pigs.get(4).width, pigs.get(4).height);
        game.batch.draw(pigs.get(2).getPigTexture(), pigs.get(2).getPosition().x, pigs.get(2).getPosition().y, pigs.get(2).width, pigs.get(2).height);
        game.batch.draw(pigs.get(1).getPigTexture(), pigs.get(1).getPosition().x, pigs.get(1).getPosition().y, pigs.get(1).width, pigs.get(1).height);
        //game.batch.draw(pigs.get(3).getPigTexture(), pigs.get(3).getPosition().x, pigs.get(3).getPosition().y, pigs.get(3).width, pigs.get(3).height);

        game.batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float frontAnchorX = 234;
        float frontAnchorY = 208;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(frontAnchorX, frontAnchorY, 224, 209, 10);
        shapeRenderer.end();
        game.batch.begin();
        game.batch.draw(slingshot.getslingFrontTexture(),200,100,slingshot.slingFront.getWidth()*0.3f,slingshot.slingFront.getHeight()*0.3f);
        for(int i=0;i<3;i++){
            game.batch.draw(birds.get(i).getBirdTexture(),birds.get(i).getPosition().x,birds.get(i).getPosition().y,birds.get(i).width,birds.get(i).height);
        }
        game.batch.draw(slingshot.getslingBackTexture(),185,150,slingshot.slingBack.getWidth()*0.3f,slingshot.slingBack.getHeight()*0.3f);
        game.batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float backAnchorX = 200;
        float backAnchorY = 208;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(backAnchorX, backAnchorY, 224,209, 10);//need to set the x2 and y2 according to launching bird thing
        shapeRenderer.end();
        update(delta);

    }

    public void launchBird(){

    }
    private void handleDragging(){

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
        winBtn.dispose();
        loseBtn.dispose();
        for (block b : blocks) b.dispose();
        for (pig p : pigs) p.dispose();
        for (bird b : birds) b.dispose();
        slingShot.dispose();
        world.dispose();
        shapeRenderer.dispose();
    }

}
