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

public class Practice extends GameState{
	 Map<String,Texture> map;
	 Texture EmilyTextures, Emily256, head128, hpexp, scarecroww, brother, test,Delg256;
	 private int count,dc;
	 RPGameDialogue dia;
	 Hashtable<String,ArrayList<String>> song;
	 boolean iswalk,ndia,isattack,isattackmon,isplay;
	 NPC delg, emily;
	 Monster scarecrow;
	 int time,ndir,d,msg;
	 String npcname,monstername;
	 Music music,attacksound;
	 
	 
	 public Practice( MyGame game )
		{
			this.game = game;
			this.ischange = false;
			this.page = 'P';
			this.count=0;
			this.dia = new RPGameDialogue();
			this.song = dia.getDialogue();
			this.dc = 0;
			this.nx = 64;
			this.ny = 640;
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
		
		this.drawMap(RPGgameMap.practiceMap,batch,map);
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        this.showlpexp(batch);
		batch.draw(head128,0,704);
		batch.draw(hpexp,128,704);  
		batch.draw(brother, delg.showdPositionX(), delg.showPositionY());
		scarecrow.drawNPC(batch, scarecroww);
		
		if(isplay)
		{
			music.setLooping (true);
			music.setVolume((float) 0.1);
			music.play ();
			isplay = false;
		}
		
		
		if(isdia)
		{
			if(npcname=="Emily")
			{
				head = Emily256;
			}
			else if (npcname == "Delg")
			{
				head = Delg256;
			}
			
			
			this.showDialogue(song.get(npcname).get(dc), head);
			
		}
		
		if(ndia)
		{
			if(dc+1 == song.get(npcname).size())
			{
				isdia = false;
				dc=0;
				npcname = delg.showName();
			}
			
			if(song.get(npcname).size()>dc+1) {dc+=1;}
			ndia = false;
			
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
	    
		
		
		if(isattackmon)
	    {
			if(scarecrow.lifepoint>0){
				this.happenattack(nx, ny,scarecrow.showdPositionX(),scarecrow.showPositionY(), ndir, batch);
				scarecrow.lifepoint = scarecrow.showlifepoint() - 1;
				isattackmon=false;
			}
	    }
		
		
		sb.drawState(batch);
		sb.drawBody( msg, batch, count , nx, ny);
		iswalk = false;
		
		if(ny>64 && ny<192 && nx>1300)
		{
			ischange = true;
			page = 'Q';
		}
		
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
