package de.milanbrzezinski.minesweeper.sound;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import de.milanbrzezinski.minesweeper.fenster.ControlSession;

import java.io.*;
import java.net.URL;

/*
* SoundTest.java
*
* Created on 1. August 2003, 21:06
*/

/**
*
* @author  Milan Brzezinski
*/

public class Song {
	
	//Fields
	Clip clip = null;
	
   /** Constructors */
	public Song() {
	}
   
   
   public void play() {
      
       try{
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/snd/babyshambles_12.wav"));
           AudioFormat af     = audioInputStream.getFormat();
           int size      = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
           byte[] audio       = new byte[size];
           DataLine.Info info      = new DataLine.Info(Clip.class, af, size);
           audioInputStream.read(audio, 0, size);
          
          // for(int i=0; i < 32; i++) {
               Clip clip = (Clip) AudioSystem.getLine(info);
               clip.open(af, audio, 0, size);
               FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            		gainControl.setValue(ControlSession.lautstaerke); // Reduce volume by xx decibels.
               if(!ControlSession.lautstaerkeAus)
            	   clip.start();
          // }
       }catch(Exception e){ e.printStackTrace(); }
      
   }
   
   public  void play(String path) {
	      
       try{
    	 
    	   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
    	   AudioFormat af     = audioInputStream.getFormat();
    	   int size      = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
    	   byte[] audio       = new byte[size];
    	   DataLine.Info info      = new DataLine.Info(Clip.class, af, size);
    	   audioInputStream.read(audio, 0, size);
          
          // for(int i=0; i < 32; i++) {
               clip = (Clip) AudioSystem.getLine(info);
               clip.open(af, audio, 0, size);
               if(!ControlSession.lautstaerkeAus)
            	   clip.start();

               FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            		gainControl.setValue(ControlSession.lautstaerke); // Reduce volume by xx decibels.


          // }
       }catch(Exception e){ 
			e.printStackTrace();
			/*
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			JOptionPane.showMessageDialog(null, "Failure!"+ "\n" + sw.toString());
			*/
    	   }
      
   }
   
   public void stop(){
	   if(clip != null){
		   clip.stop();
		   clip.close();
	   }
   }
  
}




