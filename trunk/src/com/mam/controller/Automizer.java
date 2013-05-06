package com.mam.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import com.mam.utilities.FileUtils;

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
	
	/**	The progress bar in the main frame */
	protected JProgressBar myProgressBar;
	
	/**
	 * Instantiates an Automizer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 */
	protected Automizer(String archiveDirectory, JProgressBar progressBar)
	{
		myArchiveDirectory = archiveDirectory;
		myProgressBar = progressBar;
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
		ArrayList<File> artistFolders = FileUtils.getSubDirectories(new File(myArchiveDirectory));
		
		// If there are artist folders
		if(artistFolders.size() > 0)
		{
			// For every artist
			for(File currentArtist : artistFolders)
			{
				ArrayList<File> albumFolders = FileUtils.getSubDirectories(currentArtist);
				
				// If there are album folders
				if(albumFolders.size() > 0)
				{
					// For each album
					for(File currentAlbum : albumFolders)
					{
						// For each file
						for(File currentSong : FileUtils.getFilesInDirectory(currentAlbum))
						{
							process(currentArtist, currentAlbum, currentSong);
						}
					}
				}
				else
				{
					// For each file
					for(File currentSong : FileUtils.getFilesInDirectory(currentArtist))
					{
						process(currentArtist, null, currentSong);
					}
				}
			}
		}
		else
		{
			// For each file
			for(File currentSong : FileUtils.getFilesInDirectory(new File(myArchiveDirectory)))
			{
				process(null, null, currentSong);
			}
		}
	}
}