package com.mam;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mam.model.TaggingPatterns;
import com.mam.utilities.PatternUtils;

public class PatternTest
{
	@Test
	public void testStartsWithNumber()
	{
		assertTrue(PatternUtils.startsWithNumber("1.Teoman - Gönülçelen"));
	}
	
	@Test
	public void testPattern()
	{
		String name = "01. Teoman - Gönülçelen";
		
		TaggingPatterns patternToUse = PatternUtils.getTaggingPattern(name);
		
		if(patternToUse != null)
		{
			System.out.println("Pattern to use: " + patternToUse);
			
			assertTrue(true);
		}
		else
		{
			System.out.println("No match found");
			
			assertTrue(false);
		}
	}
}