package com.RPGame.GameElement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Saber extends Creature{

	Texture body,die;
	int count;
	int ndir;
	int ndirpng;
	int nattack;
	int ndirect;
	int c;
	Music music;

	Texture attack;
	protected BitmapFont drawDialogue;
	
	public Saber ()
	{
		/*
		 * set hero 
		*/
		this.name = "Emily";
		this.lifepoint = 100;
		this.nlifepoint = 50;
		this.atkpoint = 2;
		this.defepoint = 1;
		this.lv = 1;
		this.money = 0;
		this.exp = 10;
		this.nexp=0;
		this.count = 0;
		this.ndir = 6;
		this.ndirpng = 2;
		this.nattack = 1;
		this.ndirect=3;
		this.c=0;
		/*
		* end set hero
		*/
		drawDialogue = new BitmapFont(
				Gdx.files.internal("assets/data/arial_test.fnt"),
				Gdx.files.internal("assets/data/arial_test.png"), false, true);
	}

	@Override
	protected void createEquipment() {
		//this.equipment = new Equipment(resultset[7]);
		//adjDefpoint(this.equipment.point);
	}

	@Override
	protected void createWeapon() {
		//this.weapon = new Weapon(resultset[8]);
		//adjAtkpoint(this.weapon.point);	
	}

	@Override
	public void drawState(SpriteBatch batch) {
		
		drawDialogue.drawMultiLine(batch,nlifepoint+"/"+lifepoint, 220, 818);
		drawDialogue.drawMultiLine(batch,nexp+"/"+exp, 220, 755);
		drawDialogue.drawMultiLine(batch,"LV:"+lv,5,744);
		
	}

	@Override
	public void drawBody( int direct , SpriteBatch batch , int time ,int nx,int ny) {
		if(count == 10)
		{
			ndirpng ++;
			count =0;
			if(ndirpng == 5)
			{
				ndirpng = 1;
			}
		}

		if ( direct==0 )
		{
			body = new Texture(Gdx.files.internal("assets/data/walk/"+(ndir+"_"+2)+".png"));
		}
		else if(direct==3||direct==4||direct==5||direct==6)
		{
			body = new Texture(Gdx.files.internal("assets/data/walk/"+(direct+"_"+ndirpng)+".png"));
			ndir=direct;
		}
		System.out.println(direct+"_"+ndirpng);
		batch.draw(body,nx,ny);
		count++;
	}
	
	
	public void attack(int direct,int nx,int ny, SpriteBatch batch){
		
    	if(count == 10)
		{
    		nattack ++;
			count =0;
			if(nattack == 4)
			{
				nattack = 1;
			}
		}
		
		attack = new Texture(Gdx.files.internal("assets/data/attack/"+(ndir+"_"+nattack)+".png"));

		batch.draw(attack,nx,ny);
		count++;
   

    }

	
	

	
	
}
