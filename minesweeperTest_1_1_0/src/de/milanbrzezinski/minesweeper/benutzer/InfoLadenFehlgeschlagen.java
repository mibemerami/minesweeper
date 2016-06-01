package de.milanbrzezinski.minesweeper.benutzer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class InfoLadenFehlgeschlagen extends javafx.application.Application {
	//Fields:
	Label info = new Label();
	Button btnOk = new Button();
	public String infoText = new String("Die Benutzerdaten konnten\nnicht geladen werden.");
	public String btnText = new String("O.K.");
	
	public void start (Stage stage){
		stage.setTitle("info");
		BorderPane border = new BorderPane();
		VBox vbCenter = new VBox(20);
		//HBox hbBtn = new HBox();
		
		info.setText(infoText);
		info.setTextAlignment( TextAlignment.CENTER);
		btnOk.setText(btnText);
		btnOk.setOnAction(e -> { stage.close(); });
		
		vbCenter.getChildren().add(info);
		vbCenter.getChildren().add(btnOk);
		vbCenter.setAlignment(Pos.CENTER);
		
		border.setCenter(vbCenter);
		
		Scene scene = new Scene(border, 250, 150);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
}
