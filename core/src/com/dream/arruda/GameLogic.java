package com.dream.arruda;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ti on 27/06/2016.
 */
public class GameLogic {
    public Background bg;
    public Ship ship;
    private Game g;
    private GameView gv;
    private Vector3 touchPos;

    public GameLogic(Game g, GameView gv){
        bg=new Background(g,gv);
        ship=new Ship(g,gv);
        this.g=g;
        this.gv=gv;
        touchPos=new Vector3();
    }

    public int Update(float fps){
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            g.backpressed=true;
            g.setScreen(new MenuView(g));
            gv.dispose();
        }
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            g.camera.unproject(touchPos);
            if(touchPos.x<=ship.shippos.x+ship.ship.getRegionHeight())
                ship.shippos.set(ship.shippos.x,touchPos.y-ship.ship.getRegionHeight()/2);
        }

        bg.Update(fps);
        return 0;
    }
}
