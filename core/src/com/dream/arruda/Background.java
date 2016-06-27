package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Random;

/**
 * Created by ti on 27/06/2016.
 */
public class Background {
    public TextureRegion sky;
    public TextureRegion land;
    public TextureRegion cloud;
    private Vector2 dir;
    public Vector2 skypos1;
    public Vector2 skypos2;
    private float skyvel;
    public Vector2 landpos1;
    public Vector2 landpos2;
    private float landvel;
    public Array<Vector2> cloudpos;
    private float cloudvel;
    private int w;
    private int h;

    public Background(Game g, GameView gv){
        sky=gv.atlas.findRegion("sky");
        land=gv.atlas.findRegion("land");
        cloud=gv.atlas.findRegion("cloud");
        dir=new Vector2(-1,0);
        skypos1=new Vector2(0,0);
        skypos2=new Vector2(skypos1.x+sky.getRegionWidth(),0);
        skyvel=g.width/3;
        landpos1=new Vector2(0,0);
        landpos2=new Vector2(landpos1.x+land.getRegionWidth(),0);
        landvel=g.width/2;
        cloudpos=new Array<Vector2>();
        while (cloudpos.size<12)
            cloudpos.add(new Vector2(Rand(g.width,g.width*2), Rand(0,g.height-cloud.getRegionHeight())));
        cloudvel=g.width/4;
        w=g.width;
        h=g.height;
    }

    public void Update(float fps){
        skypos1.add(dir.cpy().scl(skyvel*fps));
        skypos2.add(dir.cpy().scl(skyvel*fps));
        if(skypos1.x+sky.getRegionWidth()<=0)
            skypos1.set(skypos2.x+sky.getRegionWidth(),skypos2.y);
        if(skypos2.x+sky.getRegionWidth()<=0)
            skypos2.set(skypos1.x+sky.getRegionWidth(),skypos1.y);
        landpos1.add(dir.cpy().scl(landvel*fps));
        landpos2.add(dir.cpy().scl(landvel*fps));
        if(landpos1.x+land.getRegionWidth()<=0)
            landpos1.set(landpos2.x+land.getRegionWidth(),landpos2.y);
        if(landpos2.x+land.getRegionWidth()<=0)
            landpos2.set(landpos1.x+land.getRegionWidth(),landpos1.y);
        for(int i=0;i<=cloudpos.size-1;i++) {
            cloudpos.get(i).add(dir.cpy().scl(cloudvel * fps));
            if(cloudpos.get(i).x+cloud.getRegionWidth()<=0)
                cloudpos.get(i).set(new Vector2(Rand(w,w*2),Rand(0,h-cloud.getRegionHeight())));
        }
    }

    private int Rand(int min, int max){
        long seed = TimeUtils.nanoTime();
        seed ^= (seed << 21);
        seed ^= (seed >>> 35);
        seed ^= (seed << 4);
        int out = (int) seed;
        return new Random(out).nextInt(max)+min;

    }
}
