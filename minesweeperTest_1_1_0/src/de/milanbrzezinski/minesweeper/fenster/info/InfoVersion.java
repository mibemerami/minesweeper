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
import de.milanbrzezinski.minesweeper.fenster.*;
import de.milanbrzezinski.minesweeper.welt.Tile;

/**
*
* @author  Milan Brzezinski
*/

public class InfoVersion extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoText = new String("Version");
	String btnText = new String("O.K.");
	
	SpielFenster spiel ;
	
	//Constructors:  ---------------------------------------
	public InfoVersion(){
		
	}
	public InfoVersion(SpielFenster sf){
		spiel = sf;
	}
	
	public void start (Stage stage){
		stage.setTitle("info");
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		
		//border.setStyle("-fx-background-color: #ff4500;");
		
		infoText = "Minesweeper:\nVersion\nMineSweeper "+ControlSession.version+"\nBy Milan Brzezinski (Originaly by Microsoft of cause)";
				
		info.setText(infoText);
		info.setTextAlignment( TextAlignment.CENTER);
		//info.setTextFill(Color.web("#F2370D"));
		btnOk.setText(btnText);
		//btnOk.setTextFill(Color.web("#F2370D"));
		btnOk.setOnAction(e -> {
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
