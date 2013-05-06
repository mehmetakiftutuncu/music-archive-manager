package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;

import com.mam.model.SongTags;
import com.mam.utilities.FileUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoNamer is the class for handling auto-naming feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class AutoNamer extends Automizer
{
	/**
	 * Instantiates an AutoNamer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 */
	public AutoNamer(String archiveDirectory, JProgressBar progressBar)
	{
		super(archiveDirectory, progressBar);
	}
	
	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = TagUtils.readTags(currentSong);
		
		System.out.println("===== UPDATING FILE NAME =====");
		System.out.println("Before\t\t: "  + currentSong.getAbsolutePath());
		System.out.print("Tags");
		System.out.println("\tArtist\t: " + tags.getArtist());
		System.out.println("\tAlbum\t: " + tags.getAlbum());
		System.out.println("\tTitle\t: " + tags.getTitle());
		
		currentSong = FileUtils.updateFileName(currentSong, tags, currentArtist, currentAlbum);
		
		System.out.println("After\t\t: "  + currentSong.getAbsolutePath());
		System.out.println();
	}
}