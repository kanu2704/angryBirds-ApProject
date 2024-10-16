package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class slingshot {
    private Vector3 position;
    private Vector3 velocity;
    private final float GRAVITY= -9.8F;
    private Texture sling;
    private Rectangle bounds;

    public slingshot(){
        sling=new Texture("sling.png");
    }
}
