package com.dream.arruda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by ti on 27/06/2016.
 */
public class GameView implements Screen{

    private Game game;
    private GameLogic glogic;
    public TextureAtlas atlas;
    private float dt;

    public GameView(Game g){
        game=g;
        game.camera.setToOrtho(false, game.width, game.height);
        atlas=new TextureAtlas(Gdx.files.internal("skyshooterpack.pack"));
        glogic=new GameLogic(game, this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        dt= Gdx.graphics.getDeltaTime();
        glogic.Update(dt);
        Gdx.gl.glClearColor(0.3215f, 0.8313f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(glogic.bg.sky,glogic.bg.skypos1.x,glogic.bg.skypos1.y);
        game.batch.draw(glogic.bg.sky,glogic.bg.skypos2.x,glogic.bg.skypos2.y);
        game.batch.draw(glogic.bg.land,glogic.bg.landpos1.x,glogic.bg.landpos1.y);
        game.batch.draw(glogic.bg.land,glogic.bg.landpos2.x,glogic.bg.landpos2.y);
        for(int i=0;i<=glogic.bg.cloudpos.size-1;i++){
            game.batch.draw(glogic.bg.cloud,glogic.bg.cloudpos.get(i).x,glogic.bg.cloudpos.get(i).y);
        }
        for(int i=0; i<=glogic.ship.laserobj.size-1;i++){
            game.batch.draw(glogic.ship.laser, glogic.ship.laserobj.get(i).laserpos.x,glogic.ship.laserobj.get(i).laserpos.y);
        }
        for(int i=0;i<=glogic.enemys.size-1;i++)
                game.batch.draw(glogic.enemys.get(i).currentFrame, glogic.enemys.get(i).enemypos.x, glogic.enemys.get(i).enemypos.y);
        for(int i=0;i<=glogic.explosions.size-1;i++)
            game.batch.draw(glogic.explosions.get(i).currentFrameexplosion, glogic.explosions.get(i).pos.x, glogic.explosions.get(i).pos.y);
        game.batch.draw(glogic.ship.ship,glogic.ship.shippos.x,glogic.ship.shippos.y);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}
