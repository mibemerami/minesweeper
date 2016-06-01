package de.milanbrzezinski.minesweeper.benutzer;

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

/**
*
* @author  Milan Brzezinski
*/

public class AlleLevelGeschaft extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoText = new String(
			"Herzlichen Glückwunsch, du hast es geschafft!\n"
			+ "Dein Mut und dein Verstand haben Jurisons Bann gebrochen und Artolinia erlöst.\n"
			+ "Die Bevölkerung kann nun wieder in Frieden und Wohlstand leben. Die bezaubernde\n"
			+ "Prinzessin Isabel erweist dir die Gunst einer Umarmung.\n"
			+ "\n"
			+ "Du wirst immer einen Platz in ihrem Herzen haben. Und im ganzen Land\n"
			+ "wird man dir auf ewig dankbar sein.\n"
			+ "Du wirst mit Ländereien, Goldschätzen, Orden und Titeln überhäuft.\n"
			+ "\n"
			+ "Du bist glücklich und stolz, dies ist die schönste Zeit deines Lebens.");
	String btnText = new String("Juhuu");
	int punkte = 0;
	Song sound = new Song();

	
	SpielFenster spiel ;
	
	//Constructors:  ---------------------------------------
	public AlleLevelGeschaft(){
		
	}
	public AlleLevelGeschaft(SpielFenster sf){
		spiel = sf;

	}
	
	public void start (Stage stage){
		stage.setTitle("info");
		//stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		info.setContentDisplay(ContentDisplay.CENTER);
		
		//sound.play("snd/yells_with_firework.wav"); //Sound abspielen
		
		info.setText(infoText);
		
		Image image = new Image("gfx/prinzessinSchlossLandschaftR.png");
		ImageView iv = new ImageView(image);
		iv.setOpacity(0.6);
		info.setGraphic(iv);
				

		info.setTextAlignment( TextAlignment.CENTER);
		info.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));
		info.setTextFill(Color.web("#1936db"));
		/*info.setStyle("-fx-padding: 0.5em;");
		info.setStyle("-fx-color: #ff0;");
		info.setStyle("-fx-background-color: #00f;");
		info.setStyle("-fx-border: 10px solid #000;");*/
		//info.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		
		
		btnOk.setText(btnText);
		btnOk.setFont(Font.font("Tomaha", FontWeight.BOLD, 15));
		btnOk.setTextFill(Color.web("1936db"));
		btnOk.setOnAction(e -> {		
			/*
			try {
				ControlSession.startStartFenster();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			sound.stop();  //sound beenden
			spiel.stage.close();
			*/
			ControlSession.startFenster.adventureReaction(new Stage());
			stage.close();
		});
		
		vbCenter.getChildren().add(info);
		vbCenter.getChildren().add(btnOk);
		vbCenter.setAlignment(Pos.CENTER);
		
		border.setCenter(vbCenter);
		
		Scene scene = new Scene(border, 750, 450);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
