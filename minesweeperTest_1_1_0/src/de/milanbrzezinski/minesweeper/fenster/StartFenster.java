package de.milanbrzezinski.minesweeper.fenster;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.benutzer.*;
import de.milanbrzezinski.minesweeper.fenster.info.InfoStatus;
import de.milanbrzezinski.minesweeper.fenster.info.InfoVersion;
import de.milanbrzezinski.minesweeper.sound.Song;

/**
*
* @author  Milan Brzezinski
*/

public class StartFenster extends javafx.application.Application {
	//Fields:
	Label titel = new Label();  
	public Label info = new Label();
	public String textForInfo = "niemand \nangemeldet";
	
	Button btnQuickGame = new Button();
	Button btnAdvMode = new Button();
	Button btnAnmelden = new Button();
	
	public DataBenutzer dataBnz = new DataBenutzer();
	Einstellungen einstellungenFenster;// = new Einstellungen(dataBnz);
	

	

	

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("MineSweeper "+ControlSession.version+" - Startfenster");
		
		MenuBar menuBar = new MenuBar();
		Menu menuGame = new Menu("Game");
		MenuItem gameQuit = new MenuItem("quit");
		Menu menuSound = new Menu("Sound");
		MenuItem soundLaut = new MenuItem("laut");
		MenuItem soundMittel = new MenuItem("mittel");
		MenuItem soundLeise = new MenuItem("leise");
		MenuItem soundAus = new MenuItem("Ton an/aus");
		Menu menuInfo = new Menu("Info");
		MenuItem infoVersion = new MenuItem("Version");
		
		menuBar.getMenus().add(menuGame);
		menuGame.getItems().addAll(gameQuit);
		menuBar.getMenus().add(menuSound);
		menuSound.getItems().addAll(soundLaut, soundMittel, soundLeise, soundAus);
		menuBar.getMenus().add(menuInfo);
		menuInfo.getItems().addAll(infoVersion);
		
		
		//Den Kopfbereich festlegen:
		
		BorderPane border = new BorderPane();
		VBox top = new VBox();
		HBox topArea = new  HBox(10);

		
		topArea.setPadding(new Insets(25, 25, 25, 25));
		
		top.getChildren().add(menuBar);
		top.getChildren().add(topArea);
		
		
		titel.setPrefWidth(300.0);
		titel.setAlignment(Pos.CENTER);
		titel.setText("Minesweeper");
		titel.setFont(Font.font("Tomaha", FontWeight.NORMAL, 30));
				
		info.setAlignment(Pos.CENTER);
		info.setPrefWidth(100);
		info.setText(textForInfo);
		info.setTextFill(Color.web("#aaaaaa"));
		info.setTextAlignment(TextAlignment.CENTER);
		
		topArea.getChildren().add(titel);
		topArea.getChildren().add(info);
		
		topArea.setAlignment(Pos.CENTER);
		
		//Den zentralen Bereich festlegen:
		VBox centerBox = new VBox(10);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setPadding(new Insets(25, 25, 25, 25));
		
		
		btnQuickGame.setText("Ein Spiel starten");
		btnQuickGame.setPrefSize(160, 60);
		
		btnAdvMode.setText("Spieler");
		btnAdvMode.setPrefSize(160, 60);
		
		btnAnmelden.setText("Anmelden");
		btnAnmelden.setPrefSize(160, 60);
		
		centerBox.getChildren().add(btnQuickGame);
		centerBox.getChildren().add(btnAdvMode);
		centerBox.getChildren().add(btnAnmelden);
		
		this.defineReactions(stage, soundLaut, soundMittel, soundLeise, soundAus, infoVersion, gameQuit);
		//Song.play();
		
//		border.setTop(menuBar);
		border.setCenter(centerBox);
		border.setTop(top);
		
		Scene scene = new Scene(border, 410, 600);
		
		stage.setScene(scene);
		
		
		stage.show();
		
	}

	private void defineReactions(Stage stage, MenuItem soundLaut, MenuItem soundMittel, MenuItem soundLeise, MenuItem soundAus, MenuItem infoVersion, MenuItem gameQuit) {
		
		btnQuickGame.setOnAction(e -> {
			this.quickgameReaction(stage);
		});
		
		btnAdvMode.setOnAction(e ->{ 
			if(dataBnz.angemeldet) {
				this.adventureReaction(stage);
			}
			System.out.println("startfenster.dataBnz.angemeldet: "+dataBnz.angemeldet);});
		
		btnAnmelden.setOnAction(e -> {
			this.anmeldenReaction(stage);
		});
		
		info.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY){
				this.anmeldenReaction(stage);
			}
		});
		
		this.setMenuReactions(soundLaut, soundMittel, soundLeise, soundAus, infoVersion, gameQuit, stage);
		
	}
	
	private void setMenuReactions(MenuItem soundLaut, MenuItem soundMittel, MenuItem soundLeise, MenuItem soundAus, MenuItem infoVersion, MenuItem gameQuit, Stage stage) {
		gameQuit.setOnAction(e -> {
			stage.close();
		});
		soundLaut.setOnAction(e -> {
			ControlSession.lautstaerke = 0.0f;
			System.out.println("soundLaut wurde betätigt");
		});
		soundMittel.setOnAction(e -> {
			ControlSession.lautstaerke = -20.0f;
			System.out.println("ControlSession.lautstaerke: "+ControlSession.lautstaerke);
		});
		soundLeise.setOnAction(e -> {
			ControlSession.lautstaerke = -40.0f;
			System.out.println("ControlSession.lautstaerke: "+ControlSession.lautstaerke);
			
		});
		soundAus.setOnAction(e -> {
			if(ControlSession.lautstaerkeAus==false){
				ControlSession.lautstaerkeAus = true;
			}else{
				ControlSession.lautstaerkeAus = false;
			}
		});

		infoVersion.setOnAction(e -> {
			new InfoVersion().start(new Stage());
		});
		
	}
	
	public void quickgameReaction(Stage stage){
		einstellungenFenster = new Einstellungen(dataBnz);
		einstellungenFenster.start(new Stage()); 
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		stage.close();
					
	}
	
	public void adventureReaction(Stage stage){	
		if(dataBnz.angemeldet) {
			new SpielerInfo(this.dataBnz).start(new Stage());
			Song sound = new Song();
			sound.play("snd/gamesound-bertrof0.wav");
			stage.close();
		
			System.out.println("startfenster.dataBnz.angemeldet: "+dataBnz.angemeldet);
		}
	}
	
	public void anmeldenReaction(Stage stage){
		dataBnz.anmeldeFenster();
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		
		
	}


}
