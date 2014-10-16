package com.RPGame.GameControl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.RPGame.GameElement.Monster;
import com.RPGame.GameElement.NPC;
import com.RPGame.GameElement.Saber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Practice2 extends GameState{
	 Map<String,Texture> map;
	 Texture EmilyTextures, Emily256, head128, hpexp, scarecroww, brother, test,Delg256;
	 private int count,dc;
	 RPGameDialogue dia;
	 boolean iswalk,ndia,isattack,isattackmon,isplay;
	 NPC delg, emily;
	 Monster scarecrow;
	 int time,ndir,d,msg;
	 String npcname,monstername;
	 Music music,attacksound;
	 
	 
	 public Practice2( MyGame game )
		{
			this.game = game;
			this.ischange = false;
			this.page = 'Q';
			this.count=0;
			this.dia = new RPGameDialogue();
			this.dc = 0;
			this.nx = 0;
			this.ny = 64;
			this.ndir = 0;
			iswalk = false;
			this.sb = new Saber();
			this.ndia = false;
			this.time = 0;
			this.delg = new NPC();
			this.emily = new NPC();
			this.scarecrow=new Monster();
			this.isplay = true;
			
			this.isdia = true;
			
			this.npcname =sb.name;
			
			this.monstername="scarecrow";
			
		}
	 
	protected  void Handle( char GS ) {
		game.ctr.Request(GS);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
       
		batch.begin();
		
		this.drawMap(RPGgameMap.practice2Map,batch,map);
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        this.showlpexp(batch);
		batch.draw(head128,0,704);
		batch.draw(hpexp,128,704);  
		
		if(isplay)
		{
			music.setLooping (true);
			music.setVolume((float) 0.1);
			music.play ();
			isplay = false;
		}
		
		if (iswalk)
		{
			this.move(nx, ny, ndir);
		}
		
		if(isattack)
		{
			sb.attack( ndir, nx,ny,batch);
			if(isattack)
			{
				attacksound.setLooping (false);
				attacksound.setVolume((float) 0.1);
				attacksound.play ();
			}
			
			isattack=false;
		}
	    

		sb.drawState(batch);
		sb.drawBody( msg, batch, count , nx, ny);
		iswalk = false;
		
		/*if(ny>64 && ny<192 && nx>1300)
		{
			ischange = true;
			page = 'T';
		}*/
		
		batch.end();    //batch.end
		
		
		if(ischange){
			Handle(page);	
		}
		
		stage.act(arg0);
		stage.draw();

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		batch=new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	
		music =  Gdx.audio.newMusic(Gdx.files.internal( "assets/adventure.mp3" ));
		attacksound = Gdx.audio.newMusic(Gdx.files.internal( "assets/attacksound.mp3" ));
		
		map = getTextures(RPGgameMap.practiceMap);
		dialogue = new Texture(Gdx.files.internal("assets/data/dia.png"));
		lp = new Texture(Gdx.files.internal("assets/data/blood.png"));
		exp = new Texture(Gdx.files.internal("assets/data/exp.png"));
		head128 = new Texture(Gdx.files.internal("assets/data/head128.png"));
		Emily256 = new Texture(Gdx.files.internal("assets/data/Emily256.png"));
		hpexp = new Texture(Gdx.files.internal("assets/data/hpexp.png"));
		scarecroww = new Texture(Gdx.files.internal("assets/data/scarecroww.png"));
		hurtscarecrow = new Texture(Gdx.files.internal("assets/data/scarecrowr.png"));
		brother = new Texture(Gdx.files.internal("assets/data/b1.png"));
		Delg256 = new Texture(Gdx.files.internal("assets/data/Delg256.png"));
		
		drawDialogue = new BitmapFont( 
				Gdx.files.internal("assets/data/arial_test.fnt"),
				Gdx.files.internal("assets/data/arial_test.png"), false, true);  //³]©w¦r«¬
		
		delg.setDetail("Delg", 1200, 192);
		emily.setDetail("Emily", 64, 640);
		scarecrow.setMonster("scarecrow", 512, 256);
	}

	public void onMessage(int msg) {
		this.msg = msg;


		if(msg==0)
		{
			iswalk = false;
		}
		else if(msg>2&&msg<7)
		{
			ndir = msg;
			iswalk = true;
		}
		
		if(msg==1||msg==2)
		{
			ndia = true;
			this.happenDialogue(nx, ny, emily.showdPositionX(), emily.showPositionY(), ndir);
			this.happenDialogue(nx, ny, delg.showdPositionX(), delg.showPositionY(), ndir);
		}
		
		if(msg==11){
			isattack=true;
		    isattackmon=true;  
		}
	
	
	
	
	}


}
