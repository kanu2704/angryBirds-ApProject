package com.ap_project.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class redBird extends bird{
    public redBird(String texturePath, World world) {
        super(texturePath,world);
        this.state=BirdState.WAITING;
    }

    public void printp(){
        System.out.println("Im a red bird");

    }
}
