package de.milanbrzezinski.minesweeper.benutzer;

import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.fenster.ControlSession;
import de.milanbrzezinski.minesweeper.fenster.SpielFenster;
import de.milanbrzezinski.minesweeper.sound.Song;

/**
*
* @author  Milan Brzezinski
*/

public class LevelControl {
	//When you lose a game your level will be set back to a checkpoint 
	public static void setLevelBackToCheckpoint(){
		if((ControlSession.startFenster.dataBnz.bnz.level > 0)&&(ControlSession.startFenster.dataBnz.bnz.level < 10))
			ControlSession.startFenster.dataBnz.bnz.level = 0;
		if((ControlSession.startFenster.dataBnz.bnz.level > 10)&&(ControlSession.startFenster.dataBnz.bnz.level < 20))
			ControlSession.startFenster.dataBnz.bnz.level = 10;
		if((ControlSession.startFenster.dataBnz.bnz.level > 20)&&(ControlSession.startFenster.dataBnz.bnz.level < 30))
			ControlSession.startFenster.dataBnz.bnz.level = 20;
		if((ControlSession.startFenster.dataBnz.bnz.level > 30)&&(ControlSession.startFenster.dataBnz.bnz.level < 40))
			ControlSession.startFenster.dataBnz.bnz.level = 30;
		if((ControlSession.startFenster.dataBnz.bnz.level > 40)&&(ControlSession.startFenster.dataBnz.bnz.level < 50))
			ControlSession.startFenster.dataBnz.bnz.level = 40;
		if((ControlSession.startFenster.dataBnz.bnz.level > 50)&&(ControlSession.startFenster.dataBnz.bnz.level < 60))
			ControlSession.startFenster.dataBnz.bnz.level = 50;
		if((ControlSession.startFenster.dataBnz.bnz.level > 60)&&(ControlSession.startFenster.dataBnz.bnz.level < 70))
			ControlSession.startFenster.dataBnz.bnz.level = 60;
		if((ControlSession.startFenster.dataBnz.bnz.level > 70)&&(ControlSession.startFenster.dataBnz.bnz.level < 80))
			ControlSession.startFenster.dataBnz.bnz.level = 70;
		if((ControlSession.startFenster.dataBnz.bnz.level > 80)&&(ControlSession.startFenster.dataBnz.bnz.level < 90))
			ControlSession.startFenster.dataBnz.bnz.level = 80;
	}
	//depending on the level the game window will be started with the corresponding settings
	//level will be count up by one
	//the array gives the probability of the events: positiv1, postitv2, positiv3, positiv4, negativ1, negativ2, in that order
	public static void chooseLevel(Stage stage) {
		Song sound = new Song();
		sound.play("snd/gamesound-bertrof0.wav");

		if (ControlSession.startFenster.dataBnz.bnz.level==0){			
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 9, 9, 10, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{25,25,25,25,0,0}).start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Einleitung().start(new Stage());
			stage.close();
		}
		
		if((ControlSession.startFenster.dataBnz.bnz.level>0)&&(ControlSession.startFenster.dataBnz.bnz.level<=4)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 9, 9, 10, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{25,25,25,25,0,0}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=5)&&(ControlSession.startFenster.dataBnz.bnz.level<=8)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 9, 9, 10, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{22,21,21,21,10,5}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=9)&&(ControlSession.startFenster.dataBnz.bnz.level<=12)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 9, 9, 10, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{24,23,24,14,9,6}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=13)&&(ControlSession.startFenster.dataBnz.bnz.level<=16)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 10, 10, 13, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{23,24,24,12,10,7}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=17)&&(ControlSession.startFenster.dataBnz.bnz.level<=20)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 11, 11, 16, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{21,25,21,12,9,9}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=21)&&(ControlSession.startFenster.dataBnz.bnz.level<=24)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 16, 16, 40, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{21,25,21,12,9,9/*19,25,19,12,13,12*/}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=25)&&(ControlSession.startFenster.dataBnz.bnz.level<=29)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 16, 16, 40, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{19,25,19,12,13,12}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=30)&&(ControlSession.startFenster.dataBnz.bnz.level<=33)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 16, 16, 40, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,25,20,10,13,12}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=34)&&(ControlSession.startFenster.dataBnz.bnz.level<=37)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 17, 17, 46, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,25,20,10,13,12}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=38)&&(ControlSession.startFenster.dataBnz.bnz.level<=40)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 17, 19, 52, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,25,20,10,13,12}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=41)&&(ControlSession.startFenster.dataBnz.bnz.level<=43)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 17, 19, 52, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=44)&&(ControlSession.startFenster.dataBnz.bnz.level<=47)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 24, 17, 70, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=48)&&(ControlSession.startFenster.dataBnz.bnz.level<=50)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 27, 16, 80, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=51)&&(ControlSession.startFenster.dataBnz.bnz.level<=53)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 92, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=54)&&(ControlSession.startFenster.dataBnz.bnz.level<=57)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 92, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=58)&&(ControlSession.startFenster.dataBnz.bnz.level<=60)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 95, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=61)&&(ControlSession.startFenster.dataBnz.bnz.level<=64)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 99, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,23,20,10,14,13}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=65)&&(ControlSession.startFenster.dataBnz.bnz.level<=67)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 99, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,22,20,10,14,14}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if((ControlSession.startFenster.dataBnz.bnz.level>=68)&&(ControlSession.startFenster.dataBnz.bnz.level<=70)){
			try {
				new SpielFenster(ControlSession.startFenster.dataBnz, 30, 16, 100, (ControlSession.startFenster.dataBnz.bnz.level+1), new int[]{20,22,20,10,14,14}).start(new Stage());
				stage.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
