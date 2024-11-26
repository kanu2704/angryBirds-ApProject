package com.ap_project.game.states;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class level {
//    public static void getLevel1(World world, Array<block<?>> blocks, Array<pig<?>> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){
//        Array<block<?>> blocksLevel1=new Array<>();
//        blocksLevel1.add(new woodenBlock("wb1.png",world));
//        blocksLevel1.add(new woodenBlock("wb6.png",world));
//        blocksLevel1.add(new woodenBlock("wb6.png",world));
//        blocksLevel1.add(new woodenBlock("wb1.png",world));
//        blocksLevel1.add(new woodenBlock("wb1r.png",world));
//        blocksLevel1.add(new woodenBlock("wb1r.png",world));
//        blocksLevel1.add(new woodenBlock("wb10.png",world));
//        blocksLevel1.add(new woodenBlock("wb10.png",world));
//        blocksLevel1.add(new woodenBlock("wb10.png",world));
//        List<Vector2> blockPositionsLevel1 = new ArrayList<>(Arrays.asList(
//            new Vector2(850, 100),
//            new Vector2(900  + 25, 100),
//            new Vector2(900 + 25, 100 + blocksLevel1.get(2).height-20),
//            new Vector2(950  + 28 + 25, 100),
//            new Vector2(850, 100+blocksLevel1.get(0).height*0.5f+20),
//            new Vector2(850+blocksLevel1.get(4).width/2, 100+blocksLevel1.get(0).height*0.5f+20),
//            new Vector2(848, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
//            new Vector2(925, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
//            new Vector2(1000, 100 + 20 + blocksLevel1.get(0).height*0.5f+26)
//        ));
//        Array<pig<?>> pigsLevel1=new Array<>();
//        pigsLevel1.add(new pig1("pig1a.png",world));
//        pigsLevel1.add(new pig2("pig2a.png",world));
//        pigsLevel1.add(new pig2("pig2a.png",world));
//        List<Vector2> pigsPositionsLevel1 = new ArrayList<>(Arrays.asList(
//            new Vector2(928-50, 100),
//            new Vector2(925, 100 + 20 + blocksLevel1.get(0).height*0.5f+35+blocksLevel1.get(8).height*0.5f),
//            new Vector2(1012-50, 100)
//        ));
//        blocks.addAll(blocksLevel1);
//        pigs.addAll(pigsLevel1);
//        blockPositions.addAll(blockPositionsLevel1);
//        pigPositions.addAll(pigsPositionsLevel1);
//    }
//    public static void getLevel1(World world, Array<block<?>> blocks, Array<pig<?>> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){
//        Array<block<?>> blocksLevel2=new Array<>();
//        blocksLevel2.add(new steelBlock("sb4.png",world));
//        blocksLevel2.add(new iceBlock("ib4.png",world));
//        blocksLevel2.add(new iceBlock("ib4.png",world));
//        blocksLevel2.add(new iceBlock("ib3.png",world));
//        blocksLevel2.add(new steelBlock("sb3.png",world));
//        blocksLevel2.add(new iceBlock("ib3.png",world));
//        blocksLevel2.add(new steelBlock("sb2.png",world));
//        blocksLevel2.add(new steelBlock("sb2.png",world));
//        blocksLevel2.add(new steelBlock("sb2.png",world));
//        blocksLevel2.add(new iceBlock("ib2.png",world));
//        blocksLevel2.add(new steelBlock("sb1.png",world));
//        blocksLevel2.add(new steelBlock("sb1.png",world));
//        blocksLevel2.add(new iceBlock("ib1.png",world));
//        List<Vector2> blockPositionsLevel2 = new ArrayList<>(Arrays.asList(
//            new Vector2(800, 100),
//            new Vector2(800+blocksLevel2.get(0).width+20, 100),
//            new Vector2(840+2*blocksLevel2.get(0).width, 100),
//            new Vector2(800+15  , 100+blocksLevel2.get(0).height),
//            new Vector2(800+blocksLevel2.get(0).width+20, 100+blocksLevel2.get(0).height),
//            new Vector2(825+2*blocksLevel2.get(0).width, 100+blocksLevel2.get(0).height),
//            new Vector2(820, 100 + blocksLevel2.get(0).height+40),
//            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+40),
//            new Vector2(820+2*blocksLevel2.get(0).width, 100 + blocksLevel2.get(0).height+40),
//            new Vector2(820, 100 + blocksLevel2.get(0).height+60),
//            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+60),
//            new Vector2(820+2*blocksLevel2.get(0).width, 100 + blocksLevel2.get(0).height+60),
//            new Vector2(920, 100 + blocksLevel2.get(0).height+90)
//        ));
//        Array<pig<?>> pigsLevel2=new Array<>();
//        pigsLevel2.add(new pig1("pig1a.png",world));
//        pigsLevel2.add(new pig2("pig1a.png",world));
//        pigsLevel2.add(new pig2("pig2a.png",world));
//        List<Vector2> pigsPositionsLevel2 = new ArrayList<>(Arrays.asList(
//            new Vector2(870, 100),
//            new Vector2(980, 100),
//            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+120)
//        ));
//        blocks.addAll(blocksLevel2);
//        pigs.addAll(pigsLevel2);
//        blockPositions.addAll(blockPositionsLevel2);
//        pigPositions.addAll(pigsPositionsLevel2);
//    }
    public static void getLevel1(World world, Array<block<?>> blocks, Array<pig<?>> pigs, List<Vector2> blockPositions,List<Vector2> pigPositions){
        Array<block<?>> blocksLevel3=new Array<>();
        blocksLevel3.add(new steelBlock("sb4.png",world));
        blocksLevel3.add(new iceBlock("ib4.png",world));
        blocksLevel3.add(new iceBlock("ib4.png",world));
        blocksLevel3.add(new iceBlock("ib3.png",world));
        blocksLevel3.add(new steelBlock("sb3.png",world));
        blocksLevel3.add(new iceBlock("ib3.png",world));
        blocksLevel3.add(new steelBlock("sb2.png",world));
        blocksLevel3.add(new steelBlock("sb2.png",world));
        blocksLevel3.add(new steelBlock("sb2.png",world));
        blocksLevel3.add(new iceBlock("ib2.png",world));
        blocksLevel3.add(new steelBlock("sb1.png",world));
        blocksLevel3.add(new steelBlock("sb1.png",world));
        blocksLevel3.add(new iceBlock("ib1.png",world));
        List<Vector2> blockPositionsLevel3 = new ArrayList<>(Arrays.asList(
            new Vector2(800, 100),
            new Vector2(800+blocksLevel3.get(0).width+20, 100),
            new Vector2(840+2*blocksLevel3.get(0).width, 100),
            new Vector2(800+15  , 100+blocksLevel3.get(0).height),
            new Vector2(800+blocksLevel3.get(0).width+20, 100+blocksLevel3.get(0).height),
            new Vector2(825+2*blocksLevel3.get(0).width, 100+blocksLevel3.get(0).height),
            new Vector2(820, 100 + blocksLevel3.get(0).height+40),
            new Vector2(800+blocksLevel3.get(0).width+20, 100 + blocksLevel3.get(0).height+40),
            new Vector2(820+2*blocksLevel3.get(0).width, 100 + blocksLevel3.get(0).height+40),
            new Vector2(820, 100 + blocksLevel3.get(0).height+60),
            new Vector2(800+blocksLevel3.get(0).width+20, 100 + blocksLevel3.get(0).height+60),
            new Vector2(820+2*blocksLevel3.get(0).width, 100 + blocksLevel3.get(0).height+60),
            new Vector2(920, 100 + blocksLevel3.get(0).height+90)
        ));
        Array<pig<?>> pigsLevel3=new Array<>();
        pigsLevel3.add(new pig1("pig1a.png",world));
        pigsLevel3.add(new pig2("pig1a.png",world));
        pigsLevel3.add(new pig2("pig2a.png",world));
        List<Vector2> pigsPositionsLevel3 = new ArrayList<>(Arrays.asList(
            new Vector2(870, 100),
            new Vector2(980, 100),
            new Vector2(800+blocksLevel3.get(0).width+20, 100 + blocksLevel3.get(0).height+120)
        ));
        blocks.addAll(blocksLevel3);
        pigs.addAll(pigsLevel3);
        blockPositions.addAll(blockPositionsLevel3);
        pigPositions.addAll(pigsPositionsLevel3);

    }
}
