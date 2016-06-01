package de.milanbrzezinski.minesweeper.benutzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.Stage;

/**
*
* @author  Milan Brzezinski
*/

public class Benutzer {
	//Fields:
	public String name;
	public int anzSpiele = 0;
	public int anzSiege = 0;
	public int anzVerloren = 0;
	public int punkte = 0;
	public int leben = 0;
	public int level = 0;
	
	//Constructors:
	public Benutzer(String eingabe) throws IOException{
		this.name = eingabe;
		this.datenLaden();
		System.out.println("Ein Benutzer wurde anglegt: "+this.name);
		
	}
	
	
	private void datenLaden() throws IOException{
		
		FileReader fr = new FileReader(this.name+".txt");
		BufferedReader br = new BufferedReader(fr);
		System.out.println("Benutzer.datenladen() wurde aufgerufen");
		String zeile ="";
		while ((zeile = br.readLine())!= null){
			
			System.out.println("Schleife betreten");
			
			if (zeile.equals("anzSpiele{")){
				zeile = br.readLine();
				System.out.println("anzSpiele in Datei gefunden: "+zeile); 
				anzSpiele = Integer.parseInt(zeile);
				System.out.println("geparsed: "+anzSpiele);
			}
			if (zeile.equals("anzSiege{")){
				zeile = br.readLine();
				anzSiege = Integer.parseInt(zeile);
			}
			if (zeile.equals("anzVerloren{")){
				zeile = br.readLine();
				anzVerloren = Integer.parseInt(zeile);
			}
			if (zeile.equals("punkte{")){
				zeile = br.readLine();
				punkte = Integer.parseInt(zeile);
			}
			if (zeile.equals("leben{")){
				zeile = br.readLine();
				leben = Integer.parseInt(zeile);
			}
			if (zeile.equals("level{")){
				zeile = br.readLine();
				level = Integer.parseInt(zeile);
			}
			
		}
		br.close();
		
	}
	public void datenSchreiben() throws IOException{
		File f = new File(this.name+".txt");
		if(f.exists()) f.delete();
		
		FileWriter fw = new FileWriter(this.name+".txt");
	    BufferedWriter bw = new BufferedWriter(fw);
	    
	    bw.write(this.name+":");
	    bw.newLine();
	    bw.newLine();
	    bw.write("anzSpiele{");
	    bw.newLine();
	    bw.write(String.valueOf(this.anzSpiele));
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("anzSiege{");
	    bw.newLine();
	    bw.write(String.valueOf(this.anzSiege));
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("anzVerloren{");
	    bw.newLine();
	    bw.write(String.valueOf(this.anzVerloren));
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("punkte{");
	    bw.newLine();
	    bw.write(String.valueOf(this.punkte));
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("leben{");
	    bw.newLine();
	    bw.write(String.valueOf(this.leben));
	    bw.newLine();
	    bw.write("}");
	    bw.newLine();
	    bw.write("level{");
	    bw.newLine();
	    bw.write(String.valueOf(this.level));
	    bw.newLine();
	    bw.write("}");
	    
	    bw.close();
		
	}

}
