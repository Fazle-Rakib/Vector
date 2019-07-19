package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import javafx.scene.layout.Background;

import javax.swing.*;

public class MenuScreen implements Screen {

    private SpriteBatch batch;
    private MyGdxGame game;
    private OrthographicCamera camera;
    private Viewport gameport;
    private Texture background,front,layer1,layer2;
    Sprite button;
    private PlayScreen play1;
    private Play2ndScreen play2;
    private LevelScreen levscr;
    private Animation animation;
    private TextureAtlas backAtlas;
    private float elapsedTime  = 0;
    private int  sourceX = 0;
    Integer pos = 1;
    Sound sound,bs,es;


    public  MenuScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        batch  = new SpriteBatch();
        sound = Gdx.audio.newSound(Gdx.files.internal("Sound//menu.mp3"));
        bs = Gdx.audio.newSound(Gdx.files.internal("Sound//button.mp3"));
        es = Gdx.audio.newSound(Gdx.files.internal("Sound//enter.mp3"));
        sound.loop();

        background = new Texture("Menu//Background.png");
        button = new Sprite(new Texture("Menu//s1.png"));
        backAtlas = new TextureAtlas(Gdx.files.internal("Menu//Vector.atlas"));
        animation = new Animation(1f/3f,backAtlas.getRegions());

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(1,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        elapsedTime += Gdx.graphics.getDeltaTime();
        sourceX += 1;

        batch.begin();
        batch.draw(background,0,0);
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true),400,260);

        button.draw(batch);
        batch.end();

    }
    private void handleInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            es.play(2.0f);
            if(pos == 1)
            {
                play1 =  new PlayScreen(game);
                sound.pause();
                game.setScreen(play1);
            }
            if(pos == 2)
            {
                levscr = new LevelScreen(game);
                sound.pause();
                game.setScreen((Screen) levscr);
            }
            if(pos == 3)
            {
                //tutorial
            }
            if(pos == 4)
            {
                Gdx.app.exit();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            bs.play(2.0f);
            pos--;
            if(pos < 1)
            {
                pos = 4;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            bs.play(2.0f);
            pos++;
            if(pos > 4)
            {
                pos = 1;
            }
        }
        if(pos == 1)
        {
            button.setPosition(255,255);
        }
        if(pos == 2)
        {
            button.setPosition(255,185);
        }
        if(pos == 3)
        {
            button.setPosition(255,115);
        }
        if(pos == 4)
        {
            button.setPosition(255,45);
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
        batch.dispose();
        backAtlas.dispose();
        sound.dispose();
        bs.dispose();
        es.dispose();
    }
}
