package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;

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
	 */
	public AutoTagger(String archiveDirectory, JProgressBar progressBar)
	{
		super(archiveDirectory, progressBar);
	}

	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = new SongTags(	currentArtist != null ? currentArtist.getName() : "",
										currentAlbum != null ? currentAlbum.getName() : "",
										StringUtils.extractTitleFromFileName(currentSong != null ? currentSong.getName() : "", "\\-", 1));

		System.out.println("===== UPDATING TAG =====");
		System.out.println("File\t\t: "  + currentSong.getAbsolutePath());
		System.out.print("Tags");
		System.out.println("\tArtist\t: " + tags.getArtist());
		System.out.println("\tAlbum\t: " + tags.getAlbum());
		System.out.println("\tTitle\t: " + tags.getTitle());
		System.out.println();
		
		TagUtils.tagSong(currentSong, tags);
	}
}