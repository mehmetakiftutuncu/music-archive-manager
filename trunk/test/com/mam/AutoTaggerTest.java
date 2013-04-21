package com.mam;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.mam.controller.AutoTagger;
import com.mam.controller.FileUtils;
import com.mam.model.SongTags;

public class AutoTaggerTest
{
	@Test
	public void testExtractTitleFromFileName()
	{
		assertNotNull(new AutoTagger("testingSet").extractTitleFromFileName("Teoman - Mavi.mp3", "\\-", 1));
	}
	
	@Test
	public void testGetSubDirectories()
	{
		assertNotNull(FileUtils.getSubDirectories(new File("testingSet")));
	}
	
	@Test
	public void testGetFilesInDirectory()
	{
		assertNotNull(FileUtils.getFilesInDirectory(new File("testingSet/Teoman/Gönülçelen")));
	}
	
	@Test
	public void testTagSong()
	{
		assertTrue(new AutoTagger("").tagSong(	new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"),
												new SongTags("artist", "title", "album")));
	}
}