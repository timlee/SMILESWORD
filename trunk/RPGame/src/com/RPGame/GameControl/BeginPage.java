package com.RPGame.GameControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BeginPage extends GameState{
	private Texture isstart,isquit;
	private Texture isnstart,isnquit;
	private Texture title,beginblack;
	private int flag;
	boolean isplay,isswi;
	Music swi;
	
	public BeginPage( MyGame g )
	{
		this.game = g;
		this.ischange = false;
		this.page = 'B';
		flag = 0;
		this.isplay = true;
		this.isswi = false;
	}

	@Override
	public void dispose() {
		Gdx.app.exit();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {	
		
		if(isplay)
		{
			music.setLooping (true);
			music.setVolume((float) 0.1);
			music.play ();
			isplay = false;
		}
		/*
		 * show screen
		 */
		
		batch.begin(); 
		batch.draw(beginblack,0,0);
		batch.draw(title,0,0);

		if ( flag == 0 )//on start button
		{
			if(flag == 0)
			{
				swi.setLooping (false);
				swi.setVolume((float) 2);
				swi.play ();
			}
			batch.draw(isstart,485,200);
			batch.draw(isnquit,485,100);
		}
		else if ( flag == 1 )//on continue button
		{
			if(flag ==1)
			{
				swi.setLooping (false);
				swi.setVolume((float) 2);
				swi.play ();
			}
			batch.draw(isnstart,485,200);
			batch.draw(isquit,485,100);
		}
		batch.end(); 
		
		if ( ischange )
		{
			
			music.stop();
			Handle('P');
		}
		
		stage.act(delta);
		stage.draw();
		/*
		 * end show screen
		 */		
	}

	@Override
	public void resize(int arg0, int arg1) {	
	}

	@Override
	public void resume() {	
	}

	@Override
	public void show() {
		batch=new SpriteBatch(); 
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		/*
		 * set texture
		 */
		music =  Gdx.audio.newMusic(Gdx.files.internal( "assets/beginmusic.mp3" ));
		swi = Gdx.audio.newMusic(Gdx.files.internal( "assets/switch.mp3" ));
		
		background = new Texture(Gdx.files.internal("assets/data/beginpage/background.png"));
		isstart = new Texture(Gdx.files.internal("assets/data/beginpage/isstart.png"));
		isquit = new Texture(Gdx.files.internal("assets/data/beginpage/isquit.png"));
		isnstart = new Texture(Gdx.files.internal("assets/data/beginpage/isnstart.png"));
		isnquit = new Texture(Gdx.files.internal("assets/data/beginpage/isnquit.png"));
		beginblack = new Texture(Gdx.files.internal("assets/data/beginpage/beginblack.png"));
		title = new Texture(Gdx.files.internal("assets/data/beginpage/title.png"));
	}

	@Override
	protected void Handle(char GS) {
		game.ctr.Request(GS);
		
	}

	@Override
	public void onMessage(int msg) {
		if ( msg == 3 )
		{
			flag-=1;
			if ( flag < 0 ){ flag = 1; }
		}
		else if ( msg == 6 )
		{
			flag+=1;
			if ( flag > 1 ){ flag = 0; }
		}
		else if ( msg == 1 && flag == 0 )
		{
			ischange = true;
		}
		else if ( msg == 1 && flag == 1 )
		{
			this.dispose();
		}
	}

}
