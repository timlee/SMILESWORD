package com.RPGame.GameElement;

import java.util.ArrayList;
import com.RPGame.DBAccess.DBStrategy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Creature {
	public String name;
	public int lifepoint;
	public int nlifepoint;
	public int atkpoint;
	public int defepoint;
	public int lv;
	public int money;
	public int exp;
	public int nexp;
	public Equipment equipment;
	public Weapon weapon;
	public ArrayList<String[]> item;
	public DBStrategy dbs;
	String result;
	String[] resultset;
	
	public void adjLifepoint( int value )
	{
		lifepoint += value;
	}
	public void adjAtkpoint( int value )
	{
		atkpoint += value;
	}
	public void adjDefpoint( int value )
	{
		defepoint += value;
	}
	protected abstract void createEquipment();
	protected abstract void createWeapon();
	public abstract void drawState(SpriteBatch batch);
	public abstract void drawBody( int direct , SpriteBatch batch , int time ,int nx,int ny);
	

}
