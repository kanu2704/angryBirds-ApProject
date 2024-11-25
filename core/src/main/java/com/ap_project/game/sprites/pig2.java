package com.ap_project.game.sprites;

import com.badlogic.gdx.physics.box2d.World;

public class pig2 extends pig{
    public pig2(String texturePath, World world) {
        super(texturePath,world);
        this.hits=25;
    }
}
