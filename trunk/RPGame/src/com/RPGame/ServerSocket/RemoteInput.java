package com.RPGame.ServerSocket;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/



import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.RPGame.GameControl.GameState;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.GdxRuntimeException;

import com.RPGame.ServerSocket.RemoteSender;

/** <p>
 * An {@link Input} implementation that receives touch, key, accelerometer and compass events from a remote Android device. Just
 * instantiate it and specify the port it should listen on for incoming connections (default 8190). Then store the new RemoteInput
 * instance in Gdx.input. That's it.
 * </p>
 * 
 * <p>
 * On your Android device you can use the gdx-remote application available on the Google Code page as an APK or in SVN
 * (extensions/gdx-remote). Open it, specify the IP address and the port of the PC your libgdx app is running on and then tap
 * away.
 * </p>
 * 
 * <p>
 * The touch coordinates will be translated to the desktop window's coordinate system, no matter the orientation of the device
 * </p>
 * 
 * @author mzechner */
public class RemoteInput implements Runnable, Input {
	public interface RemoteInputListener {
		void onConnected ();

		void onDisconnected ();
	}

	public static int DEFAULT_PORT = 8900;
	private ServerSocket serverSocket;
	private int[] sensor_motion = new int[1];
	private int[] btn_action = new int[1];
	public boolean connected = false;
	public RemoteInputListener listener;
	public final int port;
	public final String[] ips;
	GameState state;

	public RemoteInput (GameState state) {
		this(DEFAULT_PORT);
		this.state = state;
	}
	
	 public void setState( GameState state )
	 {
	    this.state = state;	
	 }

	public RemoteInput (RemoteInputListener listener) {
		this(DEFAULT_PORT, listener);
	}

	public RemoteInput (int port) {
		this(port, null);
	}

	public RemoteInput (int port, RemoteInputListener listener) {
		this.listener = listener;
		try {
			this.port = port;
			serverSocket = new ServerSocket(port);
			Thread thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
			InetAddress[] allByName = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			ips = new String[allByName.length];
			for (int i = 0; i < allByName.length; i++) {
				ips[i] = allByName[i].getHostAddress();
			}
		} catch (Exception e) {
			throw new GdxRuntimeException("Couldn't open listening socket at port '" + port + "'", e);
		}
	}

	@Override
	public void run () {
		while (true) {
			try {
				connected = false;
				if (listener != null) listener.onDisconnected();
				System.out.println("listening, port " + port);
				Socket socket = null;

				socket = serverSocket.accept();
				socket.setTcpNoDelay(true);
				socket.setSoTimeout(3000);
				connected = true;
				if (listener != null) listener.onConnected();

				DataInputStream in = new DataInputStream(socket.getInputStream());
				while (true) {
					int event = in.readInt();
					switch (event) {
					case RemoteSender.SENSOR_MOTION:
						sensor_motion[0] = in.readInt();	//0 = no attack, 1 = attack
//						System.out.println("Sensor" + sensor_motion[0]);
						state.onMessage(sensor_motion[0]+10);
						break;
					case RemoteSender.BTN_ACTION:
						btn_action[0] = in.readInt();
//						System.out.println("Button" + btn_action[0]);
						state.onMessage(btn_action[0]);
						break;
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isConnected () {
		return connected;
	}

	public float getSensorMotion () {
		return sensor_motion[0];
	}

	public float getButtonAction () {
		return btn_action[0];
	}

	/** @return the IP addresses {@link RemoteSender} or gdx-remote should connect to. Most likely the LAN addresses if behind a NAT. */
	public String[] getIPs () {
		return ips;
	}

	public boolean isPeripheralAvailable (Peripheral peripheral) {
		if (peripheral == Peripheral.Accelerometer) return true;
		if (peripheral == Peripheral.Compass) return true;
		return false;
	}

	@Override
	public void cancelVibrate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getAccelerometerX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelerometerY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAccelerometerZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAzimuth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCurrentEventTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDeltaX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDeltaX(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDeltaY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDeltaY(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputProcessor getInputProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orientation getNativeOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getPlaceholderTextInput(TextInputListener arg0, String arg1,
			String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getRoll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getRotationMatrix(float[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTextInput(TextInputListener arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isButtonPressed(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCursorCatched() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isKeyPressed(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTouched() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTouched(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean justTouched() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCatchBackKey(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCatchMenuKey(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCursorCatched(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCursorPosition(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInputProcessor(InputProcessor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOnscreenKeyboardVisible(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vibrate(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vibrate(long[] arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
