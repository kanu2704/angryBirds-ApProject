package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class bird {
    private Vector3 position;
    private Vector3 velocity;
    private final float GRAVITY= -9.8F;
    private Texture bird;
    public float width;
    public float height;
    private Rectangle bounds;

    public bird(String texturePath){
        this.bird=new Texture(texturePath);
        this.width=this.bird.getWidth()*0.2f;
        this.height=this.bird.getHeight()*0.2f;
    }
//    public void update(float dt){
//
//    }
    public Texture getBirdTexture(){
        return bird;
    }

}
