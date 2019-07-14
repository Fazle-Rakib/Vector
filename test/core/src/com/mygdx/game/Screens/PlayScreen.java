package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.ApplicationAdapter;

public class PlayScreen implements Screen{
    private MyGdxGame game;
    private Texture skyTex,roadTex,backTex,exTex,h1Tex,h3Tex;
    private TextureAtlas skyAtlas;
    private float elapsedTime = 0;

    private OrthographicCamera camera;
    private Viewport viewport;
    int sourceX = 0;

    //private Viewport gameport;

    public PlayScreen(MyGdxGame game){
        this.game = game;

        skyTex = new Texture("Bright//Sky.png");
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex = new Texture("Bright//houses3.png");
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backTex = new Texture("Bright//back.png");
        backTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex = new Texture("Bright//houses1.png");
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        exTex = new Texture("Bright//ex1.png");
        exTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex = new Texture("Bright//road&lamps.png");
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);


        //gameport = new FillViewport(100,400,gamecam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        sourceX += 1;
        elapsedTime += Gdx.graphics.getDeltaTime()*100;
        game.batch.draw(skyTex,0,0,sourceX ,0,850,500);
        game.batch.draw(h3Tex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(backTex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(h1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(exTex,0,0,sourceX*2,0,850,500);
        game.batch.draw(roadTex,0,0,(int)(sourceX*2.2),0,850,500);

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
        game.batch.dispose();
        skyAtlas.dispose();
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