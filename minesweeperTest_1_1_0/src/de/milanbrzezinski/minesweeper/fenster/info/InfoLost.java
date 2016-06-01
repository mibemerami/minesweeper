package de.milanbrzezinski.minesweeper.fenster.info;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.benutzer.LevelControl;
import de.milanbrzezinski.minesweeper.fenster.*;
import de.milanbrzezinski.minesweeper.sound.Song;

/**
*
* @author  Milan Brzezinski
*/

public class InfoLost extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoTextLost = new String("Du hast leider verloren!\nDu konntest nicht alle Mienen beseitigen, und die "
			+ "Bauern können\nsich noch nicht wieder auf ihre Felder wagen."
			+ "\nAber Prinzessin Isabel dankt dir trotzdem für deinen mutigen Versuch.");
	String btnText = new String("O.K.");
	
	SpielFenster spiel ;
	Song sound = new Song();
	
	//Constructors:  ---------------------------------------
	public InfoLost(){
		
	}
	public InfoLost(SpielFenster sf){
		spiel = sf;
	}
	
	
	
	public void start (Stage stage){
		stage.setTitle("info");
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		
		//border.setStyle("-fx-background-color: #ff4500;");
		
		if(spiel.dataBnz.angemeldet) LevelControl.setLevelBackToCheckpoint();
		
		sound.play("snd/forestInferno_with_dramatic_chorus.wav"); //Sound abspielen
		
		info.setText(infoTextLost);
		info.setTextAlignment( TextAlignment.CENTER);
		info.setTextFill(Color.web("#F2370D"));
		btnOk.setText(btnText);
		btnOk.setTextFill(Color.web("#F2370D"));
		btnOk.setOnAction(e -> {		
			try {
				//new StartFenster().start(new Stage());
				ControlSession.startStartFenster();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			sound.stop();
			spiel.stage.close();
			stage.close();
		});
		
		vbCenter.getChildren().add(info);
		vbCenter.getChildren().add(btnOk);
		vbCenter.setAlignment(Pos.CENTER);
		
		border.setCenter(vbCenter);
		
		Scene scene = new Scene(border, 400, 250);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
