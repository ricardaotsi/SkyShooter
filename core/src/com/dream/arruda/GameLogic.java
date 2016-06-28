package com.dream.arruda;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by ti on 27/06/2016.
 */
public class GameLogic implements InputProcessor{
    public Background bg;
    public Ship ship;
    private Game game;
    private GameView gameView;
    private Vector3 touchPos;

    public GameLogic(Game g, GameView gv){
        bg=new Background(g,gv);
        ship=new Ship(g,gv);
        touchPos=new Vector3();
        Gdx.input.setInputProcessor(this);
        game=g;
        gameView=gv;
    }

    public void Update(float fps){
        bg.Update(fps);
        ship.Update(fps);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode==Input.Keys.BACK){
            game.backpressed=true;
            game.setScreen(new MenuView(game));
            gameView.dispose();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, 0);
        game.camera.unproject(touchPos);
        if(touchPos.x>=ship.shippos.x+ship.ship.getRegionWidth())
            ship.AddLaser();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchPos.set(screenX, screenY, 0);
        game.camera.unproject(touchPos);
        if(touchPos.x<=ship.shippos.x+ship.ship.getRegionWidth())
            ship.shippos.set(ship.shippos.x,touchPos.y-ship.ship.getRegionHeight()/2);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
