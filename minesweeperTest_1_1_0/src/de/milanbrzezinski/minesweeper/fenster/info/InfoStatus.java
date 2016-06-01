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

public class InfoStatus extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoText = new String("Status");
	String btnText = new String("O.K.");
	
	SpielFenster spiel ;
	
	//Constructors:  ---------------------------------------
	public InfoStatus(){
		
	}
	public InfoStatus(SpielFenster sf){
		spiel = sf;
	}
	
	public void start (Stage stage){
		stage.setTitle("info");
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		
		//border.setStyle("-fx-background-color: #ff4500;");
		
		if(this.spiel.dataBnz.angemeldet){
			infoText = "Status"
					+ "\nDu hast "+(this.spiel.dataBnz.bnz.leben)+" extra Leben.";
			if(this.spiel.level > 0)
				infoText = infoText+"\nAktuelles level: "+(this.spiel.level-1);
		}else{
			infoText = "Status"
					+ "\nDu bist nicht angemeldet.";
		}
				
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
