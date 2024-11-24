package com.ap_project.game.states;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class level {
    private int levelNo;
    public static void getLevel1(World world, Array<block<?>> blocks, Array<pig<?>> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){
        Array<block<?>> blocksLevel1=new Array<>();
        blocksLevel1.add(new woodenBlock("wb1.png",world));
        blocksLevel1.add(new woodenBlock("wb6.png",world));
        blocksLevel1.add(new woodenBlock("wb6.png",world));
        blocksLevel1.add(new woodenBlock("wb1.png",world));
        blocksLevel1.add(new woodenBlock("wb1r.png",world));
        blocksLevel1.add(new woodenBlock("wb1r.png",world));
        blocksLevel1.add(new woodenBlock("wb10.png",world));
        blocksLevel1.add(new woodenBlock("wb10.png",world));
        blocksLevel1.add(new woodenBlock("wb10.png",world));

        List<Vector2> blockPositionsLevel1 = new ArrayList<>(Arrays.asList(
            new Vector2(950, 100),
            new Vector2(1000  + 25, 100),
            new Vector2(1000 + 25, 100 + blocksLevel1.get(2).height-20),
            new Vector2(1000 + 50 + 28 + 25, 100),
            new Vector2(950, 100+blocksLevel1.get(0).height*0.5f+20),
            new Vector2(950+blocksLevel1.get(4).width/2, 100+blocksLevel1.get(0).height*0.5f+20),
            new Vector2(948, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
            new Vector2(1025, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
            new Vector2(1100, 100 + 20 + blocksLevel1.get(0).height*0.5f+26)
        ));

        Array<pig<?>> pigsLevel1=new Array<>();
        pigsLevel1.add(new pig1("pig1a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));
        pigsLevel1.add(new pig3("pig2a.png",world));
        pigsLevel1.add(new pig1("pig1a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));
        List<Vector2> pigsPositionsLevel1 = new ArrayList<>(Arrays.asList(
            new Vector2(978, 100),
            new Vector2(1025, 100 + 20 + blocksLevel1.get(0).height*0.5f+35+blocksLevel1.get(8).height*0.5f),
            new Vector2(950, 100 + 20 + blocksLevel1.get(0).height*0.5f+35+blocksLevel1.get(8).height*0.5f),
            new Vector2(1101, 100 + 20 + blocksLevel1.get(0).height*0.5f+29+blocksLevel1.get(8).height*0.5f),
            new Vector2(1062, 100)
        ));

        blocks.addAll(blocksLevel1);
        pigs.addAll(pigsLevel1);
        blockPositions.addAll(blockPositionsLevel1);
        pigPositions.addAll(pigsPositionsLevel1);
    }
    public static void getLevel2(World world, Array<block> blocks, Array<pig> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){

    }
    public static void getLevel3(World world, Array<block> blocks, Array<pig> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){

    }
}
