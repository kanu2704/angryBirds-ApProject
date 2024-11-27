package com.ap_project.game.sprites;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class pig1 extends pig implements Serializable{
    public pig1(String texturePath, World world) {
        super(texturePath,world);
        this.hits=20;
    }
}
