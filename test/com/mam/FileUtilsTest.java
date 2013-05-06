package com.mam;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.mam.model.SongTags;
import com.mam.utilities.FileUtils;

public class FileUtilsTest
{
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
	public void testUpdateFileName()
	{
		assertNotNull(FileUtils.updateFileName(	new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"),
																new SongTags("artist", "album", "title"),
																new File("testingSet/Teoman"),
																new File("testingSet/Teoman/Gönülçelen")));
		
		assertNotNull(FileUtils.updateFileName(	new File("testingSet/Teoman/Gönülçelen/Teoman - Mavi.mp3"),
																new SongTags(null, "album", "title"),
																new File("testingSet/Teoman"),
																new File("testingSet/Teoman/Gönülçelen")));
	}
	
	@Test
	public void testMoveFile()
	{
		assertTrue(FileUtils.moveFile(	new File("testingSet/Teoman/Gönülçelen/Teoman - Ýstasyon Ýnsanlarý.mp3"),
										new File("testingSet/Teoman1/Gönülçelen2"),
										"Teoman - Ýstasyon Ýnsanlarý3.mp3"));
	}
}