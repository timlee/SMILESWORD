package com.RPGame;

import com.RPGame.GameControl.MyGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopGame {
        public static void main (String[] args) {
        	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    		cfg.title = "RPGame";
    		cfg.useGL20 = false; //true; //updated to support non powers of two sized images
    		cfg.width = 1408;
    		cfg.height = 832;
    		new LwjglApplication(new MyGame(MyGame.platformCode.DESKTOP), cfg);
        }
}
