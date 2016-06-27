package com.dream.arruda;


/**
 * Created by ti on 27/06/2016.
 */
public class GameLogic {
    public Background bg;

    public GameLogic(Game g, GameView gv){
        bg=new Background(g,gv);
    }

    public void Update(float fps){
        bg.Update(fps);
    }
}
