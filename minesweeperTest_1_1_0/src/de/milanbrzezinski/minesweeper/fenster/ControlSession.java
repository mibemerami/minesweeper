package de.milanbrzezinski.minesweeper.fenster;

/**
*
* @author  Milan Brzezinski
*/

import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.benutzer.*;
import de.milanbrzezinski.minesweeper.sound.ZipArchiveExtractor;

public class ControlSession extends javafx.application.Application {
	//Fields:
	public static StartFenster startFenster = new StartFenster();
	public static float lautstaerke = -20.0f;
	public static boolean lautstaerkeAus = false;
	public static String version = "V-1-1-0";

	
	
	public static void main(String[] args) {
		new ZipArchiveExtractor().checkForSoundfiles("snd");
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		startFenster.start(new Stage());
		
	}
	
	public static void startStartFenster(){
		try {
			startFenster.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
