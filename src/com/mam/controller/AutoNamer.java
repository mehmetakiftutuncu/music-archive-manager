package com.mam.controller;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.commons.lang3.text.WordUtils;

import com.mam.model.SongTags;
import com.mam.utilities.StringUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoNamer is the class for handling auto-naming feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class AutoNamer extends Automizer
{
	private boolean toUppercase;
	private boolean includeAlbum;
	private String customArtistName;
	private String customAlbumName;
	private String customTitleName;
	
	/**
	 * Instantiates an AutoNamer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param runButton Run button in the main frame
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param includeAlbum If this is true, album will be written as well while renaming music files
	 * @param customArtistName This will be used as artist tag when there is none available
	 * @param customAlbumName This will be used as album tag when there is none available
	 * @param customTitleName This will be used as title tag when there is none available
	 */
	public AutoNamer(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, JButton runButton, boolean toUppercase, boolean includeAlbum, String customArtistName, String customAlbumName, String customTitleName)
	{
		super(archiveDirectory, progressBar, outputLog, runButton);
		
		this.toUppercase = toUppercase;
		this.includeAlbum = includeAlbum;
		this.customArtistName = customArtistName;
		this.customAlbumName = customAlbumName;
		this.customTitleName = customTitleName;
	}
	
	/**
	 * Updates the file name according to tags and the directory of the given file
	 * 
	 * @param currentArtist File object representing the artist (outer-most folder in the archive)
	 * @param tags Tags of the current song
	 * @param currentAlbum File object representing the album (middle folder in the archive)
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 * 
	 * @return A reference to the new file, null if any error occurs
	 */
	public File updateFileName(File currentSong, SongTags tags, File currentArtist, File currentAlbum)
	{
		String path = currentSong.getAbsolutePath();
		int index = path.lastIndexOf(currentSong.getName());
		path = path.substring(0, index);
		
		String name = "";
		
		// Artist
		if(tags.getArtist() != null)
		{
			name = toUppercase ? WordUtils.capitalizeFully(tags.getArtist()) : tags.getArtist();
		}
		else
		{
			if(currentArtist != null)
			{
				name = toUppercase ? WordUtils.capitalizeFully(currentArtist.getName()) : currentArtist.getName();
			}
			else
			{
				name = toUppercase ? WordUtils.capitalizeFully(customArtistName) : customArtistName;
			}
		}
		
		// Album
		if(includeAlbum)
		{
			if(tags.getAlbum() != null)
			{
				name += " - " + (toUppercase ? WordUtils.capitalizeFully(tags.getAlbum()) : tags.getAlbum());
			}
			else
			{
				if(currentAlbum != null)
				{
					name += " - " + (toUppercase ? WordUtils.capitalizeFully(currentAlbum.getName()) : currentAlbum.getName());
				}
				else
				{
					name += " - " + (toUppercase ? WordUtils.capitalizeFully(customAlbumName) : customAlbumName);
				}
			}
		}
		
		// Title
		if(tags.getTitle() != null)
		{
			name += " - " + (toUppercase ? WordUtils.capitalizeFully(tags.getTitle()) : tags.getTitle());
		}
		else
		{
			name += " - " + (toUppercase ? WordUtils.capitalizeFully(customTitleName) : customTitleName);
		}
		
		// Extension
		name += StringUtils.getNameOrExtension(currentSong, false);
		
		File newFile = new File(path + name);
		
		if(currentSong.renameTo(newFile))
		{
			return newFile;
		}
		else
		{
			return null;
		}
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
			
			currentSong = updateFileName(currentSong, tags, currentArtist, currentAlbum);
			
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