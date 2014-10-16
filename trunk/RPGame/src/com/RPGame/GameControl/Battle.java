package com.RPGame.GameControl;

import com.RPGame.GameElement.Monster;
import com.RPGame.GameElement.Saber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Battle extends GameState{
	private Texture monster_t;
	private Texture slice;
	private BitmapFont drawAccValue;
	private boolean isSlice = false;
	private boolean isatted = false;
	private int cooldown;
	private int cooldown2;
	private int timer;
	private Saber saber;
	private Monster monster;
	private String heroinfo;
	private String monsterinfo;
	
	public Battle(MyGame game) {
		this.game = game;
		this.ischange = false;
		this.page = 'F';
		isSlice = false;
		cooldown = 0;
		timer = 0;
		saber = new Saber();
		monster = new Monster();
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
		batch.begin(); 
		batch.draw(background,0,0); 
		//batch.draw(background_r,512,0); 
		batch.draw(monster_t,230,0);  
		/*
		 * Hero attack
		 */
		if ( isSlice )
		{
			batch.draw(slice,350,150); 
			cooldown++;
			if ( cooldown == 50)//reset
			{
				cooldown = 0;
				isSlice = false;
			}
		}
		
		/*
		 * monster attack
		 */
		if ( timer % 180 == 0 )
		{
			//drawAccValue.drawMultiLine(batch,"Duck attack you!",380,500);
			saber.adjLifepoint(saber.defepoint-monster.atkpoint);
			isatted = true;
		}
		
		if ( isatted )
		{
			cooldown2++;
		}
		
		if ( cooldown2 > 0 )
		{
			drawAccValue.drawMultiLine(batch,"Duck attack you!",330,500);
			if ( cooldown2 == 50 )
			{
				cooldown2 = 0;
				isatted = false;
			}
		}
		
		/*
		 * Battle over check
		 */
		if ( saber.lifepoint == 0 )//hero die
		{
			Handle('M');
		}
		else if ( monster.lifepoint == 0 )//monster die
		{
			Handle('M');
		}
		heroinfo = saber.name + " HP:" + saber.lifepoint;
//		monsterinfo = monster.name + " HP:" + monster.lifepoint;
		drawAccValue.drawMultiLine(batch,"HE is \n A PIG",0,500);
		drawAccValue.drawMultiLine(batch,monsterinfo,770,500);
		
		timer++;
		batch.end(); 	
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {	}

	@Override
	public void resume() {}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch=new SpriteBatch(); 
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		background = new Texture(Gdx.files.internal("assets/data/battle/BG_Fight.png"));
		monster_t = new Texture(Gdx.files.internal("assets/data/battle/monster2.png"));
		slice = new Texture(Gdx.files.internal("assets/data/slice.png"));
		drawAccValue = new BitmapFont(
				Gdx.files.internal("assets/data/arial_test.fnt"),
				Gdx.files.internal("assets/data/arial_test.png"), false, true);
	}

	@Override
	public void onMessage(int msg) {
		System.out.println("Battle call onMsg :"+msg);
		/*
		 * values[0] = orientation. 0 = up , 1 = down , 2 = left , 3 = right.
		 * values[1] = on button click. 0 = unclick , 1 = isclick.
		 */

		
	}

}
