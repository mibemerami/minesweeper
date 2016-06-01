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

public class Einleitung extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoText = new String(
			"Einst war Artolinia ein kleines, aber glückliches Reich.\n"
			+ "Die Bevölkerung lebte in Frieden und Wohlstand, unter der Führung\n"
			+ "der bezaubernden Prinzessin Isabel\n"
			+ "\n"
			+ "Doch irgendwann zog dieses Glück den Neid und die Missgunst eines bösen Zauberers nach sich.\n"
			+ "Jurison schickte seine finstere Armee, doch sein dunkler Plan scheiterte am Mut\n"
			+ "der freiheitsliebenden Bevölkerung. Aus Rache beschwor er einen Fluch, der das Land\n"
			+ "mit magischen Mienen übersäte.\n"
			+ "\n"
			+ "Die Prinzessin ließ in der ganzen Welt nach den klügsten Köpfen aussenden ihr zu helfen.\n"
			+ "Nur wer reinen Herzens und klaren Verstandes ist, kann die Mienen finden, und ihren Bann brechen.\n"
			+ "\n"
			+ "Bist du bereit diese Herausforderung anzunehmen.");
	String btnText = new String("Ja");
	int punkte = 0;
	Song sound = new Song();

	
	SpielFenster spiel ;
	
	//Constructors:  ---------------------------------------
	public Einleitung(){
		
	}
	public Einleitung(SpielFenster sf){
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
