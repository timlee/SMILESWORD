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

package com.RPGame.ServerSocket;

import java.io.DataOutputStream;
import java.net.Socket;

/** Sends all inputs from touch, key, accelerometer and compass to a {@link RemoteInput} at the given ip/port. Instantiate this and
 * call sendUpdate() periodically.
 * 
 * @author mzechner */
public class RemoteSender {
	private DataOutputStream out;
	private boolean connected = false;

	public static final int SENSOR_MOTION = 1;
	public static final int BTN_ACTION = 2;

	public RemoteSender (String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			socket.setTcpNoDelay(true);
			socket.setSoTimeout(3000);
			out = new DataOutputStream(socket.getOutputStream());
			connected = true;
		} catch (Exception e) {
		}
	}

	public void sendMotion (int motion) {
		synchronized (this) {
			if (!connected) return;
		}
		try {
			out.writeInt(SENSOR_MOTION);
			out.writeInt(motion);
		} catch (Throwable t) {
			out = null;
			connected = false;
		}
	}	
	
	public void sendButtonAction (int action) {
		synchronized (this) {
			if (!connected) return;
		}
		try {
			out.writeInt(BTN_ACTION);
			out.writeInt(action);
		} catch (Throwable t) {
			out = null;
			connected = false;
		}
	}

	public boolean isConnected () {
		synchronized (this) {
			return connected;
		}
	}
}
