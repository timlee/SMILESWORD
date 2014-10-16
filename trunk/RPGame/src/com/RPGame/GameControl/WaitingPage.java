package com.RPGame.GameControl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WaitingPage extends GameState{

	BitmapFont drawAccValue;
	Texture showip;
	String[] ips;
	String ip;
	int port;
	
	public WaitingPage ( MyGame g )
	{
		ischange =false;
		this.game = g;
		this.ischange = false;
		this.page = 'W';
		InetAddress[] allByName;
		try {
			allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			ips = new String[allByName.length];
			for (int i = 0; i < allByName.length; i++) {
				ips[i] = allByName[i].getHostAddress();
			}
			this.ip = ips[0];
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.port = g.ctr.remotet.receiver.port;
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
		/*
		 * show screen
		 */
		batch.begin(); 
		batch.draw(background,0,0);
		batch.draw(showip,192,30);;
		drawAccValue.drawMultiLine(batch,ip+":"+port,620,225);
		if ( ischange )
		{
			Handle('B');
		}
		batch.end();
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
		/*
		 * set texture
		 */
		background = new Texture(Gdx.files.internal("assets/data/waitingpage/background.jpg"));
		showip  = new Texture(Gdx.files.internal("assets/data/waitingpage/waitingpage.png"));
		drawAccValue = new BitmapFont(
		Gdx.files.internal("assets/data/arial_test.fnt"),
		Gdx.files.internal("assets/data/arial_test.png"), false, true);
		
	}

	@Override
	public void onMessage(int msg) {
		if (msg==0)
		{
			ischange = true;
		}
		
	}

	@Override
	protected void Handle(char GS) {
		game.ctr.Request(GS);
		
	}

}
