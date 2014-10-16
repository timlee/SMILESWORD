package com.RPGame.GameControl;


import com.RPGame.ServerSocket.RemoteInput;
import com.RPGame.ServerSocket.RemoteT;

public class Controller{
	private char game_state;
	public GameState state; 
	private MyGame game;
	RemoteT remotet;
	
	
	
	
	
	
	Controller(MyGame game)
	{
		game_state = 'P';
		this.game = game;
	    remotet=new RemoteT(state);
	    remotet.connect();
	}
	
	public void Request( char state_var )
	{
		switch (state_var)
		{
			case 'W': this.goto_W();	break;
			case 'K': this.goto_K();	break;
			case 'S': this.goto_S();	break;
			case 'I': this.goto_I();	break;
			case 'F': this.goto_F();	break;
			case 'B': this.goto_B();	break;
			case 'P': this.goto_P();	break;
			case 'T': this.goto_T();	break;
			case 'Q': this.goto_Q();	break;
		}
		game.setScreen(state);
	    remotet.setState(state);
	}
	
	public char getState()
	{
		return game_state;
	}
	
	public void setState(char k)
	{
		game_state = k;
	}
	
	private void goto_B()
	{
		game_state = 'B';
		state = new BeginPage(game);
	}
	
	
	private void goto_W()
	{
		game_state = 'W';
		state = new WaitingPage(game);
	}
	
	private void goto_K()
	{
		game_state = 'M';
		state = new Skill();
	}
	
	private void goto_S()
	{
		game_state = 'M';
		state = new Shop();
	}
	
	private void goto_I()
	{
		game_state = 'M';
		state = new Item();
	}
	
	private void goto_F()
	{
		game_state = 'F';
		state = new Battle(game);
	}
	
	private void goto_P()
	{
		game_state = 'P';
		state = new Practice(game);
	}
	
	private void goto_T()
	{
		game_state = 'T';
		state = new tbc(game);
	}
	
	private void goto_Q()
	{
		game_state = 'Q';
		state = new Practice2(game);
	}
	

}