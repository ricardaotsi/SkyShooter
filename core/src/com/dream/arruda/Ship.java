package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ti on 27/06/2016.
 */
public class Ship {
    public TextureRegion ship;
    public Vector2 shippos;
    public TextureRegion laser;
    private Game game;
    public Rectangle shipcollision;
    public Array<Laser> laserobj;
    public int life;

    public Ship(Game g, GameView gv){
        ship=gv.atlas.findRegion("spaceship");
        laser=gv.atlas.findRegion("laser");
        shippos=new Vector2(g.width/12,g.height/2-ship.getRegionHeight()/2);
        game=g;
        shipcollision=new Rectangle(shippos.x,shippos.y,ship.getRegionWidth(),ship.getRegionHeight());
        laserobj=new Array<Laser>();
        life=10;
    }

    public void AddLaser(){
        laserobj.add(new Laser(game,this));
    }

    public void Update(float fps){
        for(int i=0;i<=laserobj.size-1;i++) {
            laserobj.get(i).Update(fps);
            if (laserobj.get(i).laserpos.x + laser.getRegionWidth() >= game.width)
                laserobj.removeIndex(i);
        }
        shipcollision=new Rectangle(shippos.x,shippos.y,ship.getRegionWidth(),ship.getRegionHeight());
    }
}
