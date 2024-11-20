package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class block {
    public float x;
    public float y;
    public float width;
    public float height;
    public Texture block;
    protected World world;
    private Body body;
    private final float GRAVITY = -9.8F;
    private Rectangle bounds;

    public block(String texturePath, World world) {
        this.world = world;
        this.block = new Texture(texturePath);
        this.width = this.block.getWidth() * 0.1f;
        this.height = this.block.getHeight() * 0.1f;
        this.bounds = new Rectangle(x, y, width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;  // Dynamic body (affected by gravity, force, etc.)
        bodyDef.position.set(x, y);  // Initial position
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;  // Adjust the density as needed
        fixtureDef.friction = 0.5f;  // Adjust the friction as needed
        fixtureDef.restitution = 0.2f;  // Adjust restitution for bounciness

        com.badlogic.gdx.physics.box2d.PolygonShape shape = new com.badlogic.gdx.physics.box2d.PolygonShape();
        shape.setAsBox(width / 2, height / 2);  // Define the size of the block shape

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        shape.dispose();
    }
    public void setPosition(float x,float y){
        this.x = x;
        this.y = y;
        if (body != null) {
            body.setTransform(x, y, 0);  // Set the position of the Box2D body
        }
    }
    public abstract Texture getBlockTexture();

    public void update(float deltaTime) {
        x = body.getPosition().x;
        y = body.getPosition().y;
        bounds.setPosition(x, y);  // Update bounds based on new position
    }
    public void dispose() {
        block.dispose();
        world.destroyBody(body);  // Dispose the Box2D body
    }
    public Body getBody() {
        return body;
    }
    public Rectangle getBounds() {
        return bounds;
    }
}

