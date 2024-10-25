package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class pig {
    public float x;
    public float y;
    private Vector3 velocity;
    private final float GRAVITY= -9.8F;
    private Texture pig;
    public float width;
    public float height;
    private Rectangle bounds;

    public pig(String texturePath){
        this.pig = new Texture(texturePath);
        this.width=this.pig.getWidth()*0.2f;
        this.height=this.pig.getHeight()*0.2f;
    }
    public Texture getPigTexture(){
        return pig;
    }

}
