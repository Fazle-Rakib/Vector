package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;

public class deathHandle implements Screen {
    private SpriteBatch batch;
    MyGdxGame game;
    Texture deathTex;
    Sound sGameOver,sDeath;
    int i;
    MenuScreen screen1;
    PlayScreen sc1;
    Play2ndScreen sc2;
    private TextureAtlas Dat;
    private Animation anim;
    float clock = 0;

    public deathHandle(MyGdxGame game,int i)
    {
        this.game = game;
        this.i = i;
        //batch = new SpriteBatch();
        deathTex = new Texture("Object//GameOverCover.png");
        Dat = new TextureAtlas(Gdx.files.internal("PlayerDead//Player_D.atlas"));
        anim = new Animation(1/3f,Dat.getRegions());
        sGameOver = Gdx.audio.newSound(Gdx.files.internal("Sound//gameOver.mp3"));
        //sDeath = Gdx.audio.newSound(Gdx.files.internal("Sound//Death1.mp3"));
        sGameOver.loop();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0,1,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        clock += Gdx.graphics.getDeltaTime();
        //sDeath.play(0.5f);
        game.batch.begin();
        if(clock < 2.8)
        {
            game.batch.draw((TextureRegion) anim.getKeyFrame(clock,true),50,25);
        }
        else
        {
            //sDeath.pause();
            game.batch.draw(deathTex,0,0);
        }
        game.batch.end();
    }
    private void handleInput()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            sGameOver.pause();
            screen1 = new MenuScreen(game);
            game.setScreen(screen1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.R))
        {

            if(i == 1)
            {
                sGameOver.pause();
                sc1 = new PlayScreen(game);
                game.setScreen(sc1);
            }
            if(i == 2)
            {
                sGameOver.pause();
                sc2 = new Play2ndScreen(game);
                game.setScreen(sc2);
            }
        }

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
        game.batch.dispose();
        //sDeath.dispose();
        sGameOver.dispose();
    }
}
