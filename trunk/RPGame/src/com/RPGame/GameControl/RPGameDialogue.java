package com.RPGame.GameControl;

import java.util.ArrayList;
import java.util.Hashtable;

public class RPGameDialogue {
	
	ArrayList<String>  Emily = new ArrayList<String>();
	ArrayList<String>  Delg = new ArrayList<String>();
	
	public RPGameDialogue()
	{
		Emily.add("Emily: \nHi, My name is Emily. \nWelcome to the 'Sword Smile' ");
		Emily.add("Emily: \nIt's your first time to join the game,so \nnow I am going to teach you something.");
		Emily.add("Emily: \nLook at your moblie. \nNow you find the buttons on it.");
		Emily.add("Emily: \nOK, now there are \n¡ô&¡õ for Up&Down, ¡ö&¡÷ for Left&Right. \nAnd there are also A & B buttons.");
		Emily.add("Emily: \nNow you can press the ¡ô¡õ¡ö¡÷buttons \nto move the body.");
		Emily.add("Emily: \nIf you want to attack,\nyou can wave your arm.");
		Emily.add("Emily: \nGood!\nAnd now help me to practice beating the \nscarecrow in the middle of the map.");
		Emily.add("Emily: \nGood!\nAnd find my brother,Delg.");
		
		Delg.add("");
		Delg.add("");
		Delg.add("Delg: \nHi, I am Delg, your brother.");
		Delg.add("Delg: \nI know. \nIt's really a hard time for us.'");
		Delg.add("Delg: \nBut we cannot give up\nto be a human being.");
		Delg.add("Delg: \nLet's go adventure.");
		Delg.add("Delg: \nAnd now I call it 'Sword Smile'");
		
	}

	public Hashtable<String, ArrayList<String>> getDialogue(){
		
		Hashtable<String, ArrayList<String>> dia = new Hashtable<String, ArrayList<String>>();
		dia.put("Emily", Emily);
		dia.put("Delg", Delg);
	
		return dia;		
	}
	
}
