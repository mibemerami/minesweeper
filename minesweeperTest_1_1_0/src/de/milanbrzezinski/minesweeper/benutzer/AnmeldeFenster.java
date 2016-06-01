package de.milanbrzezinski.minesweeper.benutzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.milanbrzezinski.minesweeper.fenster.ControlSession;
import de.milanbrzezinski.minesweeper.sound.Song;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class AnmeldeFenster extends javafx.application.Application {
	//Fields:----------------------------------------------------------
	DataBenutzer dataBnz;
	
	BorderPane border = new BorderPane();
	HBox hb1 = new HBox();
	HBox hb2 = new HBox();
	HBox hb3 = new HBox();
	HBox hb4 = new HBox();
	HBox hb5 = new HBox();
	VBox vb1 = new VBox();
	VBox vb2 = new VBox();
	
	Text title = new Text("Benutzer-Verwaltung");
	Label lb = new Label("Name: ");
	TextField namenEingabe = new TextField();
	Button btnAnmelden = new Button("Anmelden");
	Button btnAbmelden = new Button("Abmelden");
	Button btnErstellen = new Button("Erstellen");
	Button btnZurueck = new Button("Zurück");
	Label lbInfo = new Label();
	//--------------------------------------------------------------------------------
	//Constructors:
	public AnmeldeFenster(DataBenutzer dbnz){
		dataBnz = dbnz;
		if(dataBnz.angemeldet) lbInfo.setText("Angemeldet als: "+dataBnz.bnz.name);
	}
	
	//--------------------------------------------------------------------------------
	//Methods:
	@Override
	public void start(Stage stage) throws Exception {
		//Layout Einstellungen:
		stage.setTitle("Benutzer-Verwaltung");
		title.setFont(Font.font("Tomaha",FontWeight.NORMAL, 30));
		btnAnmelden.setPrefWidth(80);
		btnAbmelden.setPrefWidth(80);
		btnErstellen.setPrefWidth(80);
		lbInfo.setTextFill(Color.web("#aaaaaa"));
		hb1.setAlignment(Pos.CENTER);
		hb2.setAlignment(Pos.CENTER);
		hb3.setAlignment(Pos.CENTER);
		hb4.setAlignment(Pos.TOP_CENTER);
		hb5.setAlignment(Pos.CENTER);
		vb1.setAlignment(Pos.CENTER);
		vb2.setAlignment(Pos.CENTER);
		hb2.setSpacing(5);
		hb3.setSpacing(10);
		hb4.setSpacing(10);
		vb1.setSpacing(10);
		vb2.setSpacing(10);
		hb5.setPadding(new Insets(5, 5, 5, 5));
		
		//Aktionen:
		this.setReactions(stage);
		this.btnAnmelden.setDefaultButton(true);
		
		//Layoutmodule in einander fügen:
		hb1.getChildren().add(title);
		hb2.getChildren().addAll(lb, namenEingabe);
		hb3.getChildren().addAll(btnAnmelden, btnAbmelden, btnErstellen);
		hb4.getChildren().add(lbInfo);		
		vb1.getChildren().addAll(hb2, hb3, hb4);
		hb5.getChildren().add(btnZurueck);
		vb2.getChildren().add(hb5);
		
		border.setTop(hb1);
		border.setCenter(vb1);
		border.setBottom(vb2);
		
		Scene scene = new Scene(border, 400, 300);
		stage.setScene(scene);
		stage.show();		
	}
	private void setReactions(Stage st) {
		this.btnAnmelden.setOnAction(e -> {this.anmeldeReaction(st);});
		this.btnErstellen.setOnAction(e -> {
			try {
				this.erstellenReaction();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}});
		this.btnZurueck.setOnAction(e -> {this.zurueckReaction(st);});
		this.btnAbmelden.setOnAction(e-> {this.abmeldeReaktion();});
	}
	
	private void abmeldeReaktion() {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		lbInfo.setText("Spieler \""+ ControlSession.startFenster.dataBnz.bnz.name +"\" wurde abgemeldet");
		ControlSession.startFenster.dataBnz.angemeldet = false;
		ControlSession.startFenster.info.setText("niemand \nangemeldet");
		ControlSession.startFenster.textForInfo = "niemand \nangemeldet";
	
}
	private void zurueckReaction(Stage st) {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		st.close();
		
	}

	private void erstellenReaction() throws IOException {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		FileWriter fw = new FileWriter(this.namenEingabe.getText()+".txt");
	    BufferedWriter bw = new BufferedWriter(fw);
	    
	    System.out.println("erstellenreaction() wurde aufgerufen und bw erstellt");
	    
	    bw.write(this.namenEingabe.getText()+":"); //Der Name des Spielers
	    bw.newLine();
	    bw.newLine();
	    bw.write("anzSpiele{");
	    bw.newLine();
	    bw.write("0");
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("anzSiege{");
	    bw.newLine();
	    bw.write("0");
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("anzVerloren{");
	    bw.newLine();
	    bw.write("0");
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("punkte{");
	    bw.newLine();
	    bw.write("0");
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("leben{");
	    bw.newLine();
	    bw.write("2");
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("level{");
	    bw.newLine();
	    bw.write("0");
	    bw.newLine();
	    bw.write("}");

	    bw.close();
	    
	    lbInfo.setText("Spieler \""+this.namenEingabe.getText()+"\" wurde erstellt.");
		
	}
		
	

	private void anmeldeReaction(Stage st) {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");
		dataBnz.ladeBenutzer(this.namenEingabe.getText());
		ControlSession.startFenster.info.setText("angemeldet:\n"+this.dataBnz.bnz.name);
		ControlSession.startFenster.textForInfo = "angemeldet:\n"+this.dataBnz.bnz.name;
		st.close();
		
	}
	
	
	

}
