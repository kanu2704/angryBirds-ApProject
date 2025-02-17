package com.ap_project.game.states;

import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class levelManager {
    public static void constructLevel(int num, World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs, ArrayList<bird<?>> birds, List<Vector2> blockPositions, List<Vector2> pigPositions, List<Vector2> birdPositions){
        if(num==1){
            level.getLevel1(world,blocks,pigs,birds,blockPositions,pigPositions,birdPositions);}
        else if(num==2){
            level.getLevel2(world,blocks,pigs,birds,blockPositions,pigPositions,birdPositions);
        }else if(num==3){
            level.getLevel3(world,blocks,pigs,birds,blockPositions,pigPositions,birdPositions);
        }
    }
}
