package com.ap_project.game.states;

import com.ap_project.game.Core;
import com.ap_project.game.sprites.bird;
import com.ap_project.game.sprites.block;
import com.ap_project.game.sprites.pig;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class collisionHandler implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();
        handleCollision(userDataA, userDataB);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
    private void handleCollision(Object objA, Object objB) {
        if (objA instanceof bird<?> && objB instanceof block<?>) {
            block<?> block = (block<?>) objB;
            block.decreaseHitPoints();
            checkAndRemoveBlock(block);
        } else if (objA instanceof block<?> && objB instanceof pig<?>) {
            pig<?> pig = (pig<?>) objB;
            pig.decreaseHitPoints();
            checkAndRemovePig(pig);
        } else if (objA instanceof bird<?> && objB instanceof pig<?>) {
            pig<?> pig = (pig<?>) objB;
            pig.decreaseHitPoints();
            checkAndRemovePig(pig);
        }else if (objA instanceof block<?> && objB instanceof block<?>) {
            block<?> block = (block<?>) objB;
            block.decreaseHitPoints();
            checkAndRemoveBlock(block);}
    }
    private void checkAndRemoveBlock(block<?> block) {
        if (block.isDestroyed()) {
            removeBlock(block);
        }
    }
    private void checkAndRemovePig(pig<?> pig) {
        if (pig.isDestroyed()) {
            removePig(pig);
        }
    }
    private void removeBlock(block<?> block) {
        Core.playScreen.queueBodyForDestruction(block.getBody(),block);
        Core.playScreen.getBlocks().removeValue(block, true);
    }

    private void removePig(pig<?> pig) {
        Core.playScreen.queueBodyForDestruction(pig.getBody(),pig);
        Core.playScreen.getPigs().removeValue(pig, true);
        System.out.println("pigs size :" + Core.playScreen.getPigs().size);
    }


}
