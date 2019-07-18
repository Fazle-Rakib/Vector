package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
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
    private Texture skyTex,roadTex,backTex,exTex,h1Tex,h3Tex,
            objBarrol,objTire,objBlock1,objBlock2,objPad1,objPad2
            ,objStone1,player;
    private TextureAtlas A_Atlas;
    private Animation AnimE_1_A,AnimE_1_R,Player_1_R,Player_1_D,Player_1_J;
    private float elapsedTime = 0,clock = 0;
    Sprite test1,test2;
    private Rectangle rec1,rec2;
    private OrthographicCamera camera;
    private Viewport viewport;
    int  sourceX = 0;
    float cx,cy;
    int player_x = 50,player_y = 25,incre = 0;
    Integer PlayerFreame = 0;
    //private Viewport gameport;

    public PlayScreen(MyGdxGame game){
        this.game = game;

        //Texture Declaration
        skyTex = new Texture("Bright//Sky.png");
        h3Tex = new Texture("Bright//houses3.png");
        backTex = new Texture("Bright//back.png");
        h1Tex = new Texture("Bright//houses1.png");
        exTex = new Texture("Bright//ex1.png");
        roadTex = new Texture("Bright//road&lamps.png");
        //player = new Texture("Run.png");

        //objBarrol = new Texture("Object//Barrel1.png");
        test1 = new Sprite(new Texture("Object//Barrel_1.png"));
        //objTire = new Texture("Object//3Tire.png");
        test2 = new Sprite(new Texture("Object//3Tire.png"));
        objBlock1 = new Texture("Object//Block.png");
        objBlock2 = new Texture("Object//Block03.png");
        objPad1 = new Texture("Object//Pad01.png");
        objPad2 = new Texture("Object//Pad02.png");
        objStone1 = new Texture("Object//Stone01.png");

        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_A//Enemy_1_A.atlas"));
        AnimE_1_A = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_R//Enemy_1_R.atlas"));
        AnimE_1_R = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerRun//Player_R.atlas"));
        Player_1_R = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerDead//Player_D.atlas"));
        Player_1_D = new Animation(10/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerJump//Player_J.atlas"));
        Player_1_J = new Animation(25/1f,A_Atlas.getRegions());
        Player_1_J.setFrameDuration(15f);
        //Texture wrapping/looping
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        exTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

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
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        sourceX += 1;

        rec1 = test1.getBoundingRectangle();
        rec2 = test2.getBoundingRectangle();
        boolean isover = rec1.overlaps(rec2);
        if(isover)
        {
            //System.out.println("Overlapping");
        }
        else
        {
            //System.out.println("Not");
        }
        cx = camera.position.x = elapsedTime += Gdx.graphics.getDeltaTime()*100;
        game.batch.draw(skyTex,0,0,sourceX ,0,850,500);
        game.batch.draw(h3Tex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(backTex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(h1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(exTex,0,0,sourceX*2,0,850,500);
        game.batch.draw(roadTex,0,0,(int)(sourceX*2.2),0,850,500);

        //game.batch.draw((TextureRegion) AnimE_1_A.getKeyFrame(elapsedTime,true ),0,25);
        //game.batch.draw((TextureRegion) AnimE_1_R.getKeyFrame(elapsedTime,true ),0,10);
        if(PlayerFreame == 0 && clock == 0)
        {
            game.batch.draw((TextureRegion) Player_1_R.getKeyFrame(elapsedTime,true ),player_x,player_y);
        }
        if(PlayerFreame == 1)
        {
            System.out.println(clock);
            clock += Gdx.graphics.getDeltaTime()*100;
            game.batch.draw((TextureRegion) Player_1_J.getKeyFrame(clock,true ),(int)(player_x+incre*1.2),player_y);
            while(clock >= 130)
            {
                camera.position.x += incre;
                PlayerFreame = 0;
                clock = 0;
                incre = 0;
            }
            incre++;
        }
        //game.batch.draw((TextureRegion) Player_1_D.getKeyFrame(elapsedTime,true ),300,25);

        //game.batch.draw(player,300,25);
        test1.setPosition((int)((cx-1000)*-1.5),25);
        test1.draw(game.batch);

        //game.batch.draw(test2,(int)((cx-1000)*-1.5),-10);
        test2.setPosition(750,25);
        test2.draw(game.batch);

        game.batch.draw(objBlock1,(int)((cx-1600)*-2.5),25);
        game.batch.draw(objPad1,(int)((cx-2100)*-2.5),25);
        game.batch.draw(objPad2,(int)((cx-2600)*-2.5),20);
        //game.batch.draw(objBarrol,(int)((cx - 3050)*-1.5),25);
        game.batch.draw(objPad2,(int)((cx-3400)*-2.5),20);
        game.batch.draw(objBlock2,(int)((cx-3750)*-2.5),25);
        game.batch.draw(objStone1,(int)((cx-4000)*-2.5),25);

        game.batch.end();
    }

    private void handleInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            PlayerFreame = 1;
            clock = 0;
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
    }
}



/*
@Override
    public PlayScreen(MyGdxGame game){
        this.game = game;
        skyAtlas = new TextureAtlas(Gdx.files.internal("Sky.atlas"));
        animation = new Animation(5f/1f,skyAtlas.getRegions());
        //gameport = new FillViewport(100,400,gamecam);
    }
    public void create()
    {
        game.batch = new SpriteBatch();
        skyAtlas = new TextureAtlas("D:\\Java Project\\test\\core\\assets\\Sky.atlas");
        animation = new Animation(1f/10f,skyAtlas.getRegions());
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        game.batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true),0,0);

        game.batch.end();
    }

    @Override
    public void dispose() {
        game.batch.dispose();
        skyAtlas.dispose();
    }
 */