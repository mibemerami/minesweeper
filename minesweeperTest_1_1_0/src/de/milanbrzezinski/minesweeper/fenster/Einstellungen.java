package de.milanbrzezinski.minesweeper.fenster;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.fenster.info.*;
import de.milanbrzezinski.minesweeper.fenster.*;
import de.milanbrzezinski.minesweeper.sound.Song;
import de.milanbrzezinski.minesweeper.benutzer.*;

/**
*
* @author  Milan Brzezinski
*/

public class Einstellungen extends javafx.application.Application{
	
	//Fields:
	DataBenutzer dataBnz;	
	SpielFenster spiel; //= new SpielFenster(dataBnz);
	InfoWindow infoWindow = new InfoWindow();
	
	Text windowTitel = new Text("Einstellungen:");
	
	Button btnLeicht = new Button("leicht");
	Button btnMittel = new Button("mittel");
	Button btnSchwer = new Button("schwer");
	
	Label titleWidth = new Label("Die Anzahl der Felder auf \nder X-Achse setzen:"); 
	Button minusW = new Button("-");
	Button plusW = new Button("+");
	Slider sldWidth = new Slider(1, 40, 9);
	Label infoWidth = new Label("9");
	
	Label titleHeight = new Label("Die Anzahl der Felder auf \nder Y-Achse setzen:");
	Button minusH = new Button("-");
	Button plusH = new Button("+");
	Slider sldHeight = new Slider(1, 20, 9);
	Label infoHeight = new Label("9");
	
	Label titleAmountBombs = new Label("Die Anzahl der Bomben setzen:");
	Button minusB = new Button("-");
	Button plusB = new Button("+");
	Slider sldAmountBombs = new Slider(1, 200, 10);
	Label infoAmountBombs = new Label("10");
	
	Label titlePlayWithEvents = new Label("Mit Events spielen:");
	ToggleButton tbEvents = new ToggleButton("Yes");
	boolean playWithEvents = true;
	
	Button btnBack = new Button("Zurück");
	Button btnSubmit = new Button("Starten");
	

	
	//Constructors:
	public Einstellungen(DataBenutzer dbnz){
		dataBnz = dbnz;
		spiel = new SpielFenster(dataBnz);
	}
	
	
	public void start (Stage stage){
				
		stage.setTitle("MineSweeper "+ControlSession.version+" - Einstellungen");
		
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10.0);
		grid.setVgap(10.0);
		grid.setPadding(new Insets(25, 25, 25, 25));
		HBox hbBtnNiveau = new HBox();
		hbBtnNiveau.setAlignment(Pos.BASELINE_CENTER);
		hbBtnNiveau.setSpacing(10.0);
		HBox hbBtnBack = new HBox();
		hbBtnBack.setAlignment(Pos.BASELINE_LEFT);
		HBox hbBtnSubmit = new HBox();
		hbBtnSubmit.setAlignment(Pos.BASELINE_RIGHT);
		HBox hbTbEvents = new HBox();
		hbTbEvents.setAlignment(Pos.BASELINE_RIGHT);
		
		
		windowTitel.setFont(Font.font("Tomaha",FontWeight.NORMAL, 40));
		windowTitel.setFill(Color.web("#555555"));
		minusW.setPrefWidth(30);
		plusW.setPrefWidth(30);
		minusH.setPrefWidth(30);
		plusH.setPrefWidth(30);
		minusB.setPrefWidth(30);
		plusB.setPrefWidth(30);
		
		//The elements reactions:
		
		btnLeicht.setOnAction(e -> {
			sldWidth.setValue(9);
			sldHeight.setValue(9);
			sldAmountBombs.setValue(10);
		});
		btnMittel.setOnAction(e -> {
			sldWidth.setValue(16);
			sldHeight.setValue(16);
			sldAmountBombs.setValue(40);
		});		
		btnSchwer.setOnAction(e -> {
			sldWidth.setValue(30);
			sldHeight.setValue(16);
			sldAmountBombs.setValue(99);
		});		
		
		minusW.setOnAction(e -> {sldWidth.setValue(sldWidth.getValue()-1);});
		plusW.setOnAction(e -> {sldWidth.setValue(sldWidth.getValue()+1);});
		
		minusH.setOnAction(e -> {sldHeight.setValue(sldHeight.getValue()-1);});
		plusH.setOnAction(e -> {sldHeight.setValue(sldHeight.getValue()+1);});
		
		minusB.setOnAction(e -> {sldAmountBombs.setValue(sldAmountBombs.getValue()-1);});
		plusB.setOnAction(e -> {sldAmountBombs.setValue(sldAmountBombs.getValue()+1);});
		
		sldWidth.valueProperty().addListener(e -> { infoWidth.setText(String.valueOf((int)sldWidth.getValue())); }); 
		sldHeight.valueProperty().addListener(e -> { infoHeight.setText(String.valueOf((int)sldHeight.getValue())); });
		sldAmountBombs.valueProperty().addListener(e -> { infoAmountBombs.setText(String.valueOf((int)sldAmountBombs.getValue())); });
		
		tbEvents.setOnAction(e -> {
			if (this.playWithEvents){
				this.playWithEvents = false;
				tbEvents.setText("No");
			}else{
				this.playWithEvents = true;
				tbEvents.setText("Yes");
			}
		});
		
		btnBack.setOnAction(e -> {
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
		});
		
		btnSubmit.setDefaultButton(true);
		//What happens when submit-button is pressed:
		btnSubmit.setOnAction(e -> { 
									Song sound = new Song();
									sound.play("snd/gamesound-bertrof0.wav");
									spiel.xAxis = (int)sldWidth.getValue();
									spiel.yAxis = (int)sldHeight.getValue();
									spiel.playWithEvents = this.playWithEvents;
									spiel.bombAmount = (int)sldAmountBombs.getValue();
									if((int)sldAmountBombs.getValue() > (int)(sldWidth.getValue()*sldHeight.getValue())){
										infoWindow.infoText = ("Die Anzahl der Bomben übersteigt \ndie "
												+ "Anzahl der Felder ("+(int)(sldWidth.getValue()*sldHeight.getValue())+"). "
												+ " \nBitte neu setzen.");
										infoWindow.btnText = "O.k.";
										infoWindow.start(new Stage() );
									}else{
										
										try {
											spiel.start(new Stage());
											stage.close();
										
										} catch (Exception e1) {
											e1.printStackTrace();
										}
										
									}
									});
		
		
		//Der Teil, wo alles zum Layout und dann zum Fenster hinzugefügt wird:
		border.setTop(windowTitel);
		BorderPane.setAlignment(border.getTop(), Pos.CENTER);
		
		hbBtnNiveau.getChildren().add(btnLeicht);
		hbBtnNiveau.getChildren().add(btnMittel);
		hbBtnNiveau.getChildren().add(btnSchwer);
		grid.add(hbBtnNiveau, 0, 0, 6, 1);
		
		grid.add(titleWidth, 0, 1);
		grid.add(minusW, 1, 1);
		grid.add(plusW, 2, 1);
		grid.add(sldWidth, 3, 1, 2, 1);
		grid.add(infoWidth, 5, 1);
		
		grid.add(titleHeight, 0, 2);
		grid.add(minusH, 1, 2);
		grid.add(plusH, 2, 2);
		grid.add(sldHeight, 3, 2, 2, 1);
		grid.add(infoHeight, 5, 2);
		
		grid.add(titleAmountBombs, 0, 3);
		grid.add(minusB, 1, 3);
		grid.add(plusB, 2, 3);
		grid.add(sldAmountBombs, 3, 3, 2, 1);
		grid.add(infoAmountBombs, 5, 3);
		
		grid.add(titlePlayWithEvents, 0, 4);
		hbTbEvents.getChildren().add(tbEvents);
		grid.add(hbTbEvents, 4, 4, 2, 1);
		
		hbBtnBack.getChildren().add(btnBack);
		grid.add(hbBtnBack, 0, 6, 2, 1 );
		
		hbBtnSubmit.getChildren().add(btnSubmit);
		grid.add(hbBtnSubmit, 4, 6, 2, 1 );
		
		border.setCenter(grid);
		Scene scene = new Scene(border, 500, 400);
				
		stage.setScene(scene);
		//grid.setGridLinesVisible(true);
		stage.show();
	}

}
