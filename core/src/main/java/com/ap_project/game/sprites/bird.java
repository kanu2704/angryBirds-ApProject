package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class bird {
    private Vector3 position;
    private Vector3 velocity;
    private final float GRAVITY= -9.8F;
    private Texture bird;
    private Rectangle bounds;

    public bird(){
        bird=new Texture("bird.png");
    }
    public void update(float dt){

    }
}
