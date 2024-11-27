package com.ap_project.game.states;
import com.ap_project.game.Core;
import com.ap_project.game.gameData;
import com.ap_project.game.pauseGameSave;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class playState extends abstractState implements Screen,Serializable{
    public int playingLevel;
    public transient Texture ground;
    private transient Texture pauseBtn;
    private transient Texture background;
    final Core game;
    OrthographicCamera camera;
    public transient World world;
    protected ArrayList<block<?>> blocks;//
    protected ArrayList<pig<?>> pigs;//
    protected ArrayList<bird<?>> birds;//
    private slingshot slingShot;//
    private int currentBirdIndex;//
    private final Vector2 slingPerchPosition = new Vector2(200, 215);
    private transient Texture winBtn;
    private transient Texture loseBtn;
    private float pauseBtnX;
    private float pauseBtnY;
    private float pauseBtnWidth;
    private float pauseBtnHeight;
    private float winBtnX;
    private float winBtnY;
    private float winBtnWidth;
    private float winBtnHeight;
    private float loseBtnX;
    private float loseBtnY;
    private float loseBtnWidth;
    private float loseBtnHeight;
    private List<Vector2> blockPositions;
    private List<Vector2> pigPositions;
    private List<Vector2> birdGroundPositions;
    private Box2DDebugRenderer debugRenderer;
    private static final float PPM = 1.0f;
    private ArrayList<Body> bodiesToDestroy = new ArrayList<>();
    Vector2 dragPos;
    final Vector2 initialPos=new Vector2(200,215);
    float timeGap;
    float gameWinTimer;
    float gameLoseTimer;
    boolean isPaused;
    private pauseState pauseScreen;
    pauseGameSave pauseGameSave;
    public boolean levelConstructed;

    public playState(Core game, int playingLevel,gameData gameData){
        super();
        this.game = game;
        this.playingLevel=playingLevel;
        isPaused=false;
        this.levelConstructed=false;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        setTextures();
        debugRenderer = new Box2DDebugRenderer();
        pauseGameSave=new pauseGameSave();
        if(gameData==null){
            this.blocks = new ArrayList<>();
            this.pigs = new ArrayList<>();
            this.birds = new ArrayList<>();
            blockPositions=new ArrayList<>();
            pigPositions=new ArrayList<>();
            birdGroundPositions=new ArrayList<>();
            currentBirdIndex = 0;
            timeGap=0;
            gameWinTimer=0;
            gameLoseTimer=0;
            System.out.println("world is set");
            this.world = new World(new Vector2(0, -12f), true);//
            this.world.setContactListener(new collisionHandler(this));
        }else{
            System.out.println("assigning previous structures.......");
            blocks=gameData.blocks;
            birds=gameData.birds;
            pigs=gameData.pigs;
            blockPositions=gameData.blockPositions;
            pigPositions=gameData.pigPositions;
            birdGroundPositions=gameData.birdPositions;
            currentBirdIndex= gameData.currentBirdIndex;
            timeGap= gameData.timeGap;
            gameWinTimer=gameData.gameWinTimer;
            gameLoseTimer=gameData.gameLoseTimer;
            System.out.println("world is set");
            this.world = new World(new Vector2(0, -12f), true);//
            this.world.setContactListener(new collisionHandler(this));
            levelConstructed=true;
            if(playingLevel==1){
                level.getLevel1Textures(this.world,blocks,pigs,birds,blockPositions,pigPositions,birdGroundPositions, (ArrayList<Vector2>) gameData.birdVelocities, (ArrayList<Vector2>) gameData.pigVelocities, (ArrayList<Vector2>) gameData.blockVelocities,gameData.pigHealth);
            }else if(playingLevel==2){
                level.getLevel2Textures(this.world,blocks,pigs,birds,blockPositions,pigPositions,birdGroundPositions, (ArrayList<Vector2>) gameData.birdVelocities, (ArrayList<Vector2>) gameData.pigVelocities, (ArrayList<Vector2>) gameData.blockVelocities,gameData.pigHealth);
            }else if(playingLevel==3){
                level.getLevel3Textures(this.world,blocks,pigs,birds,blockPositions,pigPositions,birdGroundPositions, (ArrayList<Vector2>) gameData.birdVelocities, (ArrayList<Vector2>) gameData.pigVelocities, (ArrayList<Vector2>) gameData.blockVelocities,gameData.pigHealth);
            }
        }
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= pauseBtnX && touchPos.x <= pauseBtnX + pauseBtnWidth &&
                touchPos.y >= pauseBtnY && touchPos.y <= pauseBtnY + pauseBtnHeight) {
                isPaused=true;
                pauseGameSave.saveGameState(blocks,pigs,birds,currentBirdIndex,playingLevel,timeGap,gameWinTimer,gameLoseTimer,bodiesToDestroy);
                pauseState pauseScreen=new pauseState(game,playingLevel);
                game.setScreen(pauseScreen);
                dispose();
                return;
            }
        }
        float maxDragRadius = 75f;
        float maxForce = 125f;
        bird<?> currentBird=null;
        if(currentBirdIndex<birds.size()){
            currentBird = birds.get(currentBirdIndex);
            Vector2 birdBodyPos = currentBird.getBody().getPosition();
            Rectangle birdBounds = new Rectangle(
                birdBodyPos.x - currentBird.width / 2 / PPM,
                birdBodyPos.y - currentBird.height / 2 / PPM,
                currentBird.width / PPM, currentBird.height / PPM
            );
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if (birdBounds.contains(touchPos.x / PPM, touchPos.y / PPM) &&
                    !currentBird.isLaunched() && !currentBird.isDragging) {
                    currentBird.isDragging = true;
                }
                if (currentBird.isDragging) {
                    dragPos = new Vector2(touchPos.x / PPM, touchPos.y / PPM);
                    if (dragPos.dst(initialPos.x / PPM, initialPos.y / PPM) > maxDragRadius) {
                        dragPos = new Vector2(initialPos.x / PPM, initialPos.y / PPM)
                            .add(dragPos.sub(initialPos.x / PPM, initialPos.y / PPM).nor().scl(maxDragRadius));
                    }
                    currentBird.setPosition(dragPos);
                }
            }
            else if (currentBird.isDragging) {
                currentBird.isDragging = false;
                Vector2 dragVector= new Vector2(initialPos).sub(dragPos);
                float dragDistance = dragVector.len();
                dragDistance = Math.min(dragDistance, maxDragRadius);
                Vector2 normalizedDirection = dragVector.cpy().nor();
                float forceMagnitude = (dragDistance / maxDragRadius) * maxForce;
                Vector2 launchForce = normalizedDirection.scl(forceMagnitude*currentBird.getBody().getMass());
                currentBird.getBody().setGravityScale(1f);
                currentBird.getBody().applyLinearImpulse(launchForce, currentBird.getBody().getWorldCenter(), true);
                currentBird.setVelocity(currentBird.getBody().getLinearVelocity());
                currentBird.state = BirdState.LAUNCHED;
                System.out.println("------------------------current birds velocity is "+currentBird.getVelocity());
            }
        }

    }

    @Override
    protected void update(float dt) {
        System.out.println(levelConstructed);
        if(!levelConstructed){
            if(playingLevel<=3){
                levelManager.constructLevel(playingLevel,world,blocks,pigs,birds,blockPositions,pigPositions,birdGroundPositions);
                levelConstructed=true;
            }
        }
        for(int i=0;i<birds.size();i++){
            birds.get(i).setVelocity(birds.get(i).getBody().getLinearVelocity());
        }
        for(int i=0;i<pigs.size();i++){
            pigs.get(i).setVelocity(pigs.get(i).getBody().getLinearVelocity());
        }
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setVelocity(blocks.get(i).getBody().getLinearVelocity());
        }
//        System.out.println("currentBird velocity" +birds.get(currentBirdIndex).getBody().getLinearVelocity());
//        for(int i=0;i<birds.size();i++){
//
//            birds.get(i).setVelocity(birds.get(i).getBody().getLinearVelocity());
//        }

        if(!isPaused){
            handleInput();
            for (block<?> block : blocks) {
                if (block.isDestroyed()) {
                    removeObjectFromWorld(block.getBody());
                    blocks.remove(block);
                }
            }
            for (pig<?> pig : pigs) {
                if (pig.isDestroyed()) {
                    removeObjectFromWorld(pig.getBody());
                    pigs.remove(pig);
                }
            }
            updateBirds(dt);
            checkGameOver(dt);
        }
    }
    @Override
    public void create() {

    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta){
        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        world.step(1/60f, 6, 2);
        if(levelConstructed){
            if(isPaused){
                Gdx.graphics.setContinuousRendering(false);//
                ////////////////////////////
                pauseScreen.render(delta);
            }else{
                Gdx.graphics.setContinuousRendering(true);
                ScreenUtils.clear(0, 0, 0.2f, 1);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                game.batch.begin();
                game.batch.draw(background, 0, 0, Core.WIDTH, Core.HEIGHT);
                createGround();
                game.batch.draw(ground, -ground.getWidth() / 2f, -ground.getHeight() / 2f, ground.getWidth()*0.5f, ground.getHeight()*0.5f);
                game.batch.draw(pauseBtn, pauseBtnX, pauseBtnY, pauseBtnWidth, pauseBtnHeight);
                game.batch.draw(winBtn,winBtnX,winBtnY,winBtn.getWidth()*0.21f,winBtn.getHeight()*0.21f);
                game.batch.draw(loseBtn,loseBtnX,loseBtnY,loseBtn.getWidth()*0.21f,loseBtn.getHeight()*0.21f);
                for (int i = 0; i < blocks.size(); i++) {
                    block<?> currentBlock = blocks.get(i);
                    if(!currentBlock.isDestroyed()){
                        float blockAngle = (float) Math.toDegrees(currentBlock.getBody().getAngle());
                        game.batch.draw(new TextureRegion(blocks.get(i).getBlockTexture()),
                            currentBlock.getPosition().x * PPM - currentBlock.width / 4,
                            currentBlock.getPosition().y * PPM - currentBlock.height / 4,
                            currentBlock.width /4, currentBlock.height/4,
                            currentBlock.width*0.5f, currentBlock.height*0.5f,
                            1f, 1f,
                            blockAngle);
                    }
                }
                for (int i = 0; i < pigs.size(); i++) {
                    pig<?> currentPig = pigs.get(i);
                    if(!currentPig.isDestroyed()){
                        float pigAngle = (float) Math.toDegrees(currentPig.getBody().getAngle()); // Get angle in degrees
                        game.batch.draw(
                            new TextureRegion(currentPig.getPigTexture()),
                            currentPig.getPosition().x * PPM - currentPig.width / 2, // X position
                            currentPig.getPosition().y * PPM - currentPig.height / 2, // Y position
                            currentPig.width / 2,
                            currentPig.height / 2,
                            currentPig.width,
                            currentPig.height,
                            1f,
                            1f,
                            pigAngle
                        );
                    }
                }
                game.batch.draw(slingshot.getslingFrontTexture(),180,100,slingshot.slingFront.getWidth()*0.3f,slingshot.slingFront.getHeight()*0.3f);
                for (int i = 0; i<birds.size(); i++) {
                    bird<?> currentBird = birds.get(i);
                    float birdAngle = (float) Math.toDegrees(currentBird.getBody().getAngle());
                    game.batch.draw(
                        new TextureRegion(currentBird.getBirdTexture()),
                        currentBird.getPosition().x * PPM - currentBird.width / 2, currentBird.getPosition().y * PPM - currentBird.height / 2, currentBird.width / 2, currentBird.height / 2,
                        currentBird.width,
                        currentBird.height,
                        1f,
                        1f,
                        birdAngle
                    );
                }
                game.batch.draw(slingshot.getslingBackTexture(),165,150,slingshot.slingBack.getWidth()*0.3f,slingshot.slingBack.getHeight()*0.3f);
                processQueuedBodies();
                game.batch.end();
            }
        }
        update(delta);
    }
    public void createGround() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(0, 0);  // Position the body at (0, 0), the center
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(ground.getWidth() / 2f, ground.getHeight() / 2f);  // Set Box2D shape to half the width/height
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundShape;
        groundFixtureDef.density=100f;
        groundFixtureDef.friction = 0.2f;
        groundFixtureDef.restitution = 0f;  // No bounce
        groundBody.createFixture(groundFixtureDef);
        groundShape.dispose();
        groundBody.setUserData(ground);
    }
    private void updateBirds(float delta) {
        timeGap+=delta;
        if(timeGap>=2){
            if(currentBirdIndex<birds.size()){
                bird<?> currentBird = birds.get(currentBirdIndex);
                switch (currentBird.state) {
                    case WAITING:
                        currentBird.jumpToSling(slingPerchPosition);
                        break;
                    case LAUNCHED:
                        currentBirdIndex++;
                        if(currentBirdIndex<birds.size()){
                            bird<?> nextBird = birds.get(currentBirdIndex);
                            nextBird.state = BirdState.WAITING;
                            timeGap=0;
                        }
                        break;
                }
            }
        }
    }
    public void checkGameOver(float delta){
        System.out.println(pigs.size());
        if(pigs.size()==0){
            //pigsSize=true;
            gameWinTimer+=delta;
            if(gameWinTimer>2){
                if(currentBirdIndex<=birds.size()){
                    if(Core.currentLevel==getPlayingLevel() && Core.currentLevel<=2){
                        Core.currentLevel++;
                    }
                    game.setScreen(new resultState(game,playingLevel));
                    dispose();
                }
            }
        }else{
            if(currentBirdIndex>=birds.size()){
                gameLoseTimer+=delta;
                if(gameLoseTimer>0 /*&& pigsSize==true*/){
                    if(Core.currentLevel==getPlayingLevel() && Core.currentLevel<=2){
                        Core.currentLevel++;
                    }
                    game.setScreen(new resultState(game,playingLevel));
                    dispose();
                }
                else if(gameLoseTimer>12){
                    game.setScreen(new resultState2(game,playingLevel));
                    dispose();
                }
            }
        }
    }
    public void queueBodyForDestruction(Body body,Object obj) {
        bodiesToDestroy.add(body);
        if(obj instanceof block<?>){
            block<?> block=(block<?>) obj;
            block.getBlockTexture().dispose();
        }else{
            pig<?> pig=(pig<?>) obj;
            pig.getPigTexture().dispose();
        }
    }
    public void processQueuedBodies() {
        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }
    public ArrayList<block<?>> getBlocks() {
        return blocks;
    }
    public void setBlocks(ArrayList<block<?>> blocks) {
        this.blocks = blocks;
    }
    public ArrayList<pig<?>> getPigs() {
        return pigs;
    }
    public void setPigs(ArrayList<pig<?>> pigs) {
        this.pigs = pigs;
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
    public void setTextures(){
        background=new Texture("background.jpg");
        ground=new Texture("ground1.png");
        pauseBtn=new Texture("pauseBtn.png");
        slingShot=new slingshot(400f,105f);
        pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        pauseBtnHeight = pauseBtn.getHeight() * 0.09f;
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
    void removeObjectFromWorld(Body body) {
        world.destroyBody(body);
    }
    public int getPlayingLevel() {
        return playingLevel;
    }
    public int getCurrentBirdIndex() {
        return currentBirdIndex;
    }

    public void setCurrentBirdIndex(int currentBirdIndex) {
        this.currentBirdIndex = currentBirdIndex;
    }

    public float getGameWinTimer() {
        return gameWinTimer;
    }

    public void setGameWinTimer(float gameWinTimer) {
        this.gameWinTimer = gameWinTimer;
    }

    public float getGameLoseTimer() {
        return gameLoseTimer;
    }

    public void setGameLoseTimer(float gameLoseTimer) {
        this.gameLoseTimer = gameLoseTimer;
    }

    public float getTimeGap() {
        return timeGap;
    }

    public void setTimeGap(float timeGap) {
        this.timeGap = timeGap;
    }

    public ArrayList<bird<?>> getBirds() {
        return birds;
    }

    public void setBirds(ArrayList<bird<?>> birds) {
        this.birds = birds;
    }

    public ArrayList<Body> getBodiesToDestroy() {
        return bodiesToDestroy;
    }

    public void setBodiesToDestroy(ArrayList<Body> bodiesToDestroy) {
        this.bodiesToDestroy = bodiesToDestroy;
    }

    public void setPigPositions(List<Vector2> pigPositions) {
        this.pigPositions = pigPositions;
    }

    public void setBirdGroundPositions(List<Vector2> birdGroundPositions) {
        this.birdGroundPositions = birdGroundPositions;
    }

    public void setBlockPositions(List<Vector2> blockPositions) {
        this.blockPositions = blockPositions;
    }

    @Override
    public void dispose() {
        ground.dispose();
        pauseBtn.dispose();
        background.dispose();
        winBtn.dispose();
        loseBtn.dispose();
        world.dispose();
        debugRenderer.dispose();
        slingshot.getslingFrontTexture().dispose();
        slingshot.getslingBackTexture().dispose();
        for (bird<?> currentBird : birds) {
            currentBird.getBirdTexture().dispose();
        }
        for (block<?> currentBlock : blocks) {
            System.out.println("block");
            currentBlock.getBlockTexture().dispose();
        }
        for (pig<?> currentPig : pigs) {
            currentPig.getPigTexture().dispose();
        }
    }

}
