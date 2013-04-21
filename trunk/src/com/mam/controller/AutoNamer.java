package com.mam.controller;

import java.io.File;

import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;

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
		try
		{
			MediaFile mediaFile = new MP3File(song);

	        ID3V1_0Tag id3Tags = (ID3V1_0Tag) mediaFile.getID3V1Tag();
	        
	        String artist = id3Tags.getArtist();
	        String album = id3Tags.getAlbum();
	        String title = id3Tags.getTitle();
	        
	        SongTags tags = new SongTags(artist, album, title);
	        
	        return tags;
		}
		catch(Exception e)
		{
			System.out.println("Tags couldn't be read!");
			e.printStackTrace();
			
			return null;
		}
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
		if(tags.getArtist() != null)
		{
			name = tags.getArtist();
		}
		else
		{
			name = currentArtist.getName();
		}
		name += " - " + tags.getTitle() + FileUtils.getNameOrExtension(currentSong, false);
		
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
		SongTags tags = readTags(currentSong);
		
		System.out.println("===== UPDATING FILE NAME =====");
		System.out.println("Before\t\t: "  + currentSong.getAbsolutePath());
		System.out.print("Tags");
		System.out.println("\tArtist\t: " + tags.getArtist());
		System.out.println("\tAlbum\t: " + tags.getAlbum());
		System.out.println("\tTitle\t: " + tags.getTitle());
		
		currentSong = updateFileName(currentSong, tags, currentArtist, currentAlbum);
		
		System.out.println("After\t\t: "  + currentSong.getAbsolutePath());
		System.out.println();
	}
}