package com.ap_project.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import java.io.Serializable;

public class woodenBlock extends block  {

    public woodenBlock(String texture,World world) {
        super(texture,world);
        this.hits=30;
    }

}
