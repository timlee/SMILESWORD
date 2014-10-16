package com.RPGame.GameControl;

import com.badlogic.gdx.Game;

public class MyGame extends Game{
	 
	 public static enum platformCode {DESKTOP, ANDROID, HTML5};
	 public Controller ctr; 
	 public platformCode pC;
	 
	 public MyGame ( platformCode pC )
	 {
		 super();
		 ctr = new Controller(this);
		 this.pC = pC;
	 }

	 @Override
	 public void create() 
	 {
		 ctr.Request('W');
	 }

	 @Override
	 public void resume() 
	 {
	     super.resume();
	 }
}