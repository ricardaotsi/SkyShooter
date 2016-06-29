package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ti on 29/06/2016.
 */
public class Explosion {
    public Vector2 pos;
    private Animation aniexplosion;
    private float statexplosion;
    public TextureRegion currentFrameexplosion;

    public Explosion(GameLogic gl, Vector2 enemy){
        pos=new Vector2(enemy.x,enemy.y+gl.enemy[0].getRegionHeight()/2);
        aniexplosion=new Animation(0.05f,gl.explosion);
        statexplosion=0;
        currentFrameexplosion=aniexplosion.getKeyFrame(statexplosion,false);
    }

    public boolean Update(float fps){
        statexplosion+=fps;
        currentFrameexplosion=aniexplosion.getKeyFrame(statexplosion,false);
        if(aniexplosion.isAnimationFinished(statexplosion))
            return true;
        return false;
    }
}
