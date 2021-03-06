package com.dream.arruda;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

/**
 * Created by ti on 28/06/2016.
 */
public class Enemy {
    public Vector2 enemypos;
    private Vector2 dir;
    private float enemyvel;
    private Game game;
    private GameView gameView;
    public Rectangle collisiion;
    public TextureRegion currentFrame;
    private Animation moveenemy;
    private float state;

    public Enemy(Game g, GameView gv, GameLogic gl){
        enemypos=new Vector2(Rand(g.width,g.width*2),Rand(0,g.height-gv.atlas.findRegion("enemy").getRegionHeight()));
        dir=new Vector2(-1,0);
        enemyvel=Rand(g.width/6,g.width/2);
        game=g;
        gameView=gv;
        collisiion=new Rectangle(enemypos.x,enemypos.y,
                gv.atlas.findRegion("enemy").getRotatedPackedWidth(),gv.atlas.findRegion("enemy").getRegionHeight());
        moveenemy=new Animation(0.25f,gl.enemy);
        state=0f;
        currentFrame=moveenemy.getKeyFrame(state,true);
    }

    private int Rand(int min, int max){
        long seed = TimeUtils.nanoTime();
        seed ^= (seed << 21);
        seed ^= (seed >>> 35);
        seed ^= (seed << 4);
        return new Random(seed).nextInt(max)+min;
    }

    public void Update(float fps){
        state+=fps;
        currentFrame=moveenemy.getKeyFrame(state,true);
        enemypos.add(dir.cpy().scl(enemyvel*fps));
        if(enemypos.x+gameView.atlas.findRegion("enemy").getRegionWidth()/4<=0)
            setpos();
        collisiion.set(enemypos.x,enemypos.y,
                gameView.atlas.findRegion("enemy").getRotatedPackedWidth(),gameView.atlas.findRegion("enemy").getRegionHeight());
    }

    public void setpos(){
        enemypos.set(Rand(game.width,game.width*2),Rand(0,game.height-gameView.atlas.findRegion("enemy").getRegionHeight()));
    }
}
