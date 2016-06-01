package de.milanbrzezinski.minesweeper.sound;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import de.milanbrzezinski.minesweeper.fenster.ControlSession;
import de.milanbrzezinski.minesweeper.hilfsklassen.CopyDirectory;
 
public class ZipArchiveExtractor {
 
    /**
     * @param args
     */
	
    public void checkForSoundfiles(String name){
    	File soundDir = new File(name);
    	if(!soundDir.exists()){
    		try {
				this.extractArchive(new File(
				        "mineSweeper"+ControlSession.version+".jar"), new File(
				        "tmp"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		try {
				this.placeSoundDir();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		File tmp = new File("tmp");
    		this.del(tmp);
    	}
    }
 
    private void placeSoundDir() throws IOException {
        File sourceFile = new File("tmp/snd");
        File destinationFile = new File("snd");
        new CopyDirectory().copyDir(sourceFile, destinationFile);
	}
    

    public boolean del(File dir){
		if (dir.isDirectory()){
			File[] files = dir.listFiles();
			for (File aktFile: files){
				del(aktFile);
			}
		}
		return dir.delete();
	} 

	public void extractArchive(File archive, File destDir) throws Exception {
        if (!destDir.exists()) {
            destDir.mkdir();
        }
 
        ZipFile zipFile = new ZipFile(archive);
        Enumeration entries = zipFile.entries();
 
        byte[] buffer = new byte[16384];
        int len;
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
 
            String entryFileName = entry.getName();
 
            File dir = dir = buildDirectoryHierarchyFor(entryFileName, destDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
 
            if (!entry.isDirectory()) {
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(new File(destDir, entryFileName)));
 
                BufferedInputStream bis = new BufferedInputStream(zipFile
                        .getInputStream(entry));
 
                while ((len = bis.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
 
                bos.flush();
                bos.close();
                bis.close();
            }
        }
                zipFile.close();
    }
 
    private File buildDirectoryHierarchyFor(String entryName, File destDir) {
        int lastIndex = entryName.lastIndexOf('/');
        String entryFileName = entryName.substring(lastIndex + 1);
        String internalPathToEntry = entryName.substring(0, lastIndex + 1);
        return new File(destDir, internalPathToEntry);
    }
    

}
