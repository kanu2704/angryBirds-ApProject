package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class iceBlock extends block{
    public iceBlock(String texture, World world) {
        super(texture,world);
        this.hits=3;
    }

}
