package de.milanbrzezinski.minesweeper.ereignis;

import javafx.stage.Stage;
import de.milanbrzezinski.minesweeper.fenster.*;

/**
*
* @author  Milan Brzezinski
*/

public class ControlEreignis {
	static int ereignisseGesamt = 100;
	static int probPositiv1 = 25;  	//Karten von Jurison
	static int probPositiv2 = 25;  	//Prinzessin Isabel, Besuch
	static int probPositiv3 = 25;	//Raabe
	static int probPositiv4 = 5;	//extra Leben
	static int probNegativ1 = 15;	//Blitze zerstören Schilder
	static int probNegativ2 = 5;	//Sturm dreht Felder zurück
	
	/*
	public static void go(SpielFenster sf){
		int choose = (int) (Math.random()*anzahlEreignisse);
		switch(choose){
		case 0 :
			new Positiv1(sf).start(new Stage());
			break;
		case 1 :
			new Positiv2(sf).start(new Stage());
			break;
		case 2 : 
			new Positiv3(sf).start(new Stage());
			break;
		case 3 : 
			new Negativ1(sf).start(new Stage());
			break;
		case 4 : 
			new Positiv4(sf).start(new Stage());
			break;
		case 5 : 
			new Negativ2(sf).start(new Stage());
		}
	}
	*/
	public static void go(SpielFenster sf){
		if(sf.playWithEvents){
			int choose = (int) (Math.random()*ereignisseGesamt);
			if((choose>=0)&&(choose<probPositiv1))
				new Positiv1(sf).start(new Stage());
			if((choose>=probPositiv1)&&(choose<probPositiv1+probPositiv2))
				new Positiv2(sf).start(new Stage());
			if((choose>=probPositiv1+probPositiv2)&&(choose<probPositiv1+probPositiv2+probPositiv3))
				new Positiv3(sf).start(new Stage());
			if((choose>=probPositiv1+probPositiv2+probPositiv3)&&(choose<probPositiv1+probPositiv2+probPositiv3+probPositiv4))
				new Positiv4(sf).start(new Stage());
			if((choose>=probPositiv1+probPositiv2+probPositiv3+probPositiv4)&&(choose<probPositiv1+probPositiv2+probPositiv3+probPositiv4+probNegativ1))
				new Negativ1(sf).start(new Stage());
			if((choose>=probPositiv1+probPositiv2+probPositiv3+probPositiv4+probNegativ1)&&(choose<probPositiv1+probPositiv2+probPositiv3+probPositiv4+probNegativ1+probNegativ2))
				new Negativ2(sf).start(new Stage());
		}
		
	}
	public static void go(SpielFenster sf,int[] einstellungen){
		if(sf.playWithEvents){
			int choose = (int) (Math.random()*ereignisseGesamt);
			if((choose>=0)&&(choose<einstellungen[0]))
				new Positiv1(sf).start(new Stage());
			if((choose>=einstellungen[0])&&(choose<einstellungen[0]+einstellungen[1]))
				new Positiv2(sf).start(new Stage());
			if((choose>=einstellungen[0]+einstellungen[1])&&(choose<einstellungen[0]+einstellungen[1]+einstellungen[2]))
				new Positiv3(sf).start(new Stage());
			if((choose>=einstellungen[0]+einstellungen[1]+einstellungen[2])&&(choose<einstellungen[0]+einstellungen[1]+einstellungen[2]+einstellungen[3]))
				new Positiv4(sf).start(new Stage());
			if((choose>=einstellungen[0]+einstellungen[1]+einstellungen[2]+einstellungen[3])&&(choose<einstellungen[0]+einstellungen[1]+einstellungen[2]+einstellungen[3]+einstellungen[4]))
				new Negativ1(sf).start(new Stage());
			if((choose>=einstellungen[0]+einstellungen[1]+einstellungen[2]+einstellungen[3]+einstellungen[4])&&(choose<einstellungen[0]+einstellungen[1]+einstellungen[2]+einstellungen[3]+einstellungen[4]+einstellungen[5]))
				new Negativ2(sf).start(new Stage());
		}
		
	}
}
