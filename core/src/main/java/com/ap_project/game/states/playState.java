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
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import java.util.ArrayList;
import java.util.List;

public class playState extends abstractState implements Screen {
    int currentLevel;
    private final Texture ground;
    private final Texture pauseBtn;
    private final Texture background;
    final Core game;
    OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private physicsEngine engine;
    public World world;
    private Array<block<?>> blocks;
    private Array<pig<?>> pigs;
    private Array<bird<?>> birds;
    private slingshot slingShot;
    private int currentBirdIndex;
    private final Vector2 slingPerchPosition = new Vector2(200, 215);
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
    private List<Vector2> blockPositions;
    private List<Vector2> pigPositions;
    private Box2DDebugRenderer debugRenderer;
    private static final float PPM = 1.0f;
    //float birdLaunchTimer = 0;
    Vector2 dragPos;
    final Vector2 initialPos=new Vector2(200,215);

    public playState(Core game){
        super();
        this.currentLevel=1;
        this.game = game;
        this.world = new World(new Vector2(0, -30.0f), true);//
        this.blocks = new Array<>();
        this.pigs = new Array<>();
        this.birds = new Array<>();
        blockPositions=new ArrayList<>();
        pigPositions=new ArrayList<>();
        shapeRenderer = new ShapeRenderer();
        engine = new physicsEngine();
        currentBirdIndex = 0;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,Core.WIDTH,Core.HEIGHT);
        background=new Texture("background.jpg");
        ground=new Texture("ground1.png");
        pauseBtn=new Texture("pauseBtn.png");
        slingShot=new slingshot(400f,105f);
        pauseBtnWidth = pauseBtn.getWidth() * 0.09f;
        pauseBtnHeight = pauseBtn.getHeight() * 0.09f;
        birds.add(new redBird("redBird.png",world));
        birds.add(new chuck("chuck.png",world));
        birds.add(new bomb("bomb.png",world));

        Vector2[] birdGroundPositions = new Vector2[]{
            new Vector2(170, 105), // First bird
            new Vector2(130, 105),
            new Vector2(90, 105)
        };
        for (int i = 0; i < birds.size; i++) {
            birds.get(i).setVelocity(new Vector2(0,0));
            birds.get(i).setPosition(birdGroundPositions[i]);
        }
        levelManager.constructLevel(currentLevel,world,blocks,pigs,blockPositions,pigPositions);
        for(int i=0;i<blocks.size;i++){
            blocks.get(i).setVelocity(new Vector2(0,0));
            blocks.get(i).setPosition(blockPositions.get(i));
        }
        for(int i=0;i<pigs.size;i++){
            pigs.get(i).setVelocity(new Vector2(0,0));
            pigs.get(i).setPosition(pigPositions.get(i));
        }
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
        debugRenderer = new Box2DDebugRenderer();
    }
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x >= pauseBtnX && touchPos.x <= pauseBtnX + pauseBtnWidth &&
                touchPos.y >= pauseBtnY && touchPos.y <= pauseBtnY + pauseBtnHeight) {
                game.setScreen(new pauseState(game));
                dispose();
                return;
            }
            if (touchPos.x >= winBtnX && touchPos.x <= winBtnX + winBtnWidth &&
                touchPos.y >= winBtnY && touchPos.y <= winBtnY + winBtnHeight) {
                game.setScreen(new resultState(game));
                dispose();
                return;
            }
            if (touchPos.x >= loseBtnX && touchPos.x <= loseBtnX + loseBtnWidth &&
                touchPos.y >= loseBtnY && touchPos.y <= loseBtnY + loseBtnHeight) {
                game.setScreen(new resultState2(game));
                dispose();
                return;
            }
        }
        float maxDragRadius = 65f;
        float maxForce = 500f;
        bird<?> currentBird = birds.get(currentBirdIndex);
        Vector2 birdBodyPos = currentBird.getBody().getPosition();
        System.out.println(initialPos);
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
                currentBird.isDragging = true;  // Start dragging
            }
            if (currentBird.isDragging) {
                dragPos = new Vector2(touchPos.x / PPM, touchPos.y / PPM);
                if (dragPos.dst(initialPos.x / PPM, initialPos.y / PPM) > maxDragRadius) {
                    dragPos = new Vector2(initialPos.x / PPM, initialPos.y / PPM)
                        .add(dragPos.sub(initialPos.x / PPM, initialPos.y / PPM).nor().scl(maxDragRadius));
                }
                currentBird.setPosition(dragPos);
            }

        } else if (currentBird.isDragging) {
            currentBird.isDragging = false;
            System.out.println("dragPos: " + dragPos);
            System.out.println("currentBirdps: " + currentBird.getPosition());
            Vector2 dragVector = new Vector2(initialPos).sub(dragPos);
            float dragDistance = dragVector.len();
            dragDistance = Math.min(dragDistance, maxDragRadius);
            System.out.println("Drag vector magnitude: " + dragDistance);
            Vector2 normalizedDirection = dragVector.cpy().nor();
            System.out.println("Normalized direction: " + normalizedDirection);
            float forceMagnitude = (dragDistance / maxDragRadius) * maxForce;
            System.out.println("maxforce "+maxForce);
            Vector2 launchForce = normalizedDirection.scl(forceMagnitude*currentBird.getBody().getMass());
            System.out.println("Launch Force: " + launchForce);
            currentBird.getBody().setGravityScale(1f);
            currentBird.getBody().setLinearDamping(0f);
            currentBird.getBody().applyLinearImpulse(launchForce, currentBird.getPosition(), true);
            currentBird.getBody().applyForceToCenter(launchForce.x,launchForce.y,true);
            System.out.println(currentBird.getVelocity());
            currentBird.state = BirdState.LAUNCHED;
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        updateBirds(dt);
    }
    @Override
    public void create() {

    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta){
        world.step(1/90f, 6, 2);
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
        for (int i = 0; i < blocks.size; i++) {
            block<?> currentBlock = blocks.get(i);
            float blockAngle = (float) Math.toDegrees(currentBlock.getBody().getAngle());
            game.batch.draw(blocks.get(i).getBlockTexture(),
                currentBlock.getPosition().x * PPM - currentBlock.width / 4,
                currentBlock.getPosition().y * PPM - currentBlock.height / 4,
                currentBlock.width /4, currentBlock.height/4,
                currentBlock.width*0.5f, currentBlock.height*0.5f,
                1f, 1f,
                blockAngle);
        }
        for (int i = 0; i < pigs.size; i++) {
            pig<?> currentPig = pigs.get(i);
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
        game.batch.end();
        debugRenderer.render(world, camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float frontAnchorX = 205;
        float frontAnchorY = 208;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(frontAnchorX, frontAnchorY, 185, 207, 10);
        shapeRenderer.end();
        game.batch.begin();
        game.batch.draw(slingshot.getslingFrontTexture(),180,100,slingshot.slingFront.getWidth()*0.3f,slingshot.slingFront.getHeight()*0.3f);
        for (int i = 0; i<birds.size; i++) {
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
        game.batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float backAnchorX = 185;
        float backAnchorY = 210;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rectLine(backAnchorX, backAnchorY, 185,210, 10);
        shapeRenderer.end();
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
        if (currentBirdIndex < birds.size) {
            bird<?> currentBird = birds.get(currentBirdIndex);
            switch (currentBird.state) {
                case WAITING:
                    System.out.println("Bird is going to jump to the slingshot");
                    currentBird.jumpToSling(slingPerchPosition);
                    break;
                case JUMPING:
                    currentBird.updateJump(slingPerchPosition);
                    break;
                case LAUNCHED:
                    if (currentBirdIndex < birds.size-1) {
                        currentBirdIndex++;
                        bird<?> nextBird = birds.get(currentBirdIndex);
                        nextBird.state = BirdState.WAITING;
                    } else {
                        checkGameOver();
                    }
                    break;

            }
        }
    }

    private void checkGameOver() {
        if (pigs.size == 0) {
            game.setScreen(new resultState(game)); // Win screen
        } else if (currentBirdIndex >= birds.size) {
            game.setScreen(new resultState2(game)); // Lose screen
        }
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
        for (block<?> b : blocks) b.dispose();
        for (pig<?> p : pigs) p.dispose();
        for (bird<?> b : birds) b.dispose();
        slingShot.dispose();
        world.dispose();
        debugRenderer.dispose();
        shapeRenderer.dispose();
    }
}
