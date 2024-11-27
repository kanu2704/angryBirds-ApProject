package com.ap_project.game.sprites;

import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class chuck extends bird implements Serializable {
    public chuck(String texturePath, World world) {
        super(texturePath,world);
    }
}
