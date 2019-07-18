package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class Play2ndScreen implements Screen {
    private MyGdxGame game;
    private Texture skyTex,roadTex,h2Tex,ex1Tex,ex2Tex,h1Tex,h3Tex,
            objCut1,objCut2,objCut3,objSpkie1,objSpkie2,objSpkie3
            ,objSpkie4,objBarrol,objStone1,objStone2,objPad1,objPad2,objPad3,objBlock1,objBlock2;
    private TextureAtlas Enemy_1_A_Atlas;
    private Animation AnimE_1_A,AnimE_1_R;
    private float elapsedTime = 0;

    private OrthographicCamera camera;
    private Viewport viewport;
    private int  sourceX = 0;
    float cx,cy;

    public Play2ndScreen(MyGdxGame game)
    {
        this.game = game;

        //Texture Declaration
        skyTex = new Texture("Bright2//Sky.png");
        h3Tex = new Texture("Bright2//houses.png");
        h2Tex = new Texture("Bright2//houses2.png");
        h1Tex = new Texture("Bright2//houses1.png");
        ex2Tex = new Texture("Bright2//fountain.png");
        ex1Tex = new Texture("Bright2//umbrella.png");
        roadTex = new Texture("Bright2//road.png");

        //Texture wrapping/looping
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h2Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ex2Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ex1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        objBarrol = new Texture("Object01//Barrel_1.png");
        objCut1 = new Texture("Object01//Cut1.png");
        objCut2 = new Texture("Object01//Cut2.png");
        objCut3 = new Texture("Object01//Cut3.png");
        objSpkie1 = new Texture("Object01//Spikes1.png");
        objSpkie2 = new Texture("Object01//Spikes2.png");
        objSpkie3 = new Texture("Object01//Spikes3.png");
        objSpkie4 = new Texture("Object01//Spikes4.png");
        objStone1 =  new Texture("Object01//Stone01.png");
        objStone2 =  new Texture("Object01//Stone02.png");
        objPad1 = new Texture("Object01//Pad02.png");
        objPad2 = new Texture("Object01//Pad01.png");
        objPad3 = new Texture("Object01//Pad03.png");
        objBlock1 = new Texture("Object01//Block03.png");

        //camera setup
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.begin();
        sourceX += 1;

        cx = camera.position.x = elapsedTime += Gdx.graphics.getDeltaTime()*100;
        game.batch.draw(skyTex,0,0,sourceX ,0,850,500);
        game.batch.draw(h3Tex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(h2Tex,0,0,(int)(sourceX*1.7),0,850,500);
        game.batch.draw(h1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(ex2Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(ex1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(roadTex,0,0,(int)(sourceX*2.2),0,850,500);

        game.batch.draw(objBarrol,(int)((cx - 550)*-1.5),25);
        game.batch.draw(objPad1,(int)((cx-850)*-1.5),25);
        game.batch.draw(objSpkie3,(int)((cx-1250)*-1.5),25);
        game.batch.draw(objPad2,(int)((cx-1650)*-1.5),25);
        game.batch.draw(objSpkie1,(int)((cx-2150)*-1.5),25);
        game.batch.draw(objBarrol,(int)((cx - 2500)*-1.5),25);
        game.batch.draw(objPad3,(int)((cx-3200)*-1.5),25);
        game.batch.draw(objBlock1,(int)((cx-3650)*-1.5),25);
        game.batch.draw(objSpkie2,(int)((cx-3800)*-1.5),5);
        game.batch.draw(objPad1,(int)((cx-4350)*-1.5),25);
        game.batch.draw(objStone1,(int)((cx-4800)*-1.5),25);
        game.batch.draw(objCut1,(int)((cx-5000)*-1.5),25);
        game.batch.draw(objCut3,(int)((cx-5015)*-1.5),25);
        game.batch.draw(objStone2,(int)((cx-5700)*-1.5),25);
        game.batch.draw(objBlock1,(int)((cx-6350)*-1.5),25);
        game.batch.draw(objCut2,(int)((cx-6550)*-1.5),25);



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
    }
}
