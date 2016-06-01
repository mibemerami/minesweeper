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
import de.milanbrzezinski.minesweeper.sound.Song;
import de.milanbrzezinski.minesweeper.welt.*;

/**
*
* @author  Milan Brzezinski
*/

public class Positiv2 {
	//Fields:
		Label info = new Label();
		Button btnOk = new Button();
		int anzahl = 5;
		String infoText = new String();
		String btnText = new String("O.K.");
		
		SpielFenster spiel ;
		
		//Constructors:  ---------------------------------------
		public Positiv2(){
			
		}
		public Positiv2(SpielFenster sf){
			spiel = sf;
		}
		
		public void start (Stage stage){
			stage.setTitle("info");
			stage.initModality(Modality.APPLICATION_MODAL);
			BorderPane border = new BorderPane();
			VBox vbCenter = new VBox(20);
			//HBox hbBtn = new HBox();
			Image image = new Image("gfx/prinzessin.png");
			ImageView ivImage = new ImageView();
			ivImage.setImage(image);
			
			Song sound = new Song();
			sound.play("snd/fee_harfe_hoch.wav");
			
			this.ermittleAnzahl(spiel);
			this.infoText = "Prinzessin Isabel besucht ihre Helfer bei der Arbeit."
					+ "\nIhre Gegenwart hat dich motiviert, so dass du "+anzahl+" zusätzliche"
							+ "\nMienen ausfindig machen konntest.";
			
			info.setText(infoText);
			info.setTextAlignment( TextAlignment.CENTER);
			btnOk.setText(btnText);
			btnOk.setOnAction(e -> {		
				int count = 0;
				while(count < anzahl){
					int x = (int) (Math.random()*spiel.xAxis);
					int y = (int) (Math.random()*spiel.yAxis);
					Tile t = spiel.board.tileArray[x][y];
					if((!t.leftClicked)&&(!t.rightClicked)&&(t.containsBomb)){
						spiel.rechtsKlick(t);
						count++;
					}
				}
				stage.close();
			});
			
			vbCenter.getChildren().add(info);
			vbCenter.getChildren().add(ivImage);
			vbCenter.getChildren().add(btnOk);
			vbCenter.setAlignment(Pos.CENTER);
			
			border.setCenter(vbCenter);
			
			Scene scene = new Scene(border, 350, 250);
			stage.setScene(scene);
			stage.show();
			
		}
		private void ermittleAnzahl(SpielFenster spiel2) {
			this.anzahl = (int) ((4*(spiel2.bombAmount - spiel2.board.countMarkedBombs(spiel2.xAxis, spiel2.yAxis))/100.0));//anzahl = 4% der noch nicht gesetzten Mienen (geparsed zu int)
			this.anzahl +=1;
			System.out.println("anzahl = "+this.anzahl);
		}

}


