package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Level implements Screen {
    MyGdxGame game;
    private Texture tex,tex1;
    Sound scom,sb;
    MenuScreen menu1;
    Play2ndScreen p2;
    private float clock = 0;
    public Level(MyGdxGame game)
    {
        this.game = game;
        tex = new Texture("Menu//win.png");
        tex1 = new Texture("Menu//back1.png");
        scom = Gdx.audio.newSound(Gdx.files.internal("Sound//Win.mp3"));
        scom.loop(0.5f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        game.batch.begin();
        clock += Gdx.graphics.getDeltaTime();
        if(clock < 2.5)
        {
            game.batch.draw(tex,0,0);
        }
        else
        {
            game.batch.draw(tex1,0,0);
        }
        game.batch.end();
    }
    private void handleInput()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            scom.pause();
            menu1 = new MenuScreen(game);
            game.setScreen(menu1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.R))
        {
            scom.pause();
            p2 = new Play2ndScreen(game);
            game.setScreen(p2);

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
        scom.dispose();
        game.batch.dispose();
    }
}
