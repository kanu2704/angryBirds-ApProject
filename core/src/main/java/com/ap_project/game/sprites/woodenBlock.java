package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class woodenBlock extends block{

    public woodenBlock(String texture) {
        super(texture);
    }

    public Texture getBlockTexture() {
        return block;
    }
}
