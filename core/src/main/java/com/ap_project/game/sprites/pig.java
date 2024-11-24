package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class pig<T extends pig<T>> {
    private final Body body;
    private Vector2 position;
    private Vector2 velocity;
    protected Texture pigTexture;
    public float width;
    public float height;
    protected final World world;
    protected int hits;
    private static final float PPM = 1.0f;

    public pig(String texturePath, World world) {
        this.world = world;
        this.pigTexture = new Texture(texturePath);
        this.width = this.pigTexture.getWidth() * 0.2f;
        this.height = this.pigTexture.getHeight() * 0.2f;

        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.position.x/PPM,this.position.y/PPM);
        bodyDef.linearVelocity.set(0,0);
        bodyDef.angularVelocity=0;
        bodyDef.angularDamping=0.8f;
        body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(Math.min(width, height)/2/PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        shape.setRadius(Math.min(width, height) / 2/PPM);
        float radius = Math.min(width, height) / 2/PPM;// Define the shape of the bird
        float area = (float) (Math.PI * Math.pow(radius, 2));
        float desiredMass = 10f;
        fixtureDef.density =desiredMass/area;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        shape.dispose();
        //this.bounds=new Circle(this.position.x,this.position.y,radius);
        body.setUserData(this);
    }
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
        this.getBody().setLinearVelocity(velocity);
        this.getBody().setAngularVelocity(0);
    }



    public void setPosition(Vector2 position) {
        this.position.set(position);
        this.getBody().setTransform(position.x, position.y, this.getBody().getAngle()); // Sync Box2D body position with visual position
    }

    public Vector2 getPosition() {
        return this.getBody().getPosition();
    }

    public Texture getPigTexture() {
        return pigTexture;
    }

    public int getHits() {
        return hits;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }
    public void decreaseHitPoints() {
        this.hits--;
    }
    public boolean isDestroyed() {
        return this.hits <= 0;
    }

    public Body getBody() {
        return body;
    }
    public void dispose() {
        pigTexture.dispose();  // Dispose the texture
        world.destroyBody(body);  // Destroy the Box2D body
    }
}
