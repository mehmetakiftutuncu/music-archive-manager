package com.mam.utilities;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mam.model.TaggingPatterns;

/**
 * PatternUtils is the utility class for handling pattern matching
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class PatternUtils
{
	/**
	 * Checks whether the given string starts with a number or not
	 * 
	 * @param string String to check
	 * 
	 * @return true if the given string starts with a number, false otherwise
	 */
	public static boolean startsWithNumber(String string)
	{
		Pattern pattern = Pattern.compile("\\s*[0-9]+.*");
		Matcher matcher = pattern.matcher(string);
		
		return matcher.matches();
	}
	
	/**
	 * Gets the matching tagging pattern for the given name
	 * 
	 * @param name Name of the file
	 * 
	 * @return Matching {@link TaggingPattern} or null if no matches were found 
	 */
	public static TaggingPatterns getTaggingPattern(String name)
	{
		TaggingPatterns patternToUse = null;
		
		ArrayList<TaggingPatterns> matchingPatterns = new ArrayList<TaggingPatterns>();
		
		Pattern pattern;
		Matcher matcher = null;
		
		for(TaggingPatterns i : TaggingPatterns.values())
		{
			pattern = Pattern.compile(i.getPattern());
			matcher = pattern.matcher(name);
			
			if(matcher != null && matcher.matches())
			{
				matchingPatterns.add(i);
			}
		}
		
		boolean isMatchedWithNumberedPattern = false;
		
		for(TaggingPatterns i : matchingPatterns)
		{
			switch(i)
			{
				case NUMBER_ARTIST_ALBUM_TITLE:
				case NUMBER_ARTIST_ALBUM_TITLE2:
				case NUMBER_ARTIST_TITLE:
				case NUMBER_ARTIST_TITLE2:
				case NUMBER_TITLE:
				case NUMBER_TITLE2:
					patternToUse = i;
					isMatchedWithNumberedPattern = true;
					break;
				default:
					break;
			}
			
			if(isMatchedWithNumberedPattern)
			{
				break;
			}
		}
		
		if(!isMatchedWithNumberedPattern && matchingPatterns.size() != 0)
		{
			patternToUse = matchingPatterns.get(0);
		}
		
		return patternToUse;
	}
}