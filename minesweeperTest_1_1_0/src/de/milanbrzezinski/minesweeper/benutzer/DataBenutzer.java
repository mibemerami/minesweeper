package de.milanbrzezinski.minesweeper.benutzer;

import java.io.IOException;

import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class DataBenutzer {
	//Fields:
	public boolean angemeldet = false;
	//AnmeldeFenster af = new AnmeldeFenster(this);
	public Benutzer bnz;
	public SpielerInfo si;
	int sitzungSpiele = 0; //Anzahl der Spiele, die wärend dieser Sitzung gespielt wurden, also solange das Programm aufgerufen ist
	int sitzungSiege = 0;
	int sitzungVerloren = 0;
	int sitzungLeben = 0;
	int sitzungLevel = 0;
	Stage stage = new Stage();
	
	
	
	
	//Methods:
	
	public void spieleCountUp(){
		this.sitzungSpiele++;
		bnz.anzSpiele++;
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void siegeCountUp(int punkte){
		System.out.println("DataBenutzer, sitzungSiegeCountUp wurde aufgerufen, Punkte: "+punkte);
		this.sitzungSiege++;
		bnz.anzSiege++;
		bnz.punkte += punkte;
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void verlorenCountUp(){
		this.sitzungVerloren++;
		bnz.anzVerloren++;
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void lebenCountDown(){
		this.sitzungLeben--;
		if(bnz.leben > 0){
			bnz.leben--;
		}
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void lebenCountUp(){
		this.sitzungLeben++;
		bnz.leben++;
		
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void levelCountUp(){
		this.sitzungLevel++;
		bnz.level++;
		
		try {
			bnz.datenSchreiben();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void anmeldeFenster(){
		try {
			new AnmeldeFenster(this).start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ladeBenutzer(String name){
		this.angemeldet = true;
		try {
			this.bnz = new Benutzer(name);
		} catch (IOException e) {
			new InfoLadenFehlgeschlagen().start(new Stage());
			this.angemeldet = false;
			e.printStackTrace();
			
		}
		
	}
	
}
