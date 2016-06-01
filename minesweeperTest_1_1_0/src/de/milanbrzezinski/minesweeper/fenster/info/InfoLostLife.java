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

public class InfoLostLife extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	String infoText = new String("Du hast ein Leben verloren!");
	String btnText = new String("O.K.");
	
	SpielFenster spiel ;
	Tile t = null;
	
	//Constructors:  ---------------------------------------
	public InfoLostLife(){
		
	}
	public InfoLostLife(SpielFenster sf, Tile tile){
		spiel = sf;
		t= tile;
	}
	
	public void start (Stage stage){
		stage.setTitle("info");
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		
		//border.setStyle("-fx-background-color: #ff4500;");
		infoText = "Du hast ein Leben verloren!\nDu hast noch "+(this.spiel.dataBnz.bnz.leben-1)+" extra Leben übrig.";
		
		info.setText(infoText);
		info.setTextAlignment( TextAlignment.CENTER);
		info.setTextFill(Color.web("#F2370D"));
		btnOk.setText(btnText);
		btnOk.setTextFill(Color.web("#F2370D"));
		btnOk.setOnAction(e -> {
			t.setText("");
			spiel.rechtsKlick(t);
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
