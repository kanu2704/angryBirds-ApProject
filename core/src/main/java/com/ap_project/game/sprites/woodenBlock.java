package com.ap_project.game.sprites;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class woodenBlock extends block{

    public woodenBlock(String texture,World world) {
        super(texture,world);
    }

    public TextureRegion getBlockTexture() {
        return new TextureRegion(block);
    }
}
