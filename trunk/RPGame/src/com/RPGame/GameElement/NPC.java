package com.RPGame.GameElement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPC extends Creature {
	
	String name;
	int npcx;
	int npcy;
	Texture npc;

	@Override
	protected void createEquipment() {
		// TODO Auto-generated method stub	
	}

	@Override
	protected void createWeapon() {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawState(SpriteBatch batch) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawBody(int direct, SpriteBatch batch, int time, int nx,
			int ny) {
		
	}
	
	public void setDetail(String name, int x, int y)
	{
		this.name = name;
		this.npcx = x;
		this.npcy = y;
	}
	
	public String showName()
	{
		return name;
	}
	
	public int showdPositionX()
	{
		return npcx;
	}
	
	public int showPositionY()
	{
		return npcy;
	}
	
	public void drawNPC(SpriteBatch batch,Texture npcname)
	{
		batch.draw(npcname,npcx,npcy);
	}
	

}
