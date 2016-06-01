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

public class Positiv1 {
	//Fields:
		Label info = new Label();
		Button btnOk = new Button();
		int anzahl = 5;
		String infoText = new String();
		String btnText = new String("O.K.");
		
		SpielFenster spiel ;
		
		//Constructors:  ---------------------------------------
		public Positiv1(){
			
		}
		public Positiv1(SpielFenster sf){
			spiel = sf;
		}
		
		public void start (Stage stage){
			stage.setTitle("info");
			stage.initModality(Modality.APPLICATION_MODAL);
			BorderPane border = new BorderPane();
			VBox vbCenter = new VBox(20);
			//HBox hbBtn = new HBox();
			Image image = new Image("gfx/dokumentKarte.png");
			ImageView ivImage = new ImageView();
			ivImage.setImage(image);
			
			Song sound = new Song();			//Sound abspielen
			sound.play("snd/papier-knuellen.wav");
			
			this.ermittleAnzahl(spiel);
			this.infoText = "Du hast Glück, die Schergen Jurisons haben ein paar"
					+ "\nKarten und Pläne liegen lassen. Dadurch kannst du"
					+ "\nden Aufenthaltsort von "+anzahl+" Mienen ausfindig machen.";
			
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
			this.anzahl = (int) ((6*(spiel2.bombAmount - spiel2.board.countMarkedBombs(spiel2.xAxis, spiel2.yAxis))/100.0));//anzahl = 5% der noch nicht gesetzten Mienen (geparsed zu int)
			this.anzahl +=1;
			System.out.println("anzahl = "+this.anzahl);
		}

}
