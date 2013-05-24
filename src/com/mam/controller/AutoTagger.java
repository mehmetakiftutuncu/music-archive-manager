package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.mam.model.SongTags;
import com.mam.utilities.StringUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoTagger is the class for handling auto-tagging feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class AutoTagger extends Automizer
{
	/**
	 * Instantiates an AutoTagger object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 */
	public AutoTagger(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog)
	{
		super(archiveDirectory, progressBar, outputLog);
	}

	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = new SongTags(	currentArtist != null ? currentArtist.getName() : "",
										currentAlbum != null ? currentAlbum.getName() : "",
										StringUtils.extractTitleFromFileName(currentSong != null ? currentSong.getName() : "", "\\-", 1));

		log("===== UPDATING TAG =====");
		log("File\t\t: "  + currentSong.getAbsolutePath());
		log("Tags");
		log("\tArtist\t: " + tags.getArtist());
		log("\tAlbum\t: " + tags.getAlbum());
		log("\tTitle\t: " + tags.getTitle());
		log("");
		
		TagUtils.tagSong(currentSong, tags);
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}