package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.mam.model.SongTags;
import com.mam.model.TaggingPatterns;
import com.mam.utilities.PatternUtils;
import com.mam.utilities.StringUtils;
import com.mam.utilities.TagUtils;

/**
 * AutoTagger is the class for handling auto-tagging feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class AutoTagger extends Automizer
{
	/**
	 * Instantiates an AutoTagger object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 */
	public AutoTagger(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog)
	{
		super(archiveDirectory, progressBar, outputLog);
	}
	
	public SongTags getTags(File artist, File album, File song)
	{
		SongTags tags = new SongTags(null, null, null);
		
		TaggingPatterns taggingPattern = PatternUtils.getTaggingPattern(song.getName());
		
		if(taggingPattern == null)
		{
			return null;
		}
		
		switch(taggingPattern)
		{
			case ARTIST_ALBUM_TITLE:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensArtistAlbumTitle = song.getName().split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensArtistAlbumTitle[2], true).trim());
				
				if(tags.getArtist() == null)
				{
					
					tags.setArtist(tokensArtistAlbumTitle[0].trim());
				}
				
				if(tags.getAlbum() == null)
				{
					tags.setAlbum(tokensArtistAlbumTitle[1].trim());
				}
				break;
				
			case ARTIST_TITLE:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensArtistTitle = song.getName().split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensArtistTitle[1], true).trim());
				
				if(tags.getArtist() == null)
				{
					tags.setArtist(tokensArtistTitle[0].trim());
				}
				break;
				
			case NUMBER_ARTIST_ALBUM_TITLE:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensNumberArtistAlbumTitle = song.getName().substring(song.getName().indexOf(".") + 1).split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle[2], true).trim());
				
				if(tags.getArtist() == null)
				{
					tags.setArtist(tokensNumberArtistAlbumTitle[0].trim());
				}
				
				if(tags.getAlbum() == null)
				{
					tags.setAlbum(tokensNumberArtistAlbumTitle[1].trim());
				}
				break;
				
			case NUMBER_ARTIST_ALBUM_TITLE2:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensNumberArtistAlbumTitle2 = song.getName().split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle2[3], true).trim());
				
				if(tags.getArtist() == null)
				{
					tags.setArtist(tokensNumberArtistAlbumTitle2[1].trim());
				}
				
				if(tags.getAlbum() == null)
				{
					tags.setAlbum(tokensNumberArtistAlbumTitle2[2].trim());
				}
				break;
				
			case NUMBER_ARTIST_TITLE:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensNumberArtistTitle = song.getName().substring(song.getName().indexOf(".") + 1).split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensNumberArtistTitle[1], true).trim());
				
				if(tags.getArtist() == null)
				{
					tags.setArtist(tokensNumberArtistTitle[0].trim());
				}
				break;
				
			case NUMBER_ARTIST_TITLE2:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensNumberArtistTitle2 = song.getName().split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensNumberArtistTitle2[2], true).trim());
				
				if(tags.getArtist() == null)
				{
					tags.setArtist(tokensNumberArtistTitle2[1].trim());
				}
				break;
				
			case NUMBER_TITLE:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				tags.setTitle(StringUtils.getNameOrExtension(song.getName().substring(song.getName().indexOf(".") + 1), true).trim());
				break;
				
			case NUMBER_TITLE2:
				if(artist != null)
				{
					tags.setArtist(artist.getName().trim());
				}
				
				if(album != null)
				{
					tags.setAlbum(album.getName().trim());
				}
				
				String[] tokensNumberTitle = song.getName().split("\\-");
				tags.setTitle(StringUtils.getNameOrExtension(tokensNumberTitle[1], true).trim());
				break;
		}
		
		return tags;
	}

	@Override
	public void process(File currentArtist, File currentAlbum, File currentSong)
	{
		SongTags tags = getTags(currentArtist, currentAlbum, currentSong);
		
		log("===== UPDATING TAG =====");
		log("File\t\t: "  + currentSong.getAbsolutePath());
		
		if(tags == null)
		{
			log("\tFile couldn't be matched. Skipping...");
			log("");
		}
		else
		{
			if(tags.getArtist() == null)
			{
				tags.setArtist("Unknown Artist");
			}
			if(tags.getAlbum() == null)
			{
				tags.setAlbum("Untitled Album");
			}
			if(tags.getTitle() == null)
			{
				tags.setTitle("Untitled");
			}
			
			log("Tags");
			log("\tArtist\t: " + tags.getArtist());
			log("\tAlbum\t: " + tags.getAlbum());
			log("\tTitle\t: " + tags.getTitle());
			log("");
			
			TagUtils.tagSong(currentSong, tags);
		}
		
		myProgressBar.setValue(myProgressBar.getValue() + 1);
	}
}