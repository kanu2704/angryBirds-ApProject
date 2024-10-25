package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class block {
    public float x;
    public float y;
    public float width;
    public float height;
    public Texture block;
    private final float GRAVITY = -9.8F;
    private Rectangle bounds;

    public block(String texturePath) {
        this.block = new Texture(texturePath);
        this.width = this.block.getWidth() * 0.1f;
        this.height = this.block.getHeight() * 0.1f;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public abstract Texture getBlockTexture();

    public void dispose() {
        block.dispose();
    }
//    public Rectangle getBounds() {
//        return bounds;
//    }
//
//    public void update(float deltaTime) {
//        y += GRAVITY * deltaTime;
//        bounds.setPosition(x, y);
//    }
}

