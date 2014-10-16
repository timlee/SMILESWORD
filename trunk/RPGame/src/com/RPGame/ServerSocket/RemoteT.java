package com.RPGame.ServerSocket;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.RPGame.GameControl.GameState;
import com.RPGame.ServerSocket.RemoteInput;
import com.badlogic.gdx.InputProcessor;

public class RemoteT implements InputProcessor{
	GameState state;
	public RemoteInput receiver;
	public RemoteT( GameState state )
	{
		this.state = state;
	}
	
    public	void connect(){
    receiver = new RemoteInput(state);
	Gdx.input = (Input) receiver;
	Gdx.input.setInputProcessor(this);
	
	
	}

    public void setState( GameState state )
    {
    	receiver.setState(state);
    }
    
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Gdx.app.log("Input Test", "touch dragged: " + x + ", " + y + ", pointer: " + pointer);
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
