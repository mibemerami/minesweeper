package de.milanbrzezinski.minesweeper.welt;


import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
*
* @author  Milan Brzezinski
*/

public class Tile extends Label  {
	
	//Class-Variables: ------------------------------------
		public int positionX;
		public int positionY;
		
		public int hight;
		public int width;
		
		
		
		public boolean containsBomb = false;
		public boolean leftClicked = false;
		public boolean rightClicked = false;
		//public Board board;
	
	//Constructors: -----------------------------------------------------------
	public Tile(String string) {
		super.setText(string);
	}
	public Tile(int posX, int posY) {
		
		this.positionX = posX;
		System.out.println("posX: "+this.positionX);
		this.positionY = posY;
		System.out.println("posY: "+this.positionY);
		
	}
	public Tile(String string, int posX, int posY) {
		super.setText(string);
		this.positionX = posX;
		System.out.println("posX: "+this.positionX);
		this.positionY = posY;
		System.out.println("posY: "+this.positionY);
		
	}//-----------------------------------------------------------------------
	
	//Methods
	
	
	
	
	//---------------------------------------------------


	

}
