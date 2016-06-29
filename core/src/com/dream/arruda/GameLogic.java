package com.dream.arruda;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ti on 27/06/2016.
 */
public class GameLogic implements InputProcessor{
    public Background bg;
    public Ship ship;
    public Array<Enemy> enemys;
    public Array<Explosion> explosions;
    private Game game;
    private GameView gameView;
    private Vector3 touchPos;

    //enemy animation is loaded only one time and repeated for each enemy
    public TextureRegion[] enemy;
    public TextureRegion[] explosion;
    //-------------------------------------------------------------------

    public GameLogic(Game g, GameView gv){
        bg=new Background(g,gv);
        ship=new Ship(g,gv);
        enemys=new Array<Enemy>();
        explosions=new Array<Explosion>();
        game=g;
        gameView=gv;
        touchPos=new Vector3();
        Gdx.input.setInputProcessor(this);

        //enemy animation is loaded only one time and repeated for each enemy
        TextureRegion[][] tmp=gv.atlas.findRegion("enemy").split(gv.atlas.findRegion("enemy").getRegionWidth()/4,
                gv.atlas.findRegion("enemy").getRegionHeight());
        enemy=new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
                enemy[index++] = tmp[0][i];
        }
        tmp=gv.atlas.findRegion("explosion").split(gv.atlas.findRegion("explosion").getRegionWidth()/6,
                gv.atlas.findRegion("explosion").getRegionHeight());
        explosion= new TextureRegion[6];
        index = 0;
        for (int i = 0; i < 6; i++) {
            explosion[index++] = tmp[0][i];
        }
        //-------------------------------------------------------------------
        while (enemys.size<8)
            enemys.add(new Enemy(g,gv,this));
    }

    public void Update(float fps){
        bg.Update(fps);
        ship.Update(fps);
        for(int a=0;a<=explosions.size-1;a++) {
            if(explosions.get(a).Update(fps))
                explosions.removeIndex(a);
        }
        for(int i=0;i<=enemys.size-1;i++) {
            enemys.get(i).Update(fps);
            if(Intersector.overlaps(enemys.get(i).collisiion,ship.shipcollision)) {
                explosions.add(new Explosion(this,enemys.get(i).enemypos));
                enemys.get(i).setpos();
            }
            for(int j=0;j<=ship.laserobj.size-1;j++){
                if(Intersector.overlaps(enemys.get(i).collisiion,ship.laserobj.get(j).collision)){
                    ship.laserobj.removeIndex(j);
                    explosions.add(new Explosion(this,enemys.get(i).enemypos));
                    enemys.get(i).setpos();
                }
            }
        }
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
        touchPos.set(screenX, screenY, 0);
        game.camera.unproject(touchPos);
        if(touchPos.x>=ship.shippos.x+ship.ship.getRegionWidth())
            ship.AddLaser();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

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
