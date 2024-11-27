package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.io.Serializable;

public class slingshot implements Serializable {
    public transient static Texture slingBack;
    public transient static Texture slingFront;
    public transient final float x;
    public final float y;

    public slingshot(float x, float y) {
        slingBack = new Texture("slingBack.png"); // Load the back texture
        slingFront = new Texture("slingFront.png"); // Load the front texture
        this.x = x;
        this.y = y;
    }

    public static Texture getslingFrontTexture(){
        return slingFront;
    }
    public static Texture getslingBackTexture(){
        return slingBack;
    }

    public void dispose() {
        slingBack.dispose();
        slingFront.dispose();
    }
}

