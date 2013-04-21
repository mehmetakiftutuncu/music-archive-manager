package com.mam.model;

/**
 * A model for the tags of a song
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class SongTags
{
	/** Artist of the song */
	private String artist;
	/** Album of the song */
	private String album;
	/** Title of the song */
	private String title;
	
	/**
	 * Instantiates a SongTags object
	 * 
	 * @param artist Artist of the song
	 * @param album Album of the song
	 * @param title Title of the song
	 */
	public SongTags(String artist, String album, String title)
	{
		setArtist(artist);
		setAlbum(album);
		setTitle(title);
	}
	
	/**
	 * Gets the artist of the song
	 * 
	 * @return The artist of the song
	 */
	public String getArtist()
	{
		return artist;
	}
	
	/**
	 * Sets the artist of the song
	 * 
	 * @param artist New artist
	 */
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	/**
	 * Gets the album of the song
	 * 
	 * @return The album of the song
	 */
	public String getAlbum()
	{
		return album;
	}
	
	/**
	 * Sets the album of the song
	 * 
	 * @param album New album
	 */
	public void setAlbum(String album)
	{
		this.album = album;
	}
	
	/**
	 * Gets the title of the song
	 * 
	 * @return The title of the song
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Sets the title of the song
	 * 
	 * @param title New title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s - %s (%s)", artist, title, album);
	}
}