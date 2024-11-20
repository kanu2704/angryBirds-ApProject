package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class steelBlock extends block{
    public steelBlock(String texture, World world) {
        super(texture,world);
    }
    public Texture getBlockTexture() {
        return block;
    }
}
