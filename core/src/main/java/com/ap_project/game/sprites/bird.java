package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class bird {
    private Vector2 position;
    private Vector2 velocity;
    private Texture birdTexture;
    public float width;
    public float height;
    private Body body;
    protected final World world;
    private boolean isLaunched = false;
    public boolean isDragging = false;

    public bird(String texturePath, World world) {
        this.world = world;
        this.birdTexture = new Texture(texturePath);
        this.width = this.birdTexture.getWidth() * 0.2f;
        this.height = this.birdTexture.getHeight() * 0.2f;

        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.position);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);  // Define the shape of the bird

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;  // Bounciness of the bird

        body.createFixture(fixtureDef);
        shape.dispose();
        body.setUserData(this);  // Attach the Bird instance to the body
    }
    public Texture getBirdTexture() {
        return birdTexture;
    }
    public Vector2 getPosition() {
        return this.getBody().getPosition();
    }
    public void setPosition(Vector2 position) {
        this.position.set(position);  // Update the visual position
        this.getBody().setTransform(position.x, position.y, this.getBody().getAngle());  // Update Box2D body position
    }

    public void setDragging(boolean dragging) {
        this.isDragging = dragging;
    }

    public boolean isLaunched() {
        return isLaunched;
    }

    public void setLaunched(boolean launched) {
        isLaunched = launched;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);  // Set the velocity directly
    }
    public Vector2 getVelocity() {
        return velocity;
    }
    public Body getBody() {
        return body;
    }
    // Dispose resources
    public void dispose() {
        birdTexture.dispose();  // Dispose texture to free memory
        world.destroyBody(body);  // Destroy the Box2D body from the world
    }
}

