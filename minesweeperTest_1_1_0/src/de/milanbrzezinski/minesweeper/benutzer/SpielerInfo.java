package de.milanbrzezinski.minesweeper.benutzer;

import de.milanbrzezinski.minesweeper.fenster.ControlSession;
import de.milanbrzezinski.minesweeper.fenster.SpielFenster;
import de.milanbrzezinski.minesweeper.sound.Song;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class SpielerInfo extends javafx.application.Application{
	DataBenutzer dataBnz;
	BorderPane border = new BorderPane();
	GridPane grid = new GridPane();
	HBox hb1 = new HBox();
	HBox hb2 = new HBox();
	VBox vb1 = new VBox();
	Label lbName = new Label();
	Button btnZurueck = new Button("Zurück");
	Label lbStatistik = new Label("Statistik:");
	Label lbSpiele = new Label();
	Label lbSiege = new Label();
	Label lbVerloren = new Label();
	Label lbAbgebrochen = new Label();
	Label lbPunkte = new Label();
	Label lbPunkteDurchschnitt = new Label();
	Label lbLeben = new Label();
	Button btnLevel = new Button("Level");
	Label lbDummy1 = new Label("");
	Label lbLevelAktuell = new Label();
	Button btnWeiterSpielen = new Button("Abenteuer fortsetzen");
	
	
	public SpielerInfo(DataBenutzer dbnz){
		this.dataBnz = dbnz;
	}
	
	public void start(Stage stage){
		
		this.grid.setPadding(new Insets(10, 10, 10, 10));
		this.grid.setHgap(5);
		this.grid.setHgap(5);
		hb1.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);
		hb2.setPadding(new Insets(10,10,10,10));
		
		lbName.setText(this.dataBnz.bnz.name);
		lbName.setFont(Font.font("Tomaha", FontWeight.NORMAL, 20));
		lbSpiele.setText("Spiele: "+this.dataBnz.bnz.anzSpiele);
		lbSiege.setText("Gewonnen: "+this.dataBnz.bnz.anzSiege);
		lbVerloren.setText("Verloren: "+this.dataBnz.bnz.anzVerloren);
		lbAbgebrochen.setText("Abgebrochen: "+(this.dataBnz.bnz.anzSpiele-this.dataBnz.bnz.anzSiege-this.dataBnz.bnz.anzVerloren));
		lbPunkte.setText("Punkte: "+this.dataBnz.bnz.punkte);
		if ((this.dataBnz.bnz.anzSiege+this.dataBnz.bnz.anzVerloren)!=0){
			lbPunkteDurchschnitt.setText("Punkte pro Spiel: "+(this.dataBnz.bnz.punkte/(this.dataBnz.bnz.anzSiege+this.dataBnz.bnz.anzVerloren)));
		}else{
			lbPunkteDurchschnitt.setText("Punkte pro Spiel: 0");
		}
		lbLeben.setText("Leben: "+this.dataBnz.bnz.leben);
		
		lbLevelAktuell.setText("Aktuelles Level: "+this.dataBnz.bnz.level+" von 70.");
		if(this.dataBnz.bnz.level == 0) btnWeiterSpielen.setText("Abenteuer beginnen");
		
		
		this.setReactions(stage);

		
		hb1.getChildren().add(lbName);
		hb2.getChildren().add(btnZurueck);
		vb1.getChildren().add(lbStatistik);
		grid.add(lbSpiele, 0, 0);
		grid.add(lbSiege, 0, 1);
		grid.add(lbVerloren, 0, 2);
		grid.add(lbAbgebrochen, 0, 3);
		grid.add(lbPunkte, 0, 4);
		grid.add(lbPunkteDurchschnitt, 0, 5);
		grid.add(lbLeben, 0, 6);
		grid.add(lbDummy1, 0, 7);
		grid.add(lbLevelAktuell, 0, 8);
		grid.add(btnWeiterSpielen, 1, 8);
		
		border.setTop(hb1);
		border.setLeft(vb1);
		border.setBottom(hb2);
		border.setCenter(grid);
		
		Scene scene = new Scene(border, 600, 500);
		stage.setScene(scene);
		stage.show();
	}

	private void setReactions(Stage stage) {
		btnWeiterSpielen.setOnAction(e-> {
			this.setFortsetzenReaction(stage);
		});
		btnZurueck.setOnAction(e -> {
			this.setZurueckReaction(stage);
		});
		
	}

	private void setZurueckReaction(Stage stage) {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		try {
			//new StartFenster().start(new Stage());
			ControlSession.startFenster.start(new Stage());
			stage.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private void setFortsetzenReaction(Stage stage) {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		LevelControl.chooseLevel(stage);

	}
}
