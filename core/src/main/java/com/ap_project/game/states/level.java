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

    static void getLevel1(World world, Array<block> blocks, Array<pig> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){
        Array<block> blocksLevel1=new Array<>();
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
            new Vector2(1000, 100),
            new Vector2(1000 + 50 + 25, 100),
            new Vector2(1000 + 50 + 25, 100 + 73),
            new Vector2(1000 + 100 + 25 + 25, 100),
            new Vector2(1000-58.5f , 100+blocksLevel1.get(0).height-3),
            new Vector2(1087.5f, 100+blocksLevel1.get(0).height-3),
            new Vector2(1000, 100 + 20 + blocksLevel1.get(0).height),
            new Vector2(1075, 100 + 20 + blocksLevel1.get(0).height),
            new Vector2(1150, 100 + 20 + blocksLevel1.get(0).height)
        ));

        Array<pig> pigsLevel1=new Array<>();
        pigsLevel1.add(new pig1("pig1a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));
        pigsLevel1.add(new pig3("pig3a.png",world));
        pigsLevel1.add(new pig1("pig1a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));

        List<Vector2> pigsPositionsLevel1 = new ArrayList<>(Arrays.asList(
            new Vector2(1025, 100),
            new Vector2(1075 - 10, 115 + blocksLevel1.get(0).height + blocksLevel1.get(8).height),
            new Vector2(1000 - 20, 115 + blocksLevel1.get(0).height + blocksLevel1.get(8).height),
            new Vector2(1150, 115 + blocksLevel1.get(0).height + blocksLevel1.get(8).height),
            new Vector2(1100, 100)
        ));

        blocks.addAll(blocksLevel1);
        pigs.addAll(pigsLevel1);
        blockPositions.addAll(blockPositionsLevel1);
        pigPositions.addAll(pigsPositionsLevel1);
    }
}
