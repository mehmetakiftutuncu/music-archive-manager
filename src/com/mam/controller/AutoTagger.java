package com.mam.controller;

import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.apache.commons.lang3.text.WordUtils;

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
	private boolean toUppercase;
	private boolean tryFileNameFirst;
	private String customArtistName;
	private String customAlbumName;
	
	/**
	 * Instantiates an AutoTagger object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param tryFileNameFirst If this is true, information derived from file name will be checked before file location
	 * @param customArtistName This will be written as artist tag when there is none available
	 * @param customAlbumName This will be written as album tag when there is none available
	 */
	public AutoTagger(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, boolean toUppercase, boolean tryFileNameFirst, String customArtistName, String customAlbumName)
	{
		super(archiveDirectory, progressBar, outputLog);
		
		this.toUppercase = toUppercase;
		this.tryFileNameFirst = tryFileNameFirst;
		this.customArtistName = customArtistName;
		this.customAlbumName = customAlbumName;
	}
	
	/**
	 * Gets the song tags to be written to the file
	 * 
	 * @param artist File for the current artist
	 * @param album File for the current album
	 * @param song File for the current artist
	 * 
	 * @return {@link SongTags} derived from the file location and file name, null if it was unsuccessful
	 */
	public SongTags getTags(File artist, File album, File song)
	{
		try
		{
			SongTags tags = new SongTags(null, null, null);
			
			TaggingPatterns taggingPattern = PatternUtils.getTaggingPattern(song.getName());
			
			if(taggingPattern == null)
			{
				tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(song.getName(), true).trim()) : StringUtils.getNameOrExtension(song.getName(), true).trim());
			}
			else
			{
				switch(taggingPattern)
				{
					case ARTIST_ALBUM_TITLE:
						String[] tokensArtistAlbumTitle = song.getName().split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensArtistAlbumTitle[2], true).trim()) : StringUtils.getNameOrExtension(tokensArtistAlbumTitle[2], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensArtistAlbumTitle[0].trim()) : tokensArtistAlbumTitle[0].trim());
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(tokensArtistAlbumTitle[1].trim()) : tokensArtistAlbumTitle[1].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
							if(album != null)
							{
								tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
							}
						}
						break;
						
					case ARTIST_TITLE:
						String[] tokensArtistTitle = song.getName().split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensArtistTitle[1], true).trim()) : StringUtils.getNameOrExtension(tokensArtistTitle[1], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensArtistTitle[0].trim()) : tokensArtistTitle[0].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
						}
						
						if(album != null)
						{
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());								
						}
						break;
						
					case NUMBER_ARTIST_ALBUM_TITLE:
						String[] tokensNumberArtistAlbumTitle = song.getName().substring(song.getName().indexOf(".") + 1).split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle[2], true).trim()) : StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle[2], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistAlbumTitle[0].trim()) : tokensNumberArtistAlbumTitle[0].trim());
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistAlbumTitle[1].trim()) : tokensNumberArtistAlbumTitle[1].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
							if(album != null)
							{
								tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
							}
						}
						break;
						
					case NUMBER_ARTIST_ALBUM_TITLE2:
						String[] tokensNumberArtistAlbumTitle2 = song.getName().split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle2[3], true).trim()) : StringUtils.getNameOrExtension(tokensNumberArtistAlbumTitle2[3], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistAlbumTitle2[1].trim()) : tokensNumberArtistAlbumTitle2[1].trim());
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistAlbumTitle2[2].trim()) : tokensNumberArtistAlbumTitle2[2].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
							if(album != null)
							{
								tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
							}
						}
						break;
						
					case NUMBER_ARTIST_TITLE:
						String[] tokensNumberArtistTitle = song.getName().substring(song.getName().indexOf(".") + 1).split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensNumberArtistTitle[1], true).trim()) : StringUtils.getNameOrExtension(tokensNumberArtistTitle[1], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistTitle[0].trim()) : tokensNumberArtistTitle[0].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
						}
						
						if(album != null)
						{
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
						}
						break;
						
					case NUMBER_ARTIST_TITLE2:
						String[] tokensNumberArtistTitle2 = song.getName().split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensNumberArtistTitle2[2], true).trim()) : StringUtils.getNameOrExtension(tokensNumberArtistTitle2[2], true).trim());
						
						if(tryFileNameFirst)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(tokensNumberArtistTitle2[1].trim()) : tokensNumberArtistTitle2[1].trim());
						}
						else
						{
							if(artist != null)
							{
								tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
							}
						}
						
						if(album != null)
						{
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
						}
						break;
						
					case NUMBER_TITLE:
						if(artist != null)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
						}
						if(album != null)
						{
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
						}
						
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(song.getName().substring(song.getName().indexOf(".") + 1), true).trim()) : StringUtils.getNameOrExtension(song.getName().substring(song.getName().indexOf(".") + 1), true).trim());
						break;
						
					case NUMBER_TITLE2:
						if(artist != null)
						{
							tags.setArtist(toUppercase ? WordUtils.capitalizeFully(artist.getName().trim()) : artist.getName().trim());
						}
						if(album != null)
						{
							tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(album.getName().trim()) : album.getName().trim());
						}
						
						String[] tokensNumberTitle = song.getName().split("\\-");
						tags.setTitle(toUppercase ? WordUtils.capitalizeFully(StringUtils.getNameOrExtension(tokensNumberTitle[1], true).trim()) : StringUtils.getNameOrExtension(tokensNumberTitle[1], true).trim());
						break;
				}
			}
			
			return tags;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
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
				tags.setArtist(toUppercase ? WordUtils.capitalizeFully(customArtistName) : customArtistName);
			}
			if(tags.getAlbum() == null)
			{
				tags.setAlbum(toUppercase ? WordUtils.capitalizeFully(customAlbumName) : customAlbumName);
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