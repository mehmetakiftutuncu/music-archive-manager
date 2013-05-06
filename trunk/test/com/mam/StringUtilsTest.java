package com.mam;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.mam.utilities.StringUtils;

public class StringUtilsTest
{
	@Test
	public void testExtractTitleFromFileName()
	{
		assertNotNull(StringUtils.extractTitleFromFileName("Teoman - Mavi.mp3", "\\-", 1));
	}
	
	@Test
	public void testGetNameOrExtension()
	{
		assertNotNull(StringUtils.getNameOrExtension(new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"), true));
		assertNotNull(StringUtils.getNameOrExtension(new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"), false));
	}
}