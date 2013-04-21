package com.mam.controller;

import java.io.File;

/**
 * Automizer is the class for scanning the music archive and performing automatic operations
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public abstract class Automizer
{
	/**	The root directory of the music archive */
	protected String myArchiveDirectory;
	
	/**
	 * Instantiates an Automizer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 */
	protected Automizer(String archiveDirectory)
	{
		myArchiveDirectory = archiveDirectory;
	}
	
	/**
	 * Performs the automatic operation on the specified item
	 * 
	 * @param currentArtist File object representing the artist (outer-most folder in the archive)
	 * @param currentAlbum File object representing the album (middle folder in the archive)
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 */
	public abstract void process(File currentArtist, File currentAlbum, File currentSong);
	
	/**
	 * Runs the automatic process
	 */
	public void run()
	{
		for(File currentArtist : FileUtils.getSubDirectories(new File(myArchiveDirectory)))
		{
			for(File currentAlbum : FileUtils.getSubDirectories(currentArtist))
			{
				for(File currentSong : FileUtils.getFilesInDirectory(currentAlbum))
				{
					process(currentArtist, currentAlbum, currentSong);
				}
			}
		}
	}
}