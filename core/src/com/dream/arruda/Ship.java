package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ti on 27/06/2016.
 */
public class Ship {
    public TextureRegion ship;
    public Vector2 shippos;

    public Ship(Game g, GameView gv){
        ship=gv.atlas.findRegion("spaceship");
        shippos=new Vector2(g.width/12,g.height/2-ship.getRegionHeight()/2);
    }
}
