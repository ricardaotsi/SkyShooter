package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ti on 27/06/2016.
 */
public class Ship {
    public TextureRegion ship;
    public Vector2 shippos;
    public TextureRegion laser;
    public Array<Vector2> laserpos;
    private Vector2 dir;
    private float laservel;

    public Ship(Game g, GameView gv){
        ship=gv.atlas.findRegion("spaceship");
        laser=gv.atlas.findRegion("laser");
        shippos=new Vector2(g.width/12,g.height/2-ship.getRegionHeight()/2);
        laserpos=new Array<Vector2>();
        dir=new Vector2(1,0);
        laservel=g.width/1.5f;
    }

    public void AddLaser(){
        laserpos.add(new Vector2(shippos.x,shippos.y+laser.getRegionHeight()/5*3));
    }

    public void Update(float fps){
        for(int i=0; i<=laserpos.size-1;i++){
            laserpos.get(i).add(dir.cpy().scl(laservel*fps));
        }
    }
}
