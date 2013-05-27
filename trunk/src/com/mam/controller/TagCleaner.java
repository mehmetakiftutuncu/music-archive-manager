package com.mam.controller;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.mam.utilities.TagUtils;

/**
 * TagCleaner is the class for clearing tags feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class TagCleaner extends Automizer
{
	/**
	 * Instantiates an TagCleaner object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param runButton Run button in the main frame
	 */
	public TagCleaner(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, JButton runButton)
	{
		super(archiveDirectory, progressBar, outputLog, runButton);
	}
	
	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		log("===== CLEARING TAGS OF FILE =====");
		log("File: "  + currentSong.getAbsolutePath());
		log("");
		
		if(!TagUtils.clearTags(currentSong))
		{
			log("\tTags of the file couldn't be cleared. Skipping...");
			log("");
		}
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}