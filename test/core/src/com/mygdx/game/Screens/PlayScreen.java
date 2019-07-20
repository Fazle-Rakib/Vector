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
    
/*1*/
	private MyGdxGame game;
    private Texture skyTex,roadTex,backTex,exTex,h1Tex,h3Tex,player;
    private Sprite  objBarrol,objTire,objBlock1,objBlock2,objPad1,objPad2
                        ,objStone1,objStone2,objspike01,objPad04,objPad03
                        ,objcutter,objBarrol2;
    private Sprite    objisheat;
    private Sprite test1,test2,animation_test,winTex,liv[];
    private Rectangle rec1,rec2,Player_anime_test_rec,objBarrol_rec,objTire_rec
                         ,objBlock2_rec,objPad1_rec,objPad2_rec,
                        objStone1_rec,objStone2_rec,objspike01_rec,objPad04_rec
                        ,objPad03_rec,objcutter_rec ,objBarrol2_rec,win_rec;

    private TextureAtlas A_Atlas;
    private Animation AnimE_1_A,AnimE_1_R,Player_1_R,Player_1_D,Player_1_J,Player_1_JG;
    private float elapsedTime = 0,clock = 0,clock1=0;
    private OrthographicCamera camera;
    private Viewport viewport;
    int  sourceX = 0;
    float cx,cy;
    int player_x = 50,player_y = 25,incre = 0,life = 4;
    Integer PlayerFreame = 0;
    boolean isover[]=new boolean[20];
    boolean isvanish[]=new boolean[20];
    boolean isheat,isstop;	
	
/****/ Sound sBack,sWin,sHit;
        boolean Hit = false,Death = false,Win = false;
        deathHandle deathScreen;
        MenuScreen Screen1;
        Level l1;

    public PlayScreen(MyGdxGame game){
        this.game = game;
/*2*/		
		for(int i=0;i<20;i++)
        {
            isover[i]=false;

        }
        //**//Texture Declaration
        skyTex = new Texture("Bright//Sky.png");
        h3Tex = new Texture("Bright//houses3.png");
        backTex = new Texture("Bright//back.png");
        h1Tex = new Texture("Bright//houses1.png");
        exTex = new Texture("Bright//ex1.png");
        roadTex = new Texture("Bright//road&lamps.png");
        winTex = new Sprite(new Texture("Menu//Lev.png"));
        liv = new Sprite[5];
        liv[1] = new Sprite(new Texture("Menu//U1.png"));
        liv[2] = new Sprite(new Texture("Menu//U.png"));
        liv[3] = new Sprite(new Texture("Menu//U2.png"));
        liv[4] = new Sprite(new Texture("Menu//U2.png"));
        //**//Sprite Declaration
        objBarrol = new Sprite(new Texture("Object//Barrel_1.png"));
        objBarrol2 = new Sprite(new Texture("Object//Barrel_2.png"));
        objTire = new Sprite(new Texture("Object//3Tire.png"));
        animation_test=new Sprite(new Texture("Player_collision_test.png"));
        objBlock1 =new Sprite( new Texture("Object//Block.png"));
        objBlock2 = new Sprite(new Texture("Object//Block03.png"));
        objPad1 = new Sprite(new Texture("Object//Pad01.png"));
        objPad2 = new Sprite(new Texture("Object//Pad02.png"));
        objPad03= new Sprite(new Texture("Object//Pad03.png"));
        objPad04= new Sprite(new Texture("Object//Pad04.png"));
        objStone1 = new Sprite(new Texture("Object//Stone01.png"));
        objspike01 = new Sprite(new Texture("Object//Spikes1.png"));
        objcutter=new Sprite(new Texture("Object//cut1.png"));

        objisheat=new Sprite(new Texture("Object//isheat.png"));
        ////Atlas Decl
        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_A//Enemy_1_A.atlas"));
        AnimE_1_A = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("Enemy_1_R//Enemy_1_R.atlas"));
        AnimE_1_R = new Animation(5/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerRun//Player_R.atlas"));
        Player_1_R = new Animation(3/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerDead//Player_D.atlas"));
        Player_1_D = new Animation(10/1f,A_Atlas.getRegions());
        A_Atlas = new TextureAtlas(Gdx.files.internal("PlayerJump//Player_J.atlas"));
        Player_1_J = new Animation(3/1f,A_Atlas.getRegions());
        Player_1_J.setFrameDuration(15f);
        A_Atlas = new TextureAtlas(Gdx.files.internal("JumpGlide//NewJump.atlas"));
        Player_1_JG = new Animation(15/1f,A_Atlas.getRegions());

        ////Texture wrapping/looping
        skyTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h3Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        h1Tex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        exTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        roadTex.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

       // objBarrol_rec = new Rectangle(objBarrol.getX(),objBarrol.getY(),objBarrol.getWidth(),objBarrol.getHeight());
       // Player_anime_test_rec = new Rectangle(animation_test.getX(),animation_test.getY(),animation_test.getWidth()
                                               // ,animation_test.getHeight());
		
		
/*****/
        sBack = Gdx.audio.newSound(Gdx.files.internal("Sound//play.mp3"));
        sHit = Gdx.audio.newSound(Gdx.files.internal("Sound//hit.mp3"));
        sWin = Gdx.audio.newSound(Gdx.files.internal("Sound//Win.mp3"));
        sBack.loop(0.3f);

        //camera setup
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);
    }

    @Override
    public void show() {

    }
/*3*/
	//**// pseudo_player
    private int player_rec_x=player_x;
    private int player_rec_y=player_y;
    boolean alive = true;
	
    @Override
    public void render(float delta) {
        handleInput();
        game.batch.begin();

        if(Win)
        {
            //handleWin();
            sBack.pause();
            l1 = new Level(game);
            game.setScreen(l1);
        }
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
		

        sourceX += 1;
		
		//**//psuedo rec_decl

        objBarrol_rec=objBarrol.getBoundingRectangle();
        objBarrol2_rec=objBarrol2.getBoundingRectangle();
        objTire_rec=objTire.getBoundingRectangle();
        objBlock2_rec=objBlock2.getBoundingRectangle();
        objPad1_rec=objPad1.getBoundingRectangle();
        objPad2_rec=objPad2.getBoundingRectangle();
        objPad03_rec=objPad03.getBoundingRectangle();
        objPad04_rec=objPad04.getBoundingRectangle();
        objspike01_rec=objspike01.getBoundingRectangle();
        objcutter_rec=objcutter.getBoundingRectangle();
        objStone1_rec=objStone1.getBoundingRectangle();
        win_rec = winTex.getBoundingRectangle();

        Player_anime_test_rec=animation_test.getBoundingRectangle();
		
		
        cx  = camera.position.x = elapsedTime += Gdx.graphics.getDeltaTime()*100;
      
/*4*/

		//**//backgroun draw

        game.batch.draw(skyTex,0,0,sourceX ,0,850,500);
        game.batch.draw(h3Tex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(backTex,0,0,(int)(sourceX*1.5),0,850,500);
        game.batch.draw(h1Tex,0,0,sourceX*2,0,850,500);
        game.batch.draw(exTex,0,0,sourceX*2,0,850,500);
        game.batch.draw(roadTex,0,0,(int)(sourceX*3.5),0,850,500);

        liv[life].setPosition(600,400);
        liv[life].draw(game.batch);

        //**//enenmy draww

        //game.batch.draw((TextureRegion) AnimE_1_A.getKeyFrame(elapsedTime,true ),0,25);
        //game.batch.draw((TextureRegion) AnimE_1_R.getKeyFrame(elapsedTime,true ),0,10);

        //**//player animation

        //**//pseudo player rec setposition
        animation_test.setPosition((float) player_rec_x,player_rec_y);
        //animation_test.draw(game.batch);


        //**//COllision
        isover[0] = Player_anime_test_rec.overlaps(objBarrol_rec);
         isover[2] = Player_anime_test_rec.overlaps(objBarrol2_rec);
        isover[1] = Player_anime_test_rec.overlaps(objTire_rec);
        isover[3] = Player_anime_test_rec.overlaps(objStone1_rec);
        isover[5] = Player_anime_test_rec.overlaps(objPad1_rec);
        isover[6] = Player_anime_test_rec.overlaps(objPad04_rec);
        isover[7] = Player_anime_test_rec.overlaps(objBlock2_rec);
        isover[11] = Player_anime_test_rec.overlaps(objPad03_rec);
        isover[12] = Player_anime_test_rec.overlaps(objPad2_rec);
        isover[13] = Player_anime_test_rec.overlaps(objcutter_rec);
        isover[14] = Player_anime_test_rec.overlaps(objspike01_rec);
        isover[15] =  Player_anime_test_rec.overlaps(win_rec);
        boolean isenter=false;

        for(int i=0;i<10;i++)
        {

            if(isover[i]){
                //System.out.println("isover["+i+"]");
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
        if(elapsedTime < 190 )
        {
            for(int i=0;i<20;i++)
            {
                isvanish[i]=false;
                isover[i]=false;
            }
            isstop=false;
        }
        for(int i=11;i<14;i++)
        {
            if(isover[i])
            {
                //System.out.println("mmmmmmmmmmmmmmmorche dmjdkfs");
                System.out.println("isover["+i+"]");
				Death = true;
				break;
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
			life--;
			if(life == 0)
            {
                Death = true;
            }
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
                if(!isstop && !Death)
                {
                        game.batch.draw((TextureRegion) Player_1_J.getKeyFrame(clock,true ),(int)(player_x+incre*1.2),player_y);

                        //**//pseudo player animation

                        if(clock>=20 && clock<=35){
                            player_rec_y= 50;
                        }
                        else if(clock >=36 && clock <= 100)
                        {
                            player_rec_y=150;
                        }
                        else if(clock>=101 && clock< 130)
                        {
                            player_rec_y=160;
                        }
                        else
                        {
                            player_rec_y=25;
                        }
                        if(clock>=80 && clock< 130)
                        {
                            player_rec_x=140;
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
        if(Death)
        {
            sBack.pause();
            //clock += Gdx.graphics.getDeltaTime();
            while(PlayerFreame == 1)
            {
                clock+=Gdx.graphics.getDeltaTime();
                //game.batch.draw((TextureRegion) Player_1_J.getKeyFrame(elapsedTime,true ),player_x,player_y);
                if(clock < 130)
                {
                    objisheat.setPosition((float) player_rec_x,(float) (player_rec_y-0.05*clock));
                }
                objisheat.draw(game.batch);
                if(clock >= 130)
                {
                    PlayerFreame = 0;
                    clock = 0;
                }
            }
            sHit.play();
            deathScreen = new deathHandle(game,1);
            game.setScreen(deathScreen);
        }
/****/		
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
		
/*5*/	
		 //**// obstacle draw
        if(!isvanish[0]){
            objBarrol.setPosition((cx-700)*-3,25);
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
        if(!isvanish[1]){
            objTire.setPosition((cx-1250)*-3,-20);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[1]=false;
                isstop=false;
                clock1=0;
            }
            objTire.setPosition((cx-1000)*-3,-325);
        }
        objTire.draw(game.batch);
        if(!isvanish[5]){
            objPad1.setPosition((cx-1700)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[5]=false;
                isstop=false;
                clock1=0;
            }
            objPad1.setPosition((cx-2000)*-3,-325);
        }
        objPad1.draw(game.batch);
        objPad2.setPosition((cx-2200)*-3,25);
        objPad2.draw(game.batch);
        objPad03.setPosition((cx-2750)*-3,25);
        objPad03.draw(game.batch);
        if(!isvanish[7]){
            objBlock2.setPosition((cx-3250)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[7]=false;
                isstop=false;
                clock1=0;
            }
            objBlock2.setPosition((cx-3700)*-3,-325);
        }
        objBlock2.draw(game.batch);
        if(!isvanish[3]){
            objStone1.setPosition((cx-4300)*-3,15);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[3]=false;
                isstop=false;
                clock1=0;
            }
            objStone1.setPosition((cx-2000)*-3,-325);
        }
        objStone1.draw(game.batch);
        if(!isvanish[6]){
            objPad04.setPosition((cx-4860)*-3,25);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[6]=false;
                isstop=false;
                clock1=0;
            }
            objPad04.setPosition((cx-2000)*-3,-325);
        }
        objPad04.draw(game.batch);
        objcutter.setPosition((cx-5490)*-3,25);
        objcutter.draw(game.batch);
        objspike01.setPosition((cx-6000)*-3,25);
        objspike01.draw(game.batch);
        if(!isvanish[2]){
            objBarrol2.setPosition((cx-6700)*-3,10);
        }
        else{
            clock1 += Gdx.graphics.getDeltaTime()*100;
            if(clock1<110)
                objisheat.draw(game.batch);
            else{
                isvanish[2]=false;
                isstop=false;
                clock1=0;
            }
            objBarrol2.setPosition((cx-100)*-3,-125);
        }
        objBarrol2.draw(game.batch);
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