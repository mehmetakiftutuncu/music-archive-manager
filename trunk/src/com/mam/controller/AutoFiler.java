package com.mam.controller;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.commons.lang3.text.WordUtils;

import com.mam.model.SongTags;
import com.mam.utilities.FileUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoFiler is the class for handling auto-filing feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class AutoFiler extends Automizer
{
	private boolean toUppercase;
	private boolean generateAlbum;
	private String customArtistName;
	private String customAlbumName;
	
	/**
	 * Instantiates an AutoFiler object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param runButton Run button in the main frame
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param generateAlbum If this is true, folders for each album is going to be generated inside each artist
	 * @param customArtistName This will be used as artist tag when there is none available
	 * @param customAlbumName This will be used as album tag when there is none available
	 */
	public AutoFiler(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, JButton runButton, boolean toUppercase, boolean generateAlbum, String customArtistName, String customAlbumName)
	{
		super(archiveDirectory, progressBar, outputLog, runButton);
		
		this.toUppercase = toUppercase;
		this.generateAlbum = generateAlbum;
		this.customArtistName = customArtistName;
		this.customAlbumName = customAlbumName;
	}
	
	/**
	 * Relocates a file according to tags and the name of the given file
	 * 
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 * @param readTags Read tags of the current song
	 * @param readTags Generated tags of the current song
	 * 
	 * @return Moved file if successful, null if any error occurs
	 */
	public File relocateFile(File currentSong, SongTags readTags, SongTags generatedTags)
	{
		String path = myArchiveDirectory + "/";
		
		// Artist
		if(readTags.getArtist() != null && !readTags.getArtist().equals(""))
		{
			path += (toUppercase ? WordUtils.capitalizeFully(readTags.getArtist()) : readTags.getArtist()) + "/";
		}
		else if(generatedTags.getArtist() != null)
		{
			path += (toUppercase ? WordUtils.capitalizeFully(generatedTags.getArtist()) : generatedTags.getArtist()) + "/";
		}
		else
		{
			path += (toUppercase ? WordUtils.capitalizeFully(customArtistName) : customArtistName) + "/";
		}
		
		// Album
		if(generateAlbum)
		{
			if(readTags.getAlbum() != null && !readTags.getAlbum().equals(""))
			{
				path += (toUppercase ? WordUtils.capitalizeFully(readTags.getAlbum()) : readTags.getAlbum()) + "/";
			}
			else if(generatedTags.getAlbum() != null)
			{
				path += (toUppercase ? WordUtils.capitalizeFully(generatedTags.getAlbum()) : generatedTags.getAlbum()) + "/";
			}
			else
			{
				path += (toUppercase ? WordUtils.capitalizeFully(customAlbumName) : customAlbumName) + "/";
			}
		}
		
		File targetPath = new File(path);
		
		if(FileUtils.moveFile(currentSong, targetPath, currentSong.getName()))
		{
			return new File(targetPath.getAbsoluteFile() + "/" + currentSong.getName());
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags readTags = TagUtils.readTags(currentSong);
		SongTags generatedTags = TagUtils.generateTags(currentArtist, currentAlbum, currentSong, toUppercase, true);
		
		log("===== RELOCATING FILE =====");
		log("Before\t\t: "  + currentSong.getAbsolutePath());
		
		if(readTags == null && generatedTags == null)
		{
			log("\tFile couldn't be relocated. Skipping...");
			log("");
		}
		else
		{
			log("Tags");
			log("\tArtist\t: " + readTags.getArtist());
			log("\tAlbum\t: " + readTags.getAlbum());
			log("\tTitle\t: " + readTags.getTitle());
			
			File newFile = relocateFile(currentSong, readTags, generatedTags);
			if(newFile != null)
			{
				log("After\t\t: "  + newFile.getAbsolutePath());
			}
			log("");
		}
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}