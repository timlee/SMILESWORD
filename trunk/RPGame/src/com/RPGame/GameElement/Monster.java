package com.RPGame.GameElement;

import java.sql.SQLException;
import java.util.ArrayList;

import com.RPGame.DBAccess.Select;
import com.RPGame.DBAccess.TableQuery;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Monster extends Creature{
	
	String name;
	int monx;
	int mony;
	Texture monster;
	
	public Monster()
	{
		
			this.name ="scarecrow";
			this.lifepoint = 30;
			this.atkpoint =10;
			this.lv =1 ;
			this.exp = 10;
			
		
	}

	protected ArrayList<String[]> getAward() {
		return this.item;
	}

	@Override
	protected void createEquipment() {
		this.equipment = null;
	}

	@Override
	protected void createWeapon() {
		this.weapon = null;
	}

	@Override
	public void drawState(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBody( int direct , SpriteBatch batch , int time ,int nx,int ny) {
		// TODO Auto-generated method stub
		
	}
	
    public void setMonster(String name, int x, int y){
    	this.name=name;
    	this.monx=x;
    	this.mony=y;
    	
    }
    
    public String showName()
	{
		return name;
	}
	
	public int showdPositionX()
	{
		return monx;
	}
	
	public int showPositionY()
	{
		return mony;
	}
	
	public int showlifepoint(){
		return lifepoint;
	}
    
	
    
    public void drawNPC(SpriteBatch batch,Texture npcname)
	{
    	if(this.lifepoint>0)
    		batch.draw(npcname,monx,mony);
	}

}
