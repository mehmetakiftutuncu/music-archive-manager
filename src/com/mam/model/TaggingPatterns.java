package com.mam.model;

/**
 * Patterns for matching using regular expressions
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public enum TaggingPatterns
{
	/** ARTIST - ALBUM - TITLE */
	ARTIST_ALBUM_TITLE("^[^-]+-[^-]+-[^-]+$"),
	/** ARTIST - TITLE */
	ARTIST_TITLE("^[^-]+-[^-]+$"),
	/** NUMBER. ARTIST - ALBUM - TITLE */
	NUMBER_ARTIST_ALBUM_TITLE("^[0-9]+\\s*\\.[^-]+-[^-]+-[^-]+$"),
	/** NUMBER - ARTIST - ALBUM - TITLE */
	NUMBER_ARTIST_ALBUM_TITLE2("^[0-9]+\\s*-[^-]+-[^-]+-[^-]+$"),
	/** NUMBER. ARTIST - TITLE */
	NUMBER_ARTIST_TITLE("^[0-9]+\\s*\\.[^-]+-[^-]+$"),
	/** NUMBER - ARTIST - TITLE */
	NUMBER_ARTIST_TITLE2("^[0-9]+\\s*-[^-]+-[^-]+$"),
	/** NUMBER. TITLE */
	NUMBER_TITLE("^[0-9]+\\s*\\..+$"),
	/** NUMBER - TITLE */
	NUMBER_TITLE2("^[0-9]+\\s*-.+$");
	
	/** Current pattern */
	private String myPattern;
	
	TaggingPatterns(String pattern)
	{
		myPattern = pattern;
	}
	
	public String getPattern()
	{
		return myPattern;
	}
}