package com.mam.controller;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.commons.lang3.text.WordUtils;

import com.mam.model.SongTags;
import com.mam.utilities.TagUtils;

/**
 * AutoTagger is the class for handling auto-tagging feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class AutoTagger extends Automizer
{
	private boolean toUppercase;
	private boolean tryFileNameFirst;
	private String customArtistName;
	private String customAlbumName;
	private String customTitleName;
	
	/**
	 * Instantiates an AutoTagger object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param runButton Run button in the main frame
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param tryFileNameFirst If this is true, information derived from file name will be checked before file location
	 * @param customArtistName This will be written as artist tag when there is none available
	 * @param customAlbumName This will be written as album tag when there is none available
	 * @param customTitleName This will be written as title tag when there is none available
	 */
	public AutoTagger(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, JButton runButton, boolean toUppercase, boolean tryFileNameFirst, String customArtistName, String customAlbumName, String customTitleName)
	{
		super(archiveDirectory, progressBar, outputLog, runButton);
		
		this.toUppercase = toUppercase;
		this.tryFileNameFirst = tryFileNameFirst;
		this.customArtistName = customArtistName;
		this.customAlbumName = customAlbumName;
		this.customTitleName = customTitleName;
	}

	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = TagUtils.generateTags(currentArtist, currentAlbum, currentSong, toUppercase, tryFileNameFirst);
		
		log("===== UPDATING TAG =====");
		log("File\t\t: "  + currentSong.getAbsolutePath());
		
		if(tags == null)
		{
			log("\tFile couldn't be matched. Skipping...");
			log("");
		}
		else
		{
			if(tags.getArtist() == null)
			{
				tags.setArtist(toUppercase ? WordUtils.capitalizeFully(customArtistName) : customArtistName);
			}
			if(tags.getAlbum() == null)
			{
				tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(customAlbumName) : customAlbumName);
			}
			if(tags.getTitle() == null)
			{
				tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(customTitleName) : customTitleName);
			}
			
			log("Tags");
			log("\tArtist\t: " + tags.getArtist());
			log("\tAlbum\t: " + tags.getAlbum());
			log("\tTitle\t: " + tags.getTitle());
			log("");
			
			TagUtils.tagSong(currentSong, tags);
		}
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}