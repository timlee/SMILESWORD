package com.RPGame.GameControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class tbc extends GameState{
	Texture tbc;

	public tbc( MyGame game )
	{
		this.page = 'T';
	}
	
	@Override
	public void dispose() {
		
		
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
		batch.draw(tbc,0,0);
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
		
		tbc = new Texture(Gdx.files.internal("assets/data/tbc.png"));	
		
	}

	@Override
	protected void Handle(char GS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(int msg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
