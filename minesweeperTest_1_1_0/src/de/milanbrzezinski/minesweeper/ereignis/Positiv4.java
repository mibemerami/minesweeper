package de.milanbrzezinski.minesweeper.ereignis;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.fenster.SpielFenster;
import de.milanbrzezinski.minesweeper.fenster.info.InfoLost;
import de.milanbrzezinski.minesweeper.sound.Song;
import de.milanbrzezinski.minesweeper.welt.*;

/**
*
* @author  Milan Brzezinski
*/

public class Positiv4 {
	//Fields:
		Label info = new Label();
		Button btnOk = new Button();
		int anzahl = 5;
		String infoText = new String();
		String btnText = new String("O.K.");
		Song sound = new Song();
		
		SpielFenster spiel ;
		
		//Constructors:  ---------------------------------------
		public Positiv4(){
			
		}
		public Positiv4(SpielFenster sf){
			spiel = sf;
		}
		
		public void start (Stage stage){
			stage.setTitle("info");
			stage.initModality(Modality.APPLICATION_MODAL);
			BorderPane border = new BorderPane();
			VBox vbCenter = new VBox(20);
			//HBox hbBtn = new HBox();
			Image image = new Image("gfx/prinzessinMitSchloss0iv_x200_y137.png");
			ImageView ivImage = new ImageView();
			ivImage.setImage(image);
			
			sound.play("snd/taira-komori_angel-chorus1.wav");
			
			this.ermittleAnzahl(spiel);
			this.infoText = "Du machst einen Erholungsurlaub auf dem Schloss\n"
					+ "von Prinzessin Isabel, und fühlst dich wie neu geboren.\n"
					+ "Du erhältst ein extra Leben.\nNeue Anzahl: "+(this.spiel.dataBnz.bnz.leben+1);
			
			info.setText(infoText);
			info.setTextAlignment( TextAlignment.CENTER);
			btnOk.setText(btnText);
			btnOk.setOnAction(e -> {		
				spiel.dataBnz.lebenCountUp();
				sound.stop();
				stage.close();
			});
			
			vbCenter.getChildren().add(info);
			vbCenter.getChildren().add(ivImage);
			vbCenter.getChildren().add(btnOk);
			vbCenter.setAlignment(Pos.CENTER);
			
			border.setCenter(vbCenter);
			
			Scene scene = new Scene(border, 350, 450);
			stage.setScene(scene);
			stage.show();
			
		}
		
		//What to do when the left mouse-button was pressed:
		public void linksKlick(Tile t) {
			
			if(spiel.countLeftClicks < 1){
				spiel.board.setBombs(spiel.bombAmount, spiel.xAxis, spiel.yAxis, t.positionX, t.positionY);
			}
			if(!t.rightClicked){
				if(t.containsBomb){
					t.setText("Explosion");
					new InfoLost(spiel).start(new Stage());
					if(spiel.dataBnz.angemeldet)spiel.dataBnz.verlorenCountUp(); //Den Benutzeraccount aktuallisieren
				}else{
					
					if(spiel.board.amountOfBombsAround(t.positionX, t.positionY)==0){
						spiel.board.clearConnectedTilesWithLabelZero( t.positionX,  t.positionY);	
					}else{
						t.setText(String.valueOf(spiel.board.amountOfBombsAround(t.positionX, t.positionY)));
						
					}
					spiel.board.setTileImage(t, 1);
				}
				t.leftClicked = true;
				spiel.countLeftClicks++;

			}
			
		}

		
		private void ermittleAnzahl(SpielFenster spiel2) {
			//this.anzahl = (int) ((4*(spiel2.bombAmount - spiel2.board.countMarkedBombs(spiel2.xAxis, spiel2.yAxis))/100.0));//anzahl = 4% der noch nicht gesetzten Mienen (geparsed zu int)
			
			//Zählen der noch nicht aufgedeckten Felder ohne Bomben
			int counter = 0;
			for(int i = 0; i < spiel2.yAxis; i++ ){
				for (int j = 0; j < spiel2.xAxis; j++){
					if (!spiel2.board.tileArray[j][i].containsBomb && !spiel2.board.tileArray[j][i].leftClicked) counter++;
				}
			}
			this.anzahl = (int) 5*counter/100; //Von diesen Feldern 5%
			
			if((counter>0)&&(anzahl==0)) anzahl = 1;
			
			System.out.println("anzahl = "+this.anzahl);
		}

}

