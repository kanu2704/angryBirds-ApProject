package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class slingshot {
    public static Texture slingBack;
    public static Texture slingFront;
    public final float x;
    public final float y;

    // Constructor to initialize slingshot with its position and textures
    public slingshot(float x, float y) {
        this.slingBack = new Texture("slingBack.png"); // Load the back texture
        this.slingFront = new Texture("slingFront.png"); // Load the front texture
        this.x = x;
        this.y = y;
    }
    public static Texture getslingFrontTexture(){
        return slingFront;
    }
    public static Texture getslingBackTexture(){
        return slingBack;
    }


    // Method to draw the slingshot's back part
//    public void drawBack(SpriteBatch batch) {
//        batch.draw(slingBack, x, y);
//    }
//
//    // Method to draw the slingshot's front part
//    public void drawFront(SpriteBatch batch) {
//        batch.draw(slingFront, x, y);
//    }

    // Dispose of the textures when done
    public void dispose() {
        slingBack.dispose();
        slingFront.dispose();
    }
}

