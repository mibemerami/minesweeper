package de.milanbrzezinski.minesweeper.welt;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import de.milanbrzezinski.minesweeper.fenster.info.InfoWon;
import javafx.scene.control.ContentDisplay;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class Board {
	
	//Class-Variables: ---------------------------------------------------
	public Tile[][] tileArray = new Tile[50][50];
	List<Tile> tilesDetectedAsZero = new ArrayList<Tile>(); //A list with the tiles that already have run the method clearConnectedTilesWithLabelZero
	int leftclickCount = 0;
	
	
	//------------------------------------------------------------------------------
	//Constructors:
	public Board(){
		for(int i=0; i<50; i++){
			for(int j=0; j < 50; j++){
				tileArray[i][j] = null;
			}
		}
			
	}
	
	//----------------------------------------------------------------------------
	//Methods:
	
	
	//The Tiles (which extend Label) are created and placed: ---------------------------------------------
	public void createBoard(int xAxis, int yAxis, int bombAmount){
		
		//With two combined loops the Buttons are created, supplied with the information, what to do, when clicked,
		//and put into the tileArray. 
		for (int i = 0; i < yAxis; i++) {
			for (int j = 0; j < xAxis; j++){
				
				Tile t = new Tile( j, i);
								
				t.setContentDisplay(ContentDisplay.CENTER);
												

				
				this.setTileImage(t, 0);
				t.setFont(Font.font("Tomaha", FontWeight.EXTRA_BOLD, 20));
				//t.setTextFill(Color.web("#0D14F2"));//Schriftfarbe: Blau
				t.setTextFill(Color.web("#000000")); //Schriftfarbe: Schwarz	
				
				//Schattierung - Schlagschatten 
		        DropShadow schatten = new DropShadow(); 
		        schatten.setOffsetX(4.0f);
		        schatten.setOffsetY(6.0f);
		        schatten.setColor(Color.BLACK);
		        
		        t.setEffect(schatten);
				
				//Put the tile-object into the tileArray from the board-object:
				tileArray[j][i] = t;
			}
		}
		
		
	}//---------------------------------------------------------------------------------------
	
	public boolean victoryTest(int xAxis, int yAxis, int bombAmount) {
		System.out.println("victoryTest wurde aufgerufen"+"xAxis: "+xAxis+"yAxis "+yAxis);
		int count = 0;
		for(int i = 0; i < yAxis; i++){
			for(int j = 0; j < xAxis; j++){
				//System.out.println("xAxis: "+j+" "+"yAxis "+i+" "+tileArray[j][i].toString());
				if (tileArray[j][i].containsBomb && tileArray[j][i].rightClicked){
					count++;
					if(count >= bombAmount){
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	//Methods to set an image on the tile:  --------------------------------------------------------
	
	public void setTileImage(Tile t, int status) { // the status number says which picture the tile gets
		String path = new String();
		switch(status){
		case 0:
			//path = "gfx/gerodet/gerodedx"+((t.positionX+7)%7)+"y"+((t.positionY+4)%4)+"px30.png"; 
			path = "gfx/einoede0i/x"+((t.positionX+12)%12)+"y"+((t.positionY+8)%8)+".png"; //Durch die Modulo-Funktion wird der Index des Arrays so auf den Index in den Bildnamen übertragen, dass er sich immer wiederholt und das kachelartige Gesamtbild ebtstehen kann.	
			break;
		case 1:
			path = "gfx/saftigeLandsch0bi/x"+((t.positionX+12)%12)+"y"+((t.positionY+8)%8)+".png";
			break;
		case 2:
			path = "gfx/warnschild0px30.png";
			break;
		case 3: 
			path = "gfx/explosion1iv.png";
			break;
		default:
			System.out.println("Bei board, setTileImage() Problem mit Status.");
		}
		Image image = new Image(path);
		ImageView ivImage = new ImageView();
		ivImage.setImage(image);
		t.setGraphic(ivImage);
	}
	public void setTileImages() {
		//Tu do
		String[] statusArray = new String[20];
		Image image = new Image("gfx/grün4.png");
		ImageView ivImage = new ImageView();
		ivImage.setImage(image);
		//t.setGraphic(ivImage);
	}//---------------------------------------------------------------------------------------------

	//Method that helps when a tile with 0 bombs around is clicked. It gets all surrounding tiles, opens them, than
	//gets the tiles among them, that are 0 as well and calls it self with that tiles. Tiles, that method have 
	// already been run on, are put into a list, to avoid an endless loop --------------------------------
	public void clearConnectedTilesWithLabelZero(int positionX, int positionY) {
		this.tilesDetectedAsZero.add(tileArray[positionX][positionY]);
		
		List<Tile> tilesAround = this.getTilesAround(positionX, positionY);
		System.out.println("tilesAround hat die Größe: "+tilesAround.size());
		for(Tile tile: tilesAround){
			if(!tile.containsBomb){
				tile.leftClicked = true;
				if (!(this.amountOfBombsAround(tile.positionX, tile.positionY)== 0))
					tile.setText(String.valueOf(this.amountOfBombsAround(tile.positionX, tile.positionY)));
				this.setTileImage(tile, 1);
			}
		}
		
		List<Tile> tilesWithNoSurroundingBombs = this.checkForTilesWithNoSurroundingBombs(tilesAround);
		System.out.println("tilesWithNoSurroundingBombs hat die Größe: "+tilesWithNoSurroundingBombs.size());
		for(Tile tile: tilesWithNoSurroundingBombs){
			if(!this.tilesDetectedAsZero.contains(tile))
				this.clearConnectedTilesWithLabelZero(tile.positionX, tile.positionY);
		}
	
	}
	
	public List<Tile> checkForTilesWithNoSurroundingBombs(List<Tile> tilesAround) {
		List<Tile> tilesWithLabelZero = new ArrayList<Tile>();
		
		for(Tile tile: tilesAround){
			if(this.amountOfBombsAround(tile.positionX, tile.positionY) == 0)
				tilesWithLabelZero.add(tile);
		}
		
		return tilesWithLabelZero;
	}

	public List<Tile> getTilesAround(int xAxis, int yAxis) {
		
		List<Tile> tiles = new ArrayList<Tile>();
		
		//check the three tiles above the given one
				if(!(xAxis == 0 || yAxis == 0))
					if(this.tileArray[xAxis-1][yAxis-1] != null)
						tiles.add(this.tileArray[xAxis-1][yAxis-1]);
						
				
				if(!(yAxis==0))
					if(this.tileArray[xAxis][yAxis-1] != null)
						tiles.add(this.tileArray[xAxis][yAxis-1]);
				
				if(!(yAxis==0))
					if(this.tileArray[xAxis+1][yAxis-1] != null)
						tiles.add(this.tileArray[xAxis+1][yAxis-1]);
						
				
				//check the left tile
					if(!(xAxis == 0))
						if(this.tileArray[xAxis-1][yAxis] != null)
							tiles.add(this.tileArray[xAxis-1][yAxis]);
							
					
				//check the right tile
				if(this.tileArray[xAxis+1][yAxis] != null)
					tiles.add(this.tileArray[xAxis+1][yAxis]);
					
				
				//check the three tiles below the given one
				if(!(xAxis == 0))
					if(this.tileArray[xAxis-1][yAxis+1] != null)
						tiles.add(this.tileArray[xAxis-1][yAxis+1]);
						
				
				if(this.tileArray[xAxis][yAxis+1] != null)
					tiles.add(this.tileArray[xAxis][yAxis+1]);
					
				
				if(this.tileArray[xAxis+1][yAxis+1] != null)
					tiles.add(this.tileArray[xAxis+1][yAxis+1]);
					
		return tiles;
	}
	//-------------------------------------------------------------------------------------

	//The Bombs are placed, by taking randomly a tile from the tileArray, testing whether it has already a bomb,
	//and than placing it. It stops when the given amount of bombs was placed.   ----------------------------------------
	public void setBombs(int amount, int xAxis, int yAxis){
		int count = 0;
		while(count < amount){
			int x = (int) (Math.random() * xAxis);
			int y = (int) (Math.random() * yAxis);
			//System.out.println("Das Tile wurde ausgewählt, um eine Bombe zu plazieren. "+x+" "+y);
			
			if(!this.tileArray[x][y].containsBomb){
				//System.out.println(this.tileArray[x][y].containsBomb);
				this.tileArray[x][y].containsBomb =true;
				count++;
				
			}
			
			
		}
	}
	//Sets Bombs like above, only the Tiles in the ArrayList are not set
	public void setBombs(int amount, int xAxis, int yAxis, int positionX, int positionY){
		int count = 0;
		
		List<Tile> setNoBombs = new ArrayList<Tile>();
		if((positionX-1 >= 0 && positionY+1 >= 0)&&(tileArray[positionX-1][positionY+1] != null)) setNoBombs.add(tileArray[positionX-1][positionY+1]);
		if((positionX >= 0 && positionY+1 >= 0)&&(tileArray[positionX][positionY+1] != null)) setNoBombs.add(tileArray[positionX][positionY+1]);
		if((positionX+1 >= 0 && positionY+1 >= 0)&&(tileArray[positionX+1][positionY+1] != null)) setNoBombs.add(tileArray[positionX+1][positionY+1]);
		if((positionX-1 >= 0 && positionY >= 0)&&(tileArray[positionX-1][positionY] != null)) setNoBombs.add(tileArray[positionX-1][positionY]);
		if((positionX >= 0 && positionY >= 0)&&(tileArray[positionX][positionY] != null)) setNoBombs.add(tileArray[positionX][positionY]);
		if((positionX+1 >= 0 && positionY >= 0)&&(tileArray[positionX+1][positionY] != null)) setNoBombs.add(tileArray[positionX+1][positionY]);
		if((positionX-1 >= 0 && positionY-1 >= 0)&&(tileArray[positionX-1][positionY-1] != null)) setNoBombs.add(tileArray[positionX-1][positionY-1]);
		if((positionX >= 0 && positionY-1 >= 0)&&(tileArray[positionX][positionY-1] != null)) setNoBombs.add(tileArray[positionX][positionY-1]);
		if((positionX+1 >= 0 && positionY-1 >= 0)&&(tileArray[positionX+1][positionY-1] != null)) setNoBombs.add(tileArray[positionX+1][positionY-1]);
		
		while(count < amount){
			int x = (int) (Math.random() * xAxis);
			int y = (int) (Math.random() * yAxis);
			//System.out.println("Das Tile wurde ausgewählt, um eine Bombe zu plazieren. "+x+" "+y);
			
			
			
			if((!this.tileArray[x][y].containsBomb)&&(!setNoBombs.contains(this.tileArray[x][y]))){
				//System.out.println(this.tileArray[x][y].containsBomb);
				this.tileArray[x][y].containsBomb =true;
				count++;
				
			}
			
			
		}
	}
	//-----------------------------------------------------------------------------------------
	

	
	//Counts the bombs in the surrounding tiles of a given tile:   ----------------------------------------------
	public int amountOfBombsAround(int xAxis, int yAxis){
		int count = 0;
		
		//check the three tiles above the given one
		if(!(xAxis == 0 || yAxis == 0))
			if(this.tileArray[xAxis-1][yAxis-1] != null)
				if(this.tileArray[xAxis-1][yAxis-1].containsBomb)
					count++;
		
		if(!(yAxis==0))
			if(this.tileArray[xAxis][yAxis-1] != null)
				if(this.tileArray[xAxis][yAxis-1].containsBomb)
					count++;
		
		if(!(yAxis==0))
			if(this.tileArray[xAxis+1][yAxis-1] != null)
				if(this.tileArray[xAxis+1][yAxis-1].containsBomb)
					count++;
		
		//check the left tile
			if(!(xAxis == 0))
				if(this.tileArray[xAxis-1][yAxis] != null)
					if(this.tileArray[xAxis-1][yAxis].containsBomb) 
						count++;
			
		//check the right tile
		if(this.tileArray[xAxis+1][yAxis] != null)
			if(this.tileArray[xAxis+1][yAxis].containsBomb) 
				count++;
		
		//check the three tiles below the given one
		if(!(xAxis == 0))
			if(this.tileArray[xAxis-1][yAxis+1] != null)
				if(this.tileArray[xAxis-1][yAxis+1].containsBomb)
					count++;
		
		if(this.tileArray[xAxis][yAxis+1] != null)
			if(this.tileArray[xAxis][yAxis+1].containsBomb)
				count++;
		
		if(this.tileArray[xAxis+1][yAxis+1] != null)
			if(this.tileArray[xAxis+1][yAxis+1].containsBomb)
				count++;
				
		
		
		return count;
	}//-------------------------------------------------------------------------------------------------
	
	public int countMarkedBombs(int xAxis, int yAxis){
		int count = 0;
		for(int i = 0; i < yAxis; i++){
			for(int j = 0; j < xAxis; j++){
				//System.out.println("xAxis: "+j+" "+"yAxis "+i+" "+tileArray[j][i].toString());
				if (tileArray[j][i].rightClicked){
					count++;
					
				}
			}
		}
		return count;
	}
	
	
	
}
