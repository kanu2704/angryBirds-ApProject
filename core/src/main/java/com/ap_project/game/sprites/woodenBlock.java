package com.ap_project.game.sprites;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class woodenBlock extends block{

    public woodenBlock(String texture,World world) {
        super(texture,world);
    }

    public Texture getBlockTexture() {
        return block;
    }
}
