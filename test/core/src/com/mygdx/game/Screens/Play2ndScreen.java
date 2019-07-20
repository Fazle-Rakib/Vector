package com.mygdx.game.Screens;

import  com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class Play2ndScreen implements Screen {
    private MyGdxGame game;
    private Texture skyTex,roadTex,h2Tex,ex1Tex,ex2Tex,h1Tex,h3Tex;
    private Sprite objCut1,objCut2,objCut3,objSpkie1,objSpkie2,objSpkie3
            ,objSpkie4,objBarrol,objStone1,objStone2,objPad1,objPad2,objPad3
            ,objBlock1,objBlock2;
    private  Sprite collision_test,objisheat,winTex;
    private Rectangle objCut1_rec;
    private Rectangle objCut2_rec;
    private Rectangle objCut3_rec;
    private Rectangle objSpkie1_rec;
    private Rectangle objSpkie2_rec;
    private Rectangle objSpkie3_rec;
    private Rectangle objSpkie4_rec;
    private Rectangle objBarrol_rec;
    private Rectangle objStone1_rec;
    private Rectangle objStone2_rec;
    private Rectangle objPad1_rec;
    private Rectangle objPad2_rec;
    private Rectangle objPad3_rec;
    private Rectangle objBlock1_rec;
    private Rectangle objBlock2_rec;
    private Rectangle colision_test_rec;
    private TextureAtlas Enemy_1_A_Atlas,A_Atlas;
    private Animation AnimE_1_A,AnimE_1_R,Player_1_R,Player_1_D,Player_1_J,Player_1_JG;
    private float elapsedTime = 0,clock = 0,clock1=0;


    private OrthographicCamera camera;
    private Viewport viewport;
    private int  sourceX = 0;
    float cx,cy;
    private int player_x = 50,player_y = 25,incre = 0;
    Integer PlayerFreame = 0;
    private boolean isover[]=new boolean[30];
    private    boolean isvanish[]=new boolean[30];
    private     boolean isheat,isstop;

    Sound sBack,sWin,sHit;
    boolean Hit = false,Death = false,Win = false;
    deathHandle deathScreen;
    MenuScreen Screen1;
    Level l1;

    public Play2ndScreen(MyGdxGame game)
    {
        this.game = game;
        for(int i=0;i<20;i++)
        {
            isover[i]=false;

        }
        //Texture Declaration
        skyTex = new Texture("Bright2//Sky.png");
        h3Tex = new Texture("Bright2//houses.png");
        h2Tex = new Texture("Bright2//houses2.png");
        h1Tex = new Texture("Bright2//houses1.png");
        ex2Tex = new Texture("Bright2//fountain.png");
        ex1Tex = new Texture("Bright2//umbrella.png");
        roadTex = new Texture("Bright2//road.png");
        winTex = new Sprite(new Texture("Menu//Lev.png"));

        //Texture wrapping/looping
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h2Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ex2Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ex1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        collision_test=new Sprite(new Texture("Player_collision_test.png"));
        objisheat=new Sprite(new Texture("Object//isheat.png"));

        objBarrol = new Sprite(new Texture("Object01//Barrel_1.png"));
        objCut1 = new Sprite(new Texture("Object01//Cut1.png"));
        objCut2 =new Sprite(new Texture("Object01//Cut2.png"));
        objCut3 = new Sprite(new Texture("Object01//Cut3.png"));
        objSpkie1 = new Sprite(new Texture("Object01//Spikes1.png"));
        objSpkie2 = new Sprite(new Texture("Object01//Spikes2.png"));
        objSpkie3 = new Sprite(new Texture("Object01//Spikes3.png"));
        objSpkie4 = new Sprite(new Texture("Object01//Spikes4.png"));
        objStone1 =  new Sprite(new Texture("Object01//Stone01.png"));
        objStone2 = new Sprite( new Texture("Object01//Stone02.png"));
        objPad1 = new Sprite(new Texture("Object01//Pad02.png"));
        objPad2 = new Sprite(new Texture("Object01//Pad01.png"));
        objPad3 = new Sprite(new Texture("Object01//Pad03.png"));
        objBlock1 = new Sprite(new Texture("Object01//Block03.png"));

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
        A_Atlas = new TextureAtlas(Gdx.files.internal("JumpGlide//NewJump.atlas"));
        Player_1_JG = new Animation(15/1f,A_Atlas.getRegions());
        //camera setup


        sBack = Gdx.audio.newSound(Gdx.files.internal("Sound//play.mp3"));
        sHit = Gdx.audio.newSound(Gdx.files.internal("Sound//hit.mp3"));
        sWin = Gdx.audio.newSound(Gdx.files.internal("Sound//Win.mp3"));
        sBack.loop(0.3f);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
    }

    int player_rec_x=player_x;
    int player_rec_y=player_y;

    @Override
    public void render(float delta) {
        handleInput();
        if(Death)
        {
            sBack.pause();
            deathScreen = new deathHandle(game,1);
            game.setScreen(deathScreen);
        }
        if(Win)
        {
            //handleWin();
            sBack.pause();
            l1 = new Level(game);
            game.setScreen(l1);
        }
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

        //**//pseudo player rec setposition
        collision_test.setPosition((float) player_rec_x,player_rec_y);
        //collision_test.draw(game.batch);

        colision_test_rec=collision_test.getBoundingRectangle();

        objBarrol_rec=objBarrol.getBoundingRectangle();
        objBlock1_rec=objBlock1.getBoundingRectangle();
        objPad1_rec=objPad1.getBoundingRectangle();
        objPad2_rec=objPad2.getBoundingRectangle();
        objPad3_rec=objPad3.getBoundingRectangle();
        objCut1_rec=objCut1.getBoundingRectangle();
        objCut2_rec=objCut2.getBoundingRectangle();
        objCut3_rec=objCut3.getBoundingRectangle();
        objSpkie1_rec=objSpkie1.getBoundingRectangle();
        objSpkie2_rec=objSpkie2.getBoundingRectangle();
        objSpkie3_rec=objSpkie3.getBoundingRectangle();
        objSpkie4_rec=objSpkie4.getBoundingRectangle();
        objStone1_rec=objStone1.getBoundingRectangle();
        objStone2_rec=objStone2.getBoundingRectangle();

        //**c//collison
        isover[0] = colision_test_rec.overlaps(objBarrol_rec);
        isover[1]=colision_test_rec.overlaps(objStone1_rec);
        isover[5] = colision_test_rec.overlaps(objPad1_rec);
        isover[8] = colision_test_rec.overlaps(objBlock1_rec);
        isover[4] = colision_test_rec.overlaps(objPad2_rec);

        isover[19] = colision_test_rec.overlaps(objPad3_rec);
        isover[13] = colision_test_rec.overlaps(objCut1_rec);
        isover[14] = colision_test_rec.overlaps(objCut2_rec);
        isover[15] = colision_test_rec.overlaps(objCut3_rec);
        isover[17] = colision_test_rec.overlaps(objSpkie1_rec);
        isover[11] = colision_test_rec.overlaps(objSpkie2_rec);
        isover[12] = colision_test_rec.overlaps(objSpkie3_rec);
       //  isover[16] = colision_test_rec.overlaps(objSpkie4_rec);
        isover[18]=colision_test_rec.overlaps(objStone2_rec);
        boolean isenter=false;

        for(int i=0;i<10;i++)
        {

            if(isover[i]){
                System.out.println("isover["+i+"]");
                objisheat.setPosition(player_rec_x-10,player_rec_y);
                isheat=true;
                isenter=true;
                isvanish[i]=true;
            }
            else
            {
                if(!isenter)
                    isheat=false;
            }


        }
        if(elapsedTime <160 )
        {
            for(int i=0;i<20;i++)
            {
                isvanish[i]=false;
                isover[i]=false;
            }
            isstop=false;
        }
        for(int i=11;i<20;i++)
        {
            if(isover[i])
            {
                //System.out.println("mor["+i+"]");
                Death = true;
                break;
               // System.out.println("mmmmmmmmmmmmmmmorche dmjdkfs");
            }
        }
        for(int i=0;i<20;i++)
        {
            if(isvanish[i])
            {
                isstop=true;
                break;
            }
        }
        if(isheat) {
            sHit.play();
            objisheat.draw(game.batch);
        }
        else{

            if(PlayerFreame == 0 && clock == 0 && !isstop)
            {
                game.batch.draw((TextureRegion) Player_1_R.getKeyFrame(elapsedTime,true ),player_x,player_y);
            }

            if(PlayerFreame == 1)
            {
                clock += Gdx.graphics.getDeltaTime()*100;
                if(!isstop)
                {
                    game.batch.draw((TextureRegion) Player_1_J.getKeyFrame(clock,true ),(int)(player_x+incre*1.2),player_y);

                    //**//pseudo player animation

                    if(clock>=20 && clock<=100){
                        player_rec_y=150;
                    }
                    else if(clock>=101 && clock<=120){
                        player_rec_y=125;
                    }
                    else if(clock>=2 && clock<=19){
                        player_rec_y=50;
                    }
                    else
                    {
                        player_rec_y=25;
                    }
                    if(clock>=80 && clock<=100)
                    {
                        player_rec_x=130;
                    }
                    else if(clock>=101 && clock<=150){
                        player_rec_x=155;
                    }
                    else
                        player_rec_x=50;
                    incre++;
                }
                while(clock >= 130)
                {
                    camera.position.x +=incre;
                    PlayerFreame = 0;
                    clock = 0;
                    incre = 0;
                    player_rec_x = 50;
                    player_rec_y = 25;
                }
            }
        }
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

        if(!isvanish[0]){
            objBarrol.setPosition((cx-550)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<115)
                objisheat.draw(game.batch);
            else{
                isvanish[0]=false;
                isstop=false;
                clock1=0;
            }
            objBarrol.setPosition((cx-100)*-3,-125);
        }
        objBarrol.draw(game.batch);
        if(!isvanish[5]){
            objPad1.setPosition((cx-850)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<115)
                objisheat.draw(game.batch);
            else{
                isvanish[5]=false;
                isstop=false;
                clock1=0;
            }
            objPad1.setPosition((cx-100)*-3,-125);
        }
        objPad1.draw(game.batch);
        objSpkie3.setPosition((float) ((cx - 1250)*-3),25);
        objSpkie3.draw(game.batch);
       // objPad2.setPosition((float) ((cx - 1650)*-3),25);
        if(!isvanish[4]){
            objPad2.setPosition((cx-1650)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<115)
                objisheat.draw(game.batch);
            else{
                isvanish[4]=false;
                isstop=false;
                clock1=0;
            }
            objPad2.setPosition((cx-100)*-3,-125);
        }
        objPad2.draw(game.batch);
        objSpkie1.setPosition((float) ((cx - 2150)*-3),25);
        objSpkie1.draw(game.batch);
        //objBarrol.setPosition((float) ((cx - 2500)*-3),25);
        //objBarrol.draw(game.batch);
        objPad3.setPosition((float) ((cx - 2900)*-3),25);
        objPad3.draw(game.batch);

       // objBlock1.setPosition((float) ((cx - 3650)*-3),25);
        if(!isvanish[8]){
            objBlock1.setPosition((cx-3450)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<115)
                objisheat.draw(game.batch);
            else{
                isvanish[8]=false;
                isstop=false;
                clock1=0;
            }
            objBlock1.setPosition((cx-100)*-3,-125);
        }
        objBlock1.draw(game.batch);
        objSpkie2.setPosition((float) ((cx - 3800)*-3),10);
        objSpkie2.draw(game.batch);
        //objPad1.setPosition((float) ((cx - 4350)*-3),25);
       // objPad1.draw(game.batch);

        if(!isvanish[1]){
            objStone1.setPosition((cx-4350)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<115)
                objisheat.draw(game.batch);
            else{
                isvanish[1]=false;
                isstop=false;
                clock1=0;
            }
            objStone1.setPosition((cx-100)*-3,-125);
        }
        objStone1.draw(game.batch);
        objCut1.setPosition((float) ((cx - 5000)*-3),25);
        objCut1.draw(game.batch);
        objCut3.setPosition((float) ((cx - 5415)*-3),25);
        objCut3.draw(game.batch);
        objStone2.setPosition((float) ((cx - 5900)*-3),10);
        objStone2.draw(game.batch);
        objCut2.setPosition((float) ((cx - 6550)*-3),25);
        objCut2.draw(game.batch);
        winTex.setPosition((cx-7000)*-3,25);
        winTex.draw(game.batch);
        if(isover[15] == true)
        {
            Win = true;
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
    public void show() {

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
