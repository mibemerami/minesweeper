package de.milanbrzezinski.minesweeper.hilfsklassen;

import java.io.*;

public class CopyDirectory {
    
    private BufferedInputStream in = null;
    private BufferedOutputStream out = null;
    
    public void copyDir(File quelle, File ziel) throws FileNotFoundException, IOException {
        
        File[] files = quelle.listFiles();
        ziel.mkdirs();
        for (File file : files) {
            if (file.isDirectory()) {
                copyDir(file, new File(ziel.getAbsolutePath() + System.getProperty("file.separator") + file.getName()));
            }
            else {
                copyFile(file, new File(ziel.getAbsolutePath() + System.getProperty("file.separator") + file.getName()));
            }
        }
    }
    
    public void copyFile(File file, File ziel) throws FileNotFoundException, IOException {
        
//        System.out.println("Copy " + file.getAbsolutePath() + " to " + ziel.getAbsolutePath());
        in = new BufferedInputStream(new FileInputStream(file));
        out = new BufferedOutputStream(new FileOutputStream(ziel, true));
        int bytes = 0;
        while ((bytes = in.read()) != -1) {
            out.write(bytes);
        }
        in.close();
        out.close();
    }
} 
