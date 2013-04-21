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
	/** Title of the song */
	private String title;
	/** Album of the song */
	private String album;
	
	/**
	 * Instantiates a SongTags object
	 * 
	 * @param artist Artist of the song
	 * @param title Title of the song
	 * @param album Album of the song
	 */
	public SongTags(String artist, String title, String album)
	{
		setArtist(artist);
		setTitle(title);
		setAlbum(album);
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
	
	@Override
	public String toString()
	{
		return String.format("%s - %s (%s)", artist, title, album);
	}
}