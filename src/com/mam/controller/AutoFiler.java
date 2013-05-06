package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;

import com.mam.model.SongTags;
import com.mam.utilities.FileUtils;
import com.mam.utilities.StringUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoFiler is the class for handling auto-filing feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class AutoFiler extends Automizer
{
	/**
	 * Instantiates an AutoFiler object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 */
	public AutoFiler(String archiveDirectory, JProgressBar progressBar)
	{
		super(archiveDirectory, progressBar);
	}
	
	/**
	 * Relocates a file according to tags and the name of the given file
	 * 
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 * @param tags Tags of the current song
	 * 
	 * @return Moved file if successful, null if any error occurs
	 */
	public File relocateFile(File currentSong, SongTags tags)
	{
		String path = myArchiveDirectory + "/";
		
		if(!tags.getArtist().equals(""))
		{
			path += tags.getArtist() + "/";
		}
		else
		{
			path += StringUtils.extractTitleFromFileName(currentSong.getName(), "\\-", 0) + "/";
		}
		
		if(!tags.getAlbum().equals(""))
		{
			path += tags.getAlbum();
		}
		else
		{
			path += "Untitled";
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
		SongTags tags = TagUtils.readTags(currentSong);
		
		System.out.println("===== RELOCATING FILE =====");
		System.out.println("Before\t\t: "  + currentSong.getAbsolutePath());
		System.out.print("Tags");
		System.out.println("\tArtist\t: " + tags.getArtist());
		System.out.println("\tAlbum\t: " + tags.getAlbum());
		System.out.println("\tTitle\t: " + tags.getTitle());
		
		File newFile = relocateFile(currentSong, tags);
		if(newFile != null)
		{
			System.out.println("After\t\t: "  + newFile.getAbsolutePath());
		}
		System.out.println();
	}
}