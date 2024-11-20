package com.ap_project.game.states;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

public abstract class abstractState {
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected abstractState() {
        cam=new OrthographicCamera();
        mouse=new Vector3();
    }

    protected abstract void handleInput();

    protected abstract void update(float dt);

    public abstract void create();
}
