package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class LevelScreen implements Screen {
    private MyGdxGame game;
    private PlayScreen play1;
    private Play2ndScreen play2;
    private MenuScreen menuScreen;
    private SpriteBatch batch;
    private Texture back,p1,p2;
    Sound sound,bs,es;

    Sprite s1;
    Integer pos1 = 1;
    public LevelScreen(MyGdxGame game)
    {
        this.game = game;
        batch = new SpriteBatch();
        sound = Gdx.audio.newSound(Gdx.files.internal("Sound//menu.mp3"));
        bs = Gdx.audio.newSound(Gdx.files.internal("Sound//button.mp3"));
        es = Gdx.audio.newSound(Gdx.files.internal("Sound//enter.mp3"));
        sound.loop();

        back = new Texture("Menu//back.png");
        p1 = new Texture("Menu//lev1.png");
        p2 = new Texture("Menu//lev2.png");
        s1 = new Sprite(new Texture("Menu//s1.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(back,0,0);
        batch.draw(p1,100,300);
        batch.draw(p2,100,100);
        s1.draw(batch);
        batch.end();
        handleInput();
    }
    private void handleInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            es.play(2.0f);
            if (pos1 == 1) {
                play1 = new PlayScreen(game);
                sound.pause();
                game.setScreen(play1);
            }
            if (pos1 == 2) {
                play2 = new Play2ndScreen(game);
                sound.pause();
                game.setScreen(play2);
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            bs.play(2.0f);
            pos1--;
            if(pos1 < 1)
            {
                pos1 = 2;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            bs.play(2.0f);
            pos1++;
            if(pos1 > 2)
            {
                pos1 = 1;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            sound.pause();
            menuScreen = new MenuScreen(game);
            game.setScreen(menuScreen);
        }
        if(pos1 == 1)
        {
            s1.setPosition(10,340);
        }
        if(pos1 == 2)
        {
            s1.setPosition(10,140);
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
        sound.dispose();
        bs.dispose();
        es.dispose();
    }
}
