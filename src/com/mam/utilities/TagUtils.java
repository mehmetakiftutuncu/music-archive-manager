package com.mam.utilities;

import java.io.File;

import org.apache.commons.lang3.text.WordUtils;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;

import com.mam.model.SongTags;
import com.mam.model.TaggingPatterns;

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
	
	/**
	 * Generates the song tags from the name and the location of the file
	 * 
	 * @param artist File for the current artist
	 * @param album File for the current album
	 * @param song File for the current artist
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param tryFileNameFirst If this is true, information derived from file name will be checked before file location
	 * 
	 * @return {@link SongTags} derived from the file location and file name, null if it was unsuccessful
	 */
	public static SongTags generateTags(File artist, File album, File song, boolean toUppercase, boolean tryFileNameFirst)
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
}