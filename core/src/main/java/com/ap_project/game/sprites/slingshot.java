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

    // Dispose of the textures when done
    public void dispose() {
        slingBack.dispose();
        slingFront.dispose();
    }
}

