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

public class Negativ2 {
	//Fields:
		Label info = new Label();
		Button btnOk = new Button();
		int anzahl = 5;
		String infoText = new String();
		String btnText = new String("O.K.");
		Song sound = new Song();
		
		SpielFenster spiel ;
		
		//Constructors:  ---------------------------------------
		public Negativ2(){
			
		}
		public Negativ2(SpielFenster sf){
			spiel = sf;
		}
		
		public void start (Stage stage){
			stage.setTitle("info");
			stage.initModality(Modality.APPLICATION_MODAL);
			BorderPane border = new BorderPane();
			VBox vbCenter = new VBox(20);
			//HBox hbBtn = new HBox();
			Image image = new Image("gfx/jurisonSturm_x200_y145.png");
			ImageView ivImage = new ImageView();
			ivImage.setImage(image);
					
			sound.play("snd/windUndLachen.wav"); //Sound abspielen
			
			this.ermittleAnzahl(spiel);
			this.infoText = "Der böse Zauberer Jurison ist verärgert über deine Fortschritte,\n"
					+ "er schickt ein Gewitter."
					+ "\nDurch Wind und Regen werden viele deiner sicheren Wege zerstört. "
							+ "\n"+anzahl+" Felder werden umgedreht.";
			
			info.setText(infoText);
			info.setTextAlignment( TextAlignment.CENTER);
			btnOk.setText(btnText);
			btnOk.setOnAction(e -> {		
				int count = 0;
				while(count < anzahl){
					int x = (int) (Math.random()*spiel.xAxis);
					int y = (int) (Math.random()*spiel.yAxis);
					Tile t = spiel.board.tileArray[x][y];
					if((t.leftClicked)&&(!t.rightClicked)&&(!t.containsBomb)){
						t.leftClicked = false;
						t.rightClicked = false;
						t.setText("");
						spiel.board.setTileImage(t, 0);
						count++;
					}
				}
				sound.stop();
				stage.close();
			});
			
			vbCenter.getChildren().add(info);
			vbCenter.getChildren().add(ivImage);
			vbCenter.getChildren().add(btnOk);
			vbCenter.setAlignment(Pos.CENTER);
			
			border.setCenter(vbCenter);
			
			Scene scene = new Scene(border, 350, 400);
			stage.setScene(scene);
			stage.show();
			
		}
		
		private void ermittleAnzahl(SpielFenster spiel2) {
			
			
			//Zählen der aufgedeckten Felder ohne Bomben
			int counter = 0;
			for(int i = 0; i < spiel2.yAxis; i++ ){
				for (int j = 0; j < spiel2.xAxis; j++){
					if (!spiel2.board.tileArray[j][i].containsBomb && spiel2.board.tileArray[j][i].leftClicked) counter++;
				}
			}
			this.anzahl = (int) 50*counter/100; //Von diesen Feldern 50%
			
			//this.anzahl +=1;
			System.out.println("anzahl = "+this.anzahl);
		}

}

