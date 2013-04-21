package com.mam.controller;

import java.io.File;

import com.mam.model.SongTags;

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
	public AutoNamer(String archiveDirectory)
	{
		super(archiveDirectory);
	}
	
	/**
	 * Reads tags of a song
	 * 
	 * @param song File object representing the song
	 * 
	 * @return Tags of the song if successful, null if any error occurs
	 */
	public SongTags readTags(File song)
	{
		return null;
	}
	
	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
	}
}