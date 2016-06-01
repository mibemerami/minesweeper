package de.milanbrzezinski.minesweeper.fenster.info;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.fenster.*;
import de.milanbrzezinski.minesweeper.sound.*;
import de.milanbrzezinski.minesweeper.benutzer.*;

/**
*
* @author  Milan Brzezinski
*/

public class InfoWon extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoTextWon = new String("Herzlichen Glückwunsch!\nDu hast alle Mienen beseitigt, und die "
			+ "Bauern\nkönnen nun wieder auf ihre Felder.\nPrinzessin Isabel dankt dir im Namen des\n"
			+ "gesamten artolinischen Volkes.\nPunkte: ");
	String btnText = new String("O.K.");
	int punkte = 0;
	Song sound = new Song();

	
	SpielFenster spiel ;
	
	//Constructors:  ---------------------------------------
	public InfoWon(){
		
	}
	public InfoWon(SpielFenster sf){
		spiel = sf;
		punkte = sf.punkteBerechnen();
	}
	
	public void start (Stage stage){
		stage.setTitle("info");
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		info.setContentDisplay(ContentDisplay.CENTER);
		
		
		sound.play("snd/yells_with_firework.wav"); //Sound abspielen
		
		Image image = new Image("gfx/feuerwerk1i.png");
		ImageView iv = new ImageView(image);
		iv.setOpacity(0.4);
		info.setGraphic(iv);
				
		if (ControlSession.startFenster.dataBnz.angemeldet){
			info.setText(infoTextWon + punkte + "\nGesamtpunkte: "+ ControlSession.startFenster.dataBnz.bnz.punkte);
			if(spiel.level > 0){
				info.setText(infoTextWon + punkte + "\nGesamtpunkte: "+ ControlSession.startFenster.dataBnz.bnz.punkte+
						"\nLevel "+ControlSession.startFenster.dataBnz.bnz.level+" erreicht.");
			}
		}else{
			info.setText(infoTextWon + punkte);
		}
		info.setTextAlignment( TextAlignment.CENTER);
		info.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));
		info.setTextFill(Color.web("#1936db"));
		/*info.setStyle("-fx-padding: 0.5em;");
		info.setStyle("-fx-color: #ff0;");
		info.setStyle("-fx-background-color: #00f;");
		info.setStyle("-fx-border: 10px solid #000;");*/
		//info.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		
		
		btnOk.setText(btnText);
		btnOk.setTextFill(Color.web("1936db"));
		btnOk.setOnAction(e -> {		
			if(spiel.level > 0){
				if(spiel.level==70){
					new AlleLevelGeschaft().start(new Stage());
				}else{
					ControlSession.startFenster.adventureReaction(new Stage());
				}
			}else{
				try {
					ControlSession.startStartFenster();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			sound.stop();  //sound beenden
			spiel.stage.close();
			stage.close();
		});
		
		vbCenter.getChildren().add(info);
		vbCenter.getChildren().add(btnOk);
		vbCenter.setAlignment(Pos.CENTER);
		
		border.setCenter(vbCenter);
		
		Scene scene = new Scene(border, 350, 300);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
