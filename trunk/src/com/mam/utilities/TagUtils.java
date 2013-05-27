package com.mam.utilities;

import java.io.File;

import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;

import com.mam.model.SongTags;

/**
 * TagUtils is the utility class for handling file tagging operations of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class TagUtils
{
	/**
	 * Updates tags of a song
	 * 
	 * @param song File object representing the song
	 * @param tags Tags that is going to be written to the file
	 * 
	 * @return true if successful, false if any error occurs
	 */
	public static boolean tagSong(File song, SongTags tags)
	{
		try
		{
			MediaFile mediaFile = new MP3File(song);

	        ID3V1_0Tag id3Tags = new ID3V1_0Tag();
	        id3Tags.setArtist(tags.getArtist());
	        id3Tags.setTitle(tags.getTitle());
	        id3Tags.setAlbum(tags.getAlbum());
	        
	        mediaFile.removeTags();
	       
	        mediaFile.setID3Tag(id3Tags);
	        
	        mediaFile.sync();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Tags couldn't be written!");
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Reads tags of a song
	 * 
	 * @param song File object representing the song
	 * 
	 * @return Tags of the song if successful, null if any error occurs
	 */
	public static SongTags readTags(File song)
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
}