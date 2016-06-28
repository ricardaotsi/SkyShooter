package com.dream.arruda;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ti on 28/06/2016.
 */
public class Laser {
    public Vector2 laserpos;
    private Vector2 dir;
    private float laservel;
    public Rectangle collision;
    private Ship s;

    public Laser(Game g, Ship s){
        laserpos=new Vector2(s.shippos.x,s.shippos.y+s.laser.getRegionHeight()/5*3);
        dir=new Vector2(1,0);
        laservel=g.width;
        collision=new Rectangle(laserpos.x,laserpos.y,s.laser.getRegionWidth(),s.laser.getRegionHeight());
        this.s =s;
    }

    public void Update(float fps) {
        laserpos.add(dir.cpy().scl(laservel * fps));
        collision.set(laserpos.x,laserpos.y,s.laser.getRegionWidth(),s.laser.getRegionHeight());
    }
}
