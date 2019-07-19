package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.ApplicationAdapter;

import java.awt.*;

public class PlayScreen implements Screen{
    private MyGdxGame game;
    private float elapsedTime = 0,clock = 0;

    private OrthographicCamera camera;
    private Viewport viewport;
    int  sourceX = 0;
    float cx,cy;
    int player_x = 50,player_y = 25,incre = 0;
    Integer PlayerFreame = 0;
/****/ Sound sBack,sWin,sHit;
        boolean Hit = false,Death = false,Win = false;
        Texture winTex;
        deathHandle deathScreen;
        MenuScreen Screen1;
    //private Viewport gameport;

    public PlayScreen(MyGdxGame game){
        this.game = game;
/*****/
        sBack = Gdx.audio.newSound(Gdx.files.internal("Sound//play.mp3"));
        sHit = Gdx.audio.newSound(Gdx.files.internal("Sound//hit.mp3"));
        sWin = Gdx.audio.newSound(Gdx.files.internal("Sound//Win.mp3"));
        sBack.loop(0.3f);

        //camera setup
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
        //gameport = new FillViewport(100,400,gamecam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        if(Death)
        {
            sBack.pause();
            deathScreen = new deathHandle(game,1);
            game.setScreen(deathScreen);
        }
        if(Hit)
        {
            sHit.play();
        }
        if(Win)
        {
            //handleWin();
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.begin();
        sourceX += 1;

        cx  = camera.position.x = elapsedTime += Gdx.graphics.getDeltaTime()*100;
      
       
        if(PlayerFreame == 2)
        {
            //System.out.println(clock);
            clock += Gdx.graphics.getDeltaTime()*100;
            game.batch.draw((TextureRegion) Player_1_JG.getKeyFrame(clock,true ),(int)(player_x+incre*1.2),player_y);
            while(clock >= 140)
            {
                camera.position.x += incre;
                PlayerFreame = 0;
                clock = 0;
                incre = 0;
            }
            incre++;
        }
		game.batch.end();
        
    }

    private void handleInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            sBack.pause();
            Screen1 = new MenuScreen(game);
            game.setScreen(Screen1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            PlayerFreame = 1;
            clock = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            PlayerFreame = 2;
            clock = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            Death = true;
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
        A_Atlas.dispose();
        sBack.dispose();
        sWin.dispose();
        sHit.dispose();
    }
}