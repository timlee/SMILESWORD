package com.RPGame.GameControl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenu extends GameState{
	private Texture isbattle,isshop,istrain;
	private Texture isnbattle,isnshop,isntrain;
	private Texture battle,shop,train;
	private int flag = 0;
	
	public MainMenu ( MyGame game )
	{
		this.game = game;
		this.ischange = false;
		this.page = 'M';
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
	public void render(float delta) {
		/*
		 * show screen
		 */
		
		batch.begin(); 
		batch.draw(background,0,0); 

		if ( flag == 0 )//on battle button
		{
			batch.draw(battle,384,0);
			batch.draw(isbattle,304,121);
			batch.draw(isnshop,-30,40);
			batch.draw(isntrain,630,30);
		}
		else if ( flag == 1 )//on shop button
		{
			batch.draw(shop,384,0);
			batch.draw(isnbattle,304,121);
			batch.draw(isshop,-30,40);
			batch.draw(isntrain,630,30);
		}
		else//on train button
		{
			batch.draw(train,384,0);
			batch.draw(isnbattle,304,121);
			batch.draw(isnshop,-30,40);
			batch.draw(istrain,630,30);
		}
		batch.end(); 
		
		if ( ischange )
		{
			Handle(page);
		}
		
		stage.act(delta);
		stage.draw();
		/*
		 * end show screen
		 */		
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
		// TODO Auto-generated method stub
		batch=new SpriteBatch(); 
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		/*
		 * set texture
		 */
		background = new Texture(Gdx.files.internal("assets/data/mainmenu/background.png"));
		isbattle = new Texture(Gdx.files.internal("assets/data/mainmenu/isbattle.png"));
		isshop = new Texture(Gdx.files.internal("assets/data/mainmenu/isshop.png"));
		istrain = new Texture(Gdx.files.internal("assets/data/mainmenu/istrain.png"));
		isnbattle = new Texture(Gdx.files.internal("assets/data/mainmenu/isnbattle.png"));
		isnshop = new Texture(Gdx.files.internal("assets/data/mainmenu/isnshop.png"));
		isntrain = new Texture(Gdx.files.internal("assets/data/mainmenu/isntrain.png"));
		battle = new Texture(Gdx.files.internal("assets/data/mainmenu/battle.png"));
		shop = new Texture(Gdx.files.internal("assets/data/mainmenu/shop.png"));
		train = new Texture(Gdx.files.internal("assets/data/mainmenu/train.png"));
	}

	@Override
	public void onMessage(int msg) {
		/*
		 * values[0] = orientation. 0 = up , 1 = down , 2 = left , 3 = right.
		 * values[1] = on button click. 0 = unclick , 1 = isclick.
		 */
		/*String[] values = msg.split(" ");
		System.out.println("MainMenu call onMsg :"+msg);
		switch ( flag )
		{
			case 0://now is battle
				if ( Integer.parseInt(values[0]) == 1 )
				{
					ischange = true;
					page = 'F';
				}
				else if ( Integer.parseInt(values[1]) > 0 )
					flag = 1;//to shop		
				else if ( Integer.parseInt(values[1]) < 0 )
					flag = 2;//to train
			break;
			
			case 1://now is shop
				if ( Integer.parseInt(values[0]) == 1 )
				{
					//Handle('S');
				}
				else if ( Integer.parseInt(values[2]) > 0 )
					flag = 0;//to battle		
				else if ( Integer.parseInt(values[1]) < 0 )
					flag = 2;//to train
			break;
			
			case 2://now is train
				if ( Integer.parseInt(values[0]) == 1 )
				{
					//Handle('T');
				}
				else if ( Integer.parseInt(values[2]) > 0 )
					flag = 0;//to battle		
				else if ( Integer.parseInt(values[1]) < 0 )
					flag = 1;//to train
			break;
			
		}*/
		
	}
}
