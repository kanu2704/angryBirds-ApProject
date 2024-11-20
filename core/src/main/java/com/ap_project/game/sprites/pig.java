package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class pig {
    private final Body body;
    private Vector2 position;
    private Vector2 velocity;
    protected Texture pigTexture;
    public float width;
    public float height;
    protected final World world;
    protected int hits;

    public pig(String texturePath, World world) {
        this.world = world;
        this.pigTexture = new Texture(texturePath);
        this.width = this.pigTexture.getWidth() * 0.2f;
        this.height = this.pigTexture.getHeight() * 0.2f;

        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.position);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);
        this.hits = 0;
    }
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
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

    public Body getBody() {
        return body;
    }
    public void dispose() {
        pigTexture.dispose();  // Dispose the texture
        world.destroyBody(body);  // Destroy the Box2D body
    }
}
