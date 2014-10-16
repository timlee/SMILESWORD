package com.RPGame.GameControl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.RPGame.GameElement.Saber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GameState implements Screen {
	protected MyGame game;
	protected Music music;
	protected SpriteBatch batch;
	protected Saber sb;
	protected Texture background,dialogue,head256,hurtscarecrow,head,lp,exp;
	protected Stage stage;
	protected BitmapFont drawDialogue; // show dialogue
	protected boolean ischange,isdia;
	protected char page;
	int nx=0,ny=0;
	public static enum platformCode {DESKTOP, ANDROID, HTML5};
	protected abstract void Handle( char GS );
	public abstract void onMessage(int msg);
	
	public void drawMap(String[][] map, SpriteBatch batch, Map<String,Texture> tex){
		Map<String,Texture> textures = tex;	

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				
				int a = 0;
				int b=Gdx.graphics.getHeight()-64;
				
				a += j * 64;
				b -= i * 64;
				batch.draw(textures.get(map[i][j]), a,b);
			}
		}
	
	} //drawMap
	
	public Map<String,Texture> getTextures(String[][] map)
	{
		Map<String,Texture> textures = new HashMap<String,Texture>();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(textures.get(map[i][j]) == null)
				 textures.put( map[i][j] , new Texture(Gdx.files.internal("assets/data/mapstatement/"
				 		+map[i][j]+".png")) );	
			}
		}

		return textures;
	}
	
	public void  move(int x,int y,int direct)
	{
		//1 for up, 2 for down, 3 for left, 4 for right
  	
    	int a=(x)/64;
    	int a2=((x+8)/64)+1;
    	int a3=(x-8)/64;
    	int a4=(x+8)/64;
    	int b=((Gdx.graphics.getHeight()-y)/64)-1;
    	int b2=((Gdx.graphics.getHeight()-(y+8))/64)-1;
    	int b3=((Gdx.graphics.getHeight()-(y-8))/64);
    	int b4=((Gdx.graphics.getHeight()-(y-8))/64)-1;
	
    	//¤W
    	if (b2>=0&& direct==3 && RPGgameMap.practiceMap[b2][a].charAt(0)=='r'){
    		this.nx=x;
    		this.ny=y+8;
    	}
    	//¤U
    	if (((y-2)%64==0  && direct==6 && RPGgameMap.practiceMap[b4][a].charAt(0)=='r')||(y-2)>=0&&direct==6&& RPGgameMap.practiceMap[b3][a].charAt(0)=='r'){
    		
    		this.nx=x;
    		this.ny=y-8;
    	}
    	//¥ª
    	if((x-2)>=0  &&direct==4 && RPGgameMap.practiceMap[b][a3].charAt(0)=='r'){
    		
    		this.nx=x-8;
    		this.ny=y;
    	}
    	//¥k
    	if(((x+2)%64==0  && direct==5 && RPGgameMap.practiceMap[b][a4].charAt(0)=='r')||(x+2)<=Gdx.graphics.getWidth()-64&&(direct==5 && RPGgameMap.practiceMap[b][a2].charAt(0)=='r') ){
    		
    		this.nx=x+8;
    		this.ny=y;
    	}
    
    }
	
	public void showDialogue(String msg,Texture head)
	{
		batch.draw(head,0,0);
		batch.draw(dialogue,256,0);
		drawDialogue.drawMultiLine(batch, msg, 283, 230);
	}
	
	public void happenattack(int nx,int ny,int monx,int mony,int dir,SpriteBatch batch)
	{
		switch(dir)
		{
		case 5:
			if((monx-nx>0) && (70>monx-nx) && ((ny-mony<80)&&(ny-mony>-10)))
			{
				batch.draw(hurtscarecrow,monx,mony);
			}
			break;
		case 3:
			if((mony-ny>0) && (70>mony-ny) && ((nx-monx<70)&&(nx-monx>-10)))
			{
				batch.draw(hurtscarecrow,monx,mony);
			}
			break;
		case 4:
			if((nx-monx<144)  &&((ny-mony<80)&&(ny-mony>-10)))
			{
				batch.draw(hurtscarecrow,monx,mony);
			}
			break;
		case 6:
			if((ny-mony<144)  && (nx-monx<80)&&(nx-monx>-10))
			{
				batch.draw(hurtscarecrow,monx,mony);
			}
			break;
	
		}
		
		}
	
	public void happenDialogue(int nx, int ny, int npcx, int npcy, int dir)
	{
		//isdia = false;
		
		switch(dir)
		{
		case 5:
			if(npcx-nx>0 && 70>npcx-nx && Math.abs(npcy-ny)<10)
			{
				this.isdia = true;
			}
			break;
		case 3:
			if(npcy-ny>0 && 70>npcy-ny && Math.abs(npcx-nx)<10)
			{
			
				this.isdia = true;
			}
			break;
		case 4:
			if(nx-npcx>0 && 70>nx-npcx && Math.abs(npcy-ny)<10)
			{
				this.isdia = true;
			}
			break;
		case 6:
			if(ny-npcy>0 && 70>ny-npcy && Math.abs(npcx-nx)<10)
			{
				this.isdia = true;
			}
			break;
	
		}
	}
	
	public void showlpexp(SpriteBatch batch) {  //count lp and exp
		
		float nblood = 438 * ((float)sb.nlifepoint/(float)sb.lifepoint);
		float nex = 438 * (sb.nexp/sb.exp);
        batch.draw(lp, 192, 772, nblood, 50);
        batch.draw(exp, 192, 708, nex, 50);
		
	}
}
