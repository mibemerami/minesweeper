package de.milanbrzezinski.minesweeper.fenster;




import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.sound.Song;
import de.milanbrzezinski.minesweeper.welt.*;
import de.milanbrzezinski.minesweeper.fenster.info.*;
import de.milanbrzezinski.minesweeper.ereignis.*;
import de.milanbrzezinski.minesweeper.benutzer.*;

/**
*
* @author  Milan Brzezinski
*/


public class SpielFenster extends javafx.application.Application {
	//Class-Variables
	public int xAxis = 12; //Amount of the tiles on the x-axis
	public int yAxis = 6; //Amount of the  tiles on the y-axis
	public int bombAmount = 10;
	public boolean playWithEvents = true;
	public Board board = new Board();
	BorderPane border = new BorderPane();
	HBox hb = new HBox();
	HBox hb1 = new HBox();
	HBox hb2 = new HBox();
	HBox hb3 = new HBox();
	HBox hb4 = new HBox();
	HBox hb5 = new HBox();
	VBox vb = new VBox();
	GridPane grid = new GridPane();
	public Stage stage; //Die stage, die auch in start() verwended wird, wird in diesem Feld gespeichert, damit das Info-Fenster InfoWon darauf zugreifen kann, und so das Spiel-Fenster schließen kann
	public int countLeftClicks = 0;
	Label schilder = new Label("Schilder: 0");
	Label emty0 = new Label();
	Label bombNumber = new Label();
//	Label anzLeben = new Label();
	Label anzeigeLevel = new Label();
	public DataBenutzer dataBnz;
	int[] einstellungenEreignis = new int[]{25,25,25,5,15,5}; //gibt die Warscheinlichkeit in Prozent an, mit der die Ereignisse auftreten
	public int level = 0;
	Song sound = new Song();
	MenuBar menuBar = new MenuBar();
	Menu menuGame = new Menu("Game");
	MenuItem gameQuit = new MenuItem("quit");
	Menu menuSound = new Menu("Sound");
	MenuItem soundLaut = new MenuItem("laut");
	MenuItem soundMittel = new MenuItem("mittel");
	MenuItem soundLeise = new MenuItem("leise");
	MenuItem soundAus = new MenuItem("Ton an/aus");
	Menu menuInfo = new Menu("Info");
	MenuItem infoStatus = new MenuItem("Spiel Info");
	MenuItem infoVersion = new MenuItem("Version");
	int probabilityOfGettingAnEvent = 3;
	
	//Constructors:
	public SpielFenster(DataBenutzer dbnz){
		dataBnz = dbnz;
		//if(dataBnz.angemeldet)dbnz.spieleCountUp();
//		if(dataBnz.angemeldet)dataBnz.spieleCountUp();
	}
	public SpielFenster(DataBenutzer dbnz, int xAchse, int yAchse, int bombenAnzahl, int niveau,  int[] einstEreignis){
		dataBnz = dbnz;
		//if(dataBnz.angemeldet)dbnz.spieleCountUp();
//		if(dataBnz.angemeldet)dataBnz.spieleCountUp();
		xAxis = xAchse;
		yAxis = yAchse;
		bombAmount = bombenAnzahl;
		level = niveau;
		einstellungenEreignis = einstEreignis;
	}
	public SpielFenster(DataBenutzer dbnz, int xAchse, int yAchse, int bombenAnzahl, int niveau,  int[] einstEreignis, int probOfEvent){
		dataBnz = dbnz;
		//if(dataBnz.angemeldet)dbnz.spieleCountUp();
//		if(dataBnz.angemeldet)dataBnz.spieleCountUp();
		xAxis = xAchse;
		yAxis = yAchse;
		bombAmount = bombenAnzahl;
		level = niveau;
		einstellungenEreignis = einstEreignis;
		probabilityOfGettingAnEvent = probOfEvent;
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("MineSweeper "+ControlSession.version);
		
		if(dataBnz.angemeldet)dataBnz.spieleCountUp();
		
		menuBar.getMenus().add(menuGame);
		menuGame.getItems().addAll(gameQuit);
		menuBar.getMenus().add(menuSound);
		menuSound.getItems().addAll(soundLaut, soundMittel, soundLeise, soundAus);
		menuBar.getMenus().add(menuInfo);
		menuInfo.getItems().addAll(infoStatus, infoVersion);
		
		schilder.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));		
		bombNumber.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));
		bombNumber.setText(" Mienen: "+bombAmount);
//		anzLeben.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));	
//		if(dataBnz.angemeldet)
//			anzLeben.setText(" Leben: "+dataBnz.bnz.leben);
		

		hb1.setAlignment(Pos.CENTER);
		hb2.getChildren().add(schilder);
		hb3.getChildren().add(bombNumber);
//		hb4.getChildren().add(anzLeben);
		hb1.getChildren().add(hb2);
		hb1.getChildren().add(hb3);
		if(dataBnz.angemeldet) hb1.getChildren().add(hb4);
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(2);
		grid.setVgap(2);
		grid.setPadding(new Insets( 10, 10, 10, 10));
		grid.setStyle("-fx-background-color: #ffffff;"
					 +"-fx-border-style: solid;"
					 +"-fx-border-width: 1;"
				     +"-fx-border-color: black");
		
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().add(grid);
		
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(vb);
		
		//border.setTop(emty0);#
		border.setTop(menuBar);
		border.setBottom(hb1);
		border.setCenter(hb);
		
		Scene scene = new Scene(border, (xAxis*32+100), (yAxis*32+100)); //Die Größe des Fensters richtet sich nach der Anzahl der gesetzten Felder
		primaryStage.setScene(scene);
		
		
		board.createBoard(xAxis, yAxis, bombAmount);
		
		this.showBoard();
		
		this.setMenuReactions();

		
		
		//grid.setGridLinesVisible(true);
		primaryStage.show();
		this.stage = primaryStage; 
	}
	
	
	//Methods:
	
	private void setMenuReactions() {
		gameQuit.setOnAction(e -> {
			stage.close();
		});
		soundLaut.setOnAction(e -> {
			ControlSession.lautstaerke = 0.0f;
			System.out.println("soundLaut wurde betätigt");
		});
		soundMittel.setOnAction(e -> {
			ControlSession.lautstaerke = -20.0f;
			System.out.println("ControlSession.lautstaerke: "+ControlSession.lautstaerke);
		});
		soundLeise.setOnAction(e -> {
			ControlSession.lautstaerke = -40.0f;
			System.out.println("ControlSession.lautstaerke: "+ControlSession.lautstaerke);
			
		});
		soundAus.setOnAction(e -> {
			if(ControlSession.lautstaerkeAus==false){
				ControlSession.lautstaerkeAus = true;
			}else{
				ControlSession.lautstaerkeAus = false;
			}
		});
		infoStatus.setOnAction(e -> {
			new InfoStatus(this).start(new Stage());
		});
		infoVersion.setOnAction(e -> {
			new InfoVersion(this).start(new Stage());
		});
		
	}
	
	public void showBoard(){
		for (int i = 0; i < yAxis; i++) {
			for (int j = 0; j < xAxis; j++){
				if(board.tileArray[j][i] != null){
					Tile t = board.tileArray[j][i];
					System.out.println("Ein Tile wurde an t übergeben. ");	
					System.out.println(t.toString());
					this.setTileReactions(t);
					grid.add(t, j, i);
				}else{
					System.out.println("Das Array-Feld "+i+ j+" war leer");
				}
			}
		}
	}


	private void setTileReactions(Tile t) {
		t.setOnMouseClicked( e -> {
			System.out.println("Ein Button wurde geklickt: "+(t.positionX)+(t.positionY));
			
			//What to do when the left mouse-button was pressed:
			if(e.getButton() == MouseButton.PRIMARY){
				System.out.println("Es wurde die linke Maustaste benutzt.");
				this.linksKlick(t);

			}
			
			//What to do when the right mouse-button was pressed:
			if(e.getButton() == MouseButton.SECONDARY){
				System.out.println("Es wurde die rechte Maustaste benutzt.");
				this.rechtsKlick(t);
			}
			
		});
		
	}

	//What to do when the left mouse-button was pressed:
	public void linksKlick(Tile t) {
		//Wenn noch nicht geklickt wurde werden hier erst die Mienen gesetzt 
		if(this.countLeftClicks < 1){
			this.board.setBombs(this.bombAmount, this.xAxis, this.yAxis, t.positionX, t.positionY);
		}
		if(!t.rightClicked){
			boolean alreadyOpen = false;
			if (t.leftClicked) alreadyOpen = true;
			t.leftClicked = true;
			
			if(t.containsBomb){
				sound.play("snd/ljudman_grenade.wav"); //sound abspielen
				//t.setText("Explosion");
				board.setTileImage(t, 3);
				if((dataBnz.angemeldet == true)){
					if (this.dataBnz.bnz.leben > 0){
						t.leftClicked = false;
						new InfoLostLife(this,t).start(new Stage());
						if(dataBnz.angemeldet){
							this.dataBnz.lebenCountDown(); //Den Benutzeraccount aktuallisieren
						}
					}else{
						System.out.println("Spiel verloren, angemeldet");
						new InfoLost(this).start(new Stage());
						if(dataBnz.angemeldet)this.dataBnz.verlorenCountUp(); //Den Benutzeraccount aktuallisieren
						//sound.play("snd/forestInferno_with_dramatic_chorus.wav"); //Sound abspielen
					}
						
				}else{
					System.out.println("Spiel verloren, unangemeldet");
					new InfoLost(this).start(new Stage());
					if(dataBnz.angemeldet)this.dataBnz.verlorenCountUp(); //Den Benutzeraccount aktuallisieren
					//sound.play("snd/forestInferno_with_dramatic_chorus.wav"); //Sound abspielen
				}
				System.out.println("Spiel verloren, nach if-else");
				
			}else{
				
				if(board.amountOfBombsAround(t.positionX, t.positionY)==0){
					sound.play("snd/bertrof_game-sound-correct.wav"); //sound abspielen
					board.clearConnectedTilesWithLabelZero( t.positionX,  t.positionY);	
				}else{
					t.setText(String.valueOf(board.amountOfBombsAround(t.positionX, t.positionY)));
					
				}
				board.setTileImage(t, 1);
			}
			
			//Wenn der Zufall es bestimmt wird hier ein Ereignis aufgerufen (z.B. Positiv3 = Felder werden aufgedeckt) 
			if (this.ereignis()&&!t.containsBomb&&!alreadyOpen){
				if(!(this.countLeftClicks < 1)){
					ControlEreignis.go(this,einstellungenEreignis);
				}
			}
			this.countLeftClicks++;
		}
		
	}
	
	//What to do when the right mouse-button was pressed:
	public void rechtsKlick(Tile t) {
		
		if(this.countLeftClicks >= 1){
			if(!t.leftClicked){ 		//Wenn das geklickte Feld noch nicht aufgedeckt ist,
				if(!t.rightClicked){	//und noch kein Warn-Schild hat
					//t.setText("Bomb");
					t.rightClicked = true;
					board.setTileImage(t, 2);
					this.schilder.setText("Schilder: "+this.board.countMarkedBombs(this.xAxis, this.yAxis));
					if(this.board.countMarkedBombs(this.xAxis, this.yAxis)==this.bombAmount){
						if (board.victoryTest(xAxis, yAxis, bombAmount)){
							
							int punkte = this.punkteBerechnen(); //(int)(20.0*(1.0*bombAmount/(xAxis*yAxis))+(10.0*(1.0*(xAxis*yAxis)/(40*20)))); //Formel für Punkte
							System.out.println("Spielfenster übergibt Punkte: "+punkte);
							System.out.println("bombAmount = "+bombAmount+", xAxis = "+xAxis+", yAxis = "+yAxis);
							if (dataBnz.angemeldet){
								this.dataBnz.siegeCountUp(punkte); //Den Benutzeraccount aktuallisieren
								if (level > 0 ) dataBnz.levelCountUp();
							}
							new InfoWon(this).start(new Stage());
						}
					}
				}else{
					//t.setText("Feld");
					t.rightClicked = false;
					board.setTileImage(t, 0);
					this.schilder.setText("Schilder: "+this.board.countMarkedBombs(this.xAxis, this.yAxis));
					if(this.board.countMarkedBombs(this.xAxis, this.yAxis)==this.bombAmount){
						if (board.victoryTest(xAxis, yAxis, bombAmount)){
							
							int punkte = this.punkteBerechnen();//(int)(20.0*(1.0*bombAmount/(xAxis*yAxis))+(10.0*(1.0*(xAxis*yAxis)/(40*20)))); //Formel für Punkte
							System.out.println("Spielfenster übergibt Punkte: "+punkte);
							if (dataBnz.angemeldet)this.dataBnz.siegeCountUp(punkte); //Den Benutzeraccount aktuallisieren
							new InfoWon(this).start(new Stage());
						}
					}
				}
			}
		}
		
	}

	public int punkteBerechnen() {
		int punkte = (int)(20.0*(1.0*bombAmount/(xAxis*yAxis))+(10.0*(1.0*(xAxis*yAxis)/(40*20)))); //Formel für Punkte
		return punkte;
	}


	private boolean ereignis() {
		if (Math.random()*100 < probabilityOfGettingAnEvent){
			return true;
		}else{
			return false;
		}
	}
	

}
