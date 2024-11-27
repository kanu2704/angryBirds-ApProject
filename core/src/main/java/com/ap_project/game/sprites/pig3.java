package com.ap_project.game.sprites;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class pig3 extends pig implements Serializable {
    public pig3(String texturePath, World world) {
        super(texturePath,world);
        this.hits=25;
    }

}
