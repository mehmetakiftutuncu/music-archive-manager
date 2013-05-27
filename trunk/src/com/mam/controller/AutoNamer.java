package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.mam.model.SongTags;
import com.mam.utilities.FileUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoNamer is the class for handling auto-naming feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class AutoNamer extends Automizer
{
	/**
	 * Instantiates an AutoNamer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 */
	public AutoNamer(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog)
	{
		super(archiveDirectory, progressBar, outputLog);
	}
	
	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = TagUtils.readTags(currentSong);
		
		log("===== UPDATING FILE NAME =====");
		log("Before\t\t: "  + currentSong.getAbsolutePath());
		
		if(tags == null)
		{
			log("\tFile couldn't be renamed. Skipping...");
			log("");
		}
		else
		{
			log("Tags");
			log("\tArtist\t: " + tags.getArtist());
			log("\tAlbum\t: " + tags.getAlbum());
			log("\tTitle\t: " + tags.getTitle());
			
			currentSong = FileUtils.updateFileName(currentSong, tags, currentArtist, currentAlbum);
			
			if(currentSong == null)
			{
				log("\tFile couldn't be renamed. Skipping...");
				log("");
			}
			else
			{
				log("After\t\t: "  + currentSong.getAbsolutePath());
				log("");
			}
		}
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}