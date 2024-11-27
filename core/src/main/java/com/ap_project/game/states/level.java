package com.ap_project.game.states;
import com.ap_project.game.sprites.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class level {
    public static void getLevel1(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions){
        ArrayList<block<?>> blocksLevel1=new ArrayList<>();
        System.out.println("level constructor called");
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
            new Vector2(850, 100),
            new Vector2(900  + 25, 100),
            new Vector2(900 + 25, 100 + blocksLevel1.get(2).height-20),
            new Vector2(950  + 28 + 25, 100),
            new Vector2(850, 100+blocksLevel1.get(0).height*0.5f+20),
            new Vector2(850+blocksLevel1.get(4).width/2, 100+blocksLevel1.get(0).height*0.5f+20),
            new Vector2(848, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
            new Vector2(925, 100 + 20 + blocksLevel1.get(0).height*0.5f+26),
            new Vector2(1000, 100 + 20 + blocksLevel1.get(0).height*0.5f+26)
        ));
        ArrayList<pig<?>> pigsLevel1=new ArrayList<>();
        pigsLevel1.add(new pig1("pig1a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));
        pigsLevel1.add(new pig2("pig2a.png",world));
        List<Vector2> pigsPositionsLevel1 = new ArrayList<>(Arrays.asList(
            new Vector2(928-50, 100),
            new Vector2(925, 100 + 20 + blocksLevel1.get(0).height*0.5f+35+blocksLevel1.get(8).height*0.5f),
            new Vector2(1012-50, 100)
        ));
        ArrayList<bird<?>> birdsLevel1=new ArrayList<>();
        birdsLevel1.add(new redBird("redBird.png",world));
        birdsLevel1.add(new chuck("chuck.png",world));
        birdsLevel1.add(new bomb("bomb.png",world));
        List<Vector2> birdsPositionsLevel1 = new ArrayList<>(Arrays.asList(
            new Vector2(170, 105),
            new Vector2(130, 105),
            new Vector2(90, 105)
        ));
        for (block<?> b : blocksLevel1) {
            blocks.add(b);
        }
        for (pig<?> p : pigsLevel1) {
            pigs.add(p);
        }
        for (bird<?> b : birdsLevel1) {
            birds.add(b);
        }
        blockPositions.addAll(blockPositionsLevel1);
        pigPositions.addAll(pigsPositionsLevel1);
        birdsPositions.addAll(birdsPositionsLevel1);
        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).setVelocity(new Vector2(0,0));
            birds.get(i).setPosition(birdsPositionsLevel1.get(i));
        }
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setVelocity(new Vector2(0,0));
            blocks.get(i).setPosition(blockPositionsLevel1.get(i));
        }
        for(int i=0;i<pigs.size();i++){
            pigs.get(i).setVelocity(new Vector2(0,0));
            pigs.get(i).setPosition(pigsPositionsLevel1.get(i));
        }
    }
    public static void getLevel2(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions){
        Array<block<?>> blocksLevel2=new Array<>();
        blocksLevel2.add(new steelBlock("sb4.png",world));
        blocksLevel2.add(new iceBlock("ib4.png",world));
        blocksLevel2.add(new iceBlock("ib4.png",world));
        blocksLevel2.add(new iceBlock("ib3.png",world));
        blocksLevel2.add(new steelBlock("sb3.png",world));
        blocksLevel2.add(new iceBlock("ib3.png",world));
        blocksLevel2.add(new steelBlock("sb2.png",world));
        blocksLevel2.add(new steelBlock("sb2.png",world));
        blocksLevel2.add(new steelBlock("sb2.png",world));
        blocksLevel2.add(new iceBlock("ib2.png",world));
        blocksLevel2.add(new steelBlock("sb1.png",world));
        blocksLevel2.add(new steelBlock("sb1.png",world));
        blocksLevel2.add(new iceBlock("ib1.png",world));
        List<Vector2> blockPositionsLevel2 = new ArrayList<>(Arrays.asList(
            new Vector2(800, 100),
            new Vector2(800+blocksLevel2.get(0).width+20, 100),
            new Vector2(840+2*blocksLevel2.get(0).width, 100),
            new Vector2(800+15  , 100+blocksLevel2.get(0).height),
            new Vector2(800+blocksLevel2.get(0).width+20, 100+blocksLevel2.get(0).height),
            new Vector2(825+2*blocksLevel2.get(0).width, 100+blocksLevel2.get(0).height),
            new Vector2(820, 100 + blocksLevel2.get(0).height+40),
            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+40),
            new Vector2(820+2*blocksLevel2.get(0).width, 100 + blocksLevel2.get(0).height+40),
            new Vector2(820, 100 + blocksLevel2.get(0).height+60),
            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+60),
            new Vector2(820+2*blocksLevel2.get(0).width, 100 + blocksLevel2.get(0).height+60),
            new Vector2(920, 100 + blocksLevel2.get(0).height+90)
        ));
        Array<pig<?>> pigsLevel2=new Array<>();
        pigsLevel2.add(new pig1("pig1a.png",world));
        pigsLevel2.add(new pig2("pig1a.png",world));
        pigsLevel2.add(new pig2("pig2a.png",world));
        List<Vector2> pigsPositionsLevel2 = new ArrayList<>(Arrays.asList(
            new Vector2(870, 100),
            new Vector2(980, 100),
            new Vector2(800+blocksLevel2.get(0).width+20, 100 + blocksLevel2.get(0).height+120)
        ));
        Array<bird<?>> birdsLevel2=new Array<>();
        birdsLevel2.add(new redBird("redBird.png",world));
        birdsLevel2.add(new chuck("chuck.png",world));
        birdsLevel2.add(new bomb("bomb.png",world));
        List<Vector2> birdsPositionsLevel2 = new ArrayList<>(Arrays.asList(
            new Vector2(170, 105),
            new Vector2(130, 105),
            new Vector2(90, 105)
        ));
        for (block<?> b : blocksLevel2) {
            blocks.add(b);
        }
        for (pig<?> p : pigsLevel2) {
            pigs.add(p);
        }
        for (bird<?> b : birdsLevel2) {
            birds.add(b);
        }
        blockPositions.addAll(blockPositionsLevel2);
        pigPositions.addAll(pigsPositionsLevel2);
        birdsPositions.addAll(birdsPositionsLevel2);
        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).setVelocity(new Vector2(0,0));
            birds.get(i).setPosition(birdsPositionsLevel2.get(i));
        }
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setVelocity(new Vector2(0,0));
            blocks.get(i).setPosition(blockPositionsLevel2.get(i));
        }
        for(int i=0;i<pigs.size();i++){
            pigs.get(i).setVelocity(new Vector2(0,0));
            pigs.get(i).setPosition(pigsPositionsLevel2.get(i));
        }
    }
    public static void getLevel3(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions){
        Array<block<?>> blocksLevel3=new Array<>();
        blocksLevel3.add(new steelBlock("sb6.png",world));
        blocksLevel3.add(new woodenBlock("wb17.png",world));
        blocksLevel3.add(new iceBlock("ib5.png",world));
        blocksLevel3.add(new iceBlock("ib1.png",world));
        blocksLevel3.add(new steelBlock("sb5.png",world));
        blocksLevel3.add(new steelBlock("ib6.png",world));
        blocksLevel3.add(new iceBlock("sb7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new iceBlock("ib7.png",world));
        blocksLevel3.add(new steelBlock("ib6.png",world));
        blocksLevel3.add(new steelBlock("ib6.png",world));
        blocksLevel3.add(new woodenBlock("wb15.png",world));
        blocksLevel3.add(new woodenBlock("wb15.png",world));
        blocksLevel3.add(new woodenBlock("wb16.png",world));

        List<Vector2> blockPositionsLevel3 = new ArrayList<>(Arrays.asList(
            new Vector2(800, 100),
            new Vector2(800+blocksLevel3.get(0).width+20, 100),
            new Vector2(840+2*blocksLevel3.get(0).width, 100),
            new Vector2(800+70 , 100+90),
            new Vector2(840+2*blocksLevel3.get(0).width-70, 100+80),
            new Vector2(900, 100+80+20),
            new Vector2(1020, 100 +80+20),
            new Vector2(880, 100+80+50),
            new Vector2(900, 100 + 80+50),
            new Vector2(920, 100 + 80+50),
            new Vector2(1040, 100 + 80+50),
            new Vector2(1020, 100 + 80+50),
            new Vector2(1000, 100 + 80+50),
            new Vector2(900, 100+80+80),
            new Vector2(1020, 100+80+80),
            new Vector2(900, 100+80+120),
            new Vector2(1020, 100+80+120),
            new Vector2(960, 100+80+150)
        ));
        Array<pig<?>> pigsLevel3=new Array<>();
        pigsLevel3.add(new pig1("pig2a.png",world));
        pigsLevel3.add(new pig2("pig1a.png",world));
        pigsLevel3.add(new pig2("pig1a.png",world));
        List<Vector2> pigsPositionsLevel3 = new ArrayList<>(Arrays.asList(
            new Vector2(960, 100+80+180),
            new Vector2(880, 100),
            new Vector2(1040, 100 )
        ));
        Array<bird<?>> birdsLevel3=new Array<>();
        birdsLevel3.add(new redBird("redBird.png",world));
        birdsLevel3.add(new chuck("chuck.png",world));
        birdsLevel3.add(new bomb("bomb.png",world));
        List<Vector2> birdsPositionsLevel3 = new ArrayList<>(Arrays.asList(
            new Vector2(170, 105),
            new Vector2(130, 105),
            new Vector2(90, 105)
        ));
        for (block<?> b : blocksLevel3) {
            blocks.add(b);
        }
        for (pig<?> p : pigsLevel3) {
            pigs.add(p);
        }
        for (bird<?> b : birdsLevel3) {
            birds.add(b);
        }
        blockPositions.addAll(blockPositionsLevel3);
        pigPositions.addAll(pigsPositionsLevel3);
        birdsPositions.addAll(birdsPositionsLevel3);
        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).setVelocity(new Vector2(0,0));
            birds.get(i).setPosition(birdsPositionsLevel3.get(i));
        }
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setVelocity(new Vector2(0,0));
            blocks.get(i).setPosition(blockPositionsLevel3.get(i));
        }
        for(int i=0;i<pigs.size();i++){
            pigs.get(i).setVelocity(new Vector2(0,0));
            pigs.get(i).setPosition(pigsPositionsLevel3.get(i));
        }
    }
    public static void getLevel1Textures(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions,ArrayList<Vector2> birdsVelocity,ArrayList<Vector2> pigsVelocity,ArrayList<Vector2> blocksVelocity,ArrayList<Integer> pigsHealth){
        blocks.get(0).setBlockTexture(new Texture("wb1.png"));
        blocks.get(1).setBlockTexture(new Texture("wb6.png"));
        blocks.get(2).setBlockTexture(new Texture("wb6.png"));
        blocks.get(3).setBlockTexture(new Texture("wb1.png"));
        blocks.get(4).setBlockTexture(new Texture("wb1r.png"));
        blocks.get(5).setBlockTexture(new Texture("wb1r.png"));
        blocks.get(6).setBlockTexture(new Texture("wb10.png"));
        blocks.get(7).setBlockTexture(new Texture("wb10.png"));
        blocks.get(8).setBlockTexture(new Texture("wb10.png"));

        pigs.get(0).setPigTexture(new Texture("pig1a.png"));
        pigs.get(1).setPigTexture(new Texture("pig2a.png"));
        pigs.get(2).setPigTexture(new Texture("pig2a.png"));

        birds.get(0).setBirdTexture(new Texture("redBird.png"));
        birds.get(1).setBirdTexture(new Texture("chuck.png"));
        birds.get(2).setBirdTexture(new Texture("bomb.png"));

        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).createBody(world);
            System.out.println("bone 1");
            birds.get(i).setPosition(birdsPositions.get(i));
            System.out.println("bone 2");
            birds.get(i).setVelocity(birdsVelocity.get(i));
            System.out.println("bone 3");
        }
        System.out.println("blocks size :"+blocks.size());
        for (int i = 0; i < blocks.size(); i++) {
            if(!blocks.get(i).isDestroyed()){
                blocks.get(i).createBody(world);
            }
            System.out.println("done 1");
            blocks.get(i).setPosition(blockPositions.get(i));
            System.out.println("done 2");
            System.out.println("size of block velocity "+blocksVelocity.size());
            blocks.get(i).setVelocity(blocksVelocity.get(i));
            System.out.println("done 3");
        }
        System.out.println("pigs size: "+pigs.size());
        for (int i = 0; i < pigs.size(); i++) {
            if(!pigs.get(i).isDestroyed()){
                pigs.get(i).createBody(world);
            }
            pigs.get(i).setPosition(pigPositions.get(i));
            pigs.get(i).setHits(pigsHealth.get(i));
            pigs.get(i).setVelocity(pigsVelocity.get(i));
        }
    }
    public static void getLevel2Textures(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions,ArrayList<Vector2> birdsVelocity,ArrayList<Vector2> pigsVelocity,ArrayList<Vector2> blocksVelocity,ArrayList<Integer> pigsHealth) {
        blocks.get(0).setBlockTexture(new Texture("sb4.png"));
        blocks.get(1).setBlockTexture(new Texture("ib4.png"));
        blocks.get(2).setBlockTexture(new Texture("ib4.png"));
        blocks.get(3).setBlockTexture(new Texture("ib3.png"));
        blocks.get(4).setBlockTexture(new Texture("sb3.png"));
        blocks.get(5).setBlockTexture(new Texture("ib3.png"));
        blocks.get(6).setBlockTexture(new Texture("sb2.png"));
        blocks.get(7).setBlockTexture(new Texture("sb2.png"));
        blocks.get(8).setBlockTexture(new Texture("sb2.png"));
        blocks.get(9).setBlockTexture(new Texture("ib2.png"));
        blocks.get(10).setBlockTexture(new Texture("sb1.png"));
        blocks.get(11).setBlockTexture(new Texture("sb1.png"));
        blocks.get(12).setBlockTexture(new Texture("ib1.png"));

        pigs.get(0).setPigTexture(new Texture("pig1a.png"));
        pigs.get(1).setPigTexture(new Texture("pig1a.png"));
        pigs.get(2).setPigTexture(new Texture("pig2a.png"));

        birds.get(0).setBirdTexture(new Texture("redBird.png"));
        birds.get(1).setBirdTexture(new Texture("chuck.png"));
        birds.get(2).setBirdTexture(new Texture("bomb.png"));

        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).createBody(world);
            System.out.println("bone 1");
            birds.get(i).setPosition(birdsPositions.get(i));
            System.out.println("bone 2");
            birds.get(i).setVelocity(birdsVelocity.get(i));
            System.out.println("bone 3");
        }
        System.out.println("blocks size :" + blocks.size());
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).createBody(world);
            System.out.println("done 1");
            blocks.get(i).setPosition(blockPositions.get(i));
            System.out.println("done 2");
            System.out.println("size of block velocity " + blocksVelocity.size());
            blocks.get(i).setVelocity(blocksVelocity.get(i));
            System.out.println("done 3");
        }
        System.out.println("pigs size: " + pigs.size());
        for (int i = 0; i < pigs.size(); i++) {
            pigs.get(i).createBody(world);
            pigs.get(i).setPosition(pigPositions.get(i));
            pigs.get(i).setHits(pigsHealth.get(i));
            pigs.get(i).setVelocity(pigsVelocity.get(i));
        }
    }
    public static void getLevel3Textures(World world, ArrayList<block<?>> blocks, ArrayList<pig<?>> pigs,ArrayList<bird<?>> birds, List<Vector2> blockPositions,List<Vector2> pigPositions,List<Vector2> birdsPositions,ArrayList<Vector2> birdsVelocity,ArrayList<Vector2> pigsVelocity,ArrayList<Vector2> blocksVelocity,ArrayList<Integer> pigsHealth) {
        blocks.get(0).setBlockTexture(new Texture("sb6.png"));
        blocks.get(1).setBlockTexture(new Texture("wb17.png"));
        blocks.get(2).setBlockTexture(new Texture("ib5.png"));
        blocks.get(3).setBlockTexture(new Texture("ib1.png"));
        blocks.get(4).setBlockTexture(new Texture("sb5.png"));
        blocks.get(5).setBlockTexture(new Texture("ib6.png"));
        blocks.get(6).setBlockTexture(new Texture("sb7.png"));
        blocks.get(7).setBlockTexture(new Texture("ib7.png"));
        blocks.get(8).setBlockTexture(new Texture("ib7.png"));
        blocks.get(9).setBlockTexture(new Texture("ib7.png"));
        blocks.get(10).setBlockTexture(new Texture("ib7.png"));
        blocks.get(11).setBlockTexture(new Texture("ib7.png"));
        blocks.get(12).setBlockTexture(new Texture("ib7.png"));
        blocks.get(13).setBlockTexture(new Texture("ib6.png"));
        blocks.get(14).setBlockTexture(new Texture("ib6.png"));
        blocks.get(15).setBlockTexture(new Texture("wb15.png"));
        blocks.get(16).setBlockTexture(new Texture("wb15.png"));
        blocks.get(17).setBlockTexture(new Texture("wb16.png"));

        pigs.get(0).setPigTexture(new Texture("pig2a.png"));
        pigs.get(1).setPigTexture(new Texture("pig1a.png"));
        pigs.get(2).setPigTexture(new Texture("pig2a.png"));

        birds.get(0).setBirdTexture(new Texture("redBird.png"));
        birds.get(1).setBirdTexture(new Texture("chuck.png"));
        birds.get(2).setBirdTexture(new Texture("bomb.png"));

        for (int i = 0; i < birds.size(); i++) {
            birds.get(i).createBody(world);
            System.out.println("bone 1");
            birds.get(i).setPosition(birdsPositions.get(i));
            System.out.println("bone 2");
            birds.get(i).setVelocity(birdsVelocity.get(i));
            System.out.println("bone 3");
        }
        System.out.println("blocks size :" + blocks.size());
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).createBody(world);
            System.out.println("done 1");
            blocks.get(i).setPosition(blockPositions.get(i));
            System.out.println("done 2");
            System.out.println("size of block velocity " + blocksVelocity.size());
            blocks.get(i).setVelocity(blocksVelocity.get(i));
            System.out.println("done 3");
        }
        System.out.println("pigs size: " + pigs.size());
        for (int i = 0; i < pigs.size(); i++) {
            pigs.get(i).createBody(world);
            pigs.get(i).setPosition(pigPositions.get(i));
            pigs.get(i).setHits(pigsHealth.get(i));
            pigs.get(i).setVelocity(pigsVelocity.get(i));
        }
    }

}
