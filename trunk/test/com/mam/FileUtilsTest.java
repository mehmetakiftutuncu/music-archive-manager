package com.mam;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.mam.controller.FileUtils;

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
	public void testGetNameOrExtension()
	{
		assertNotNull(FileUtils.getNameOrExtension(new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"), true));
		assertNotNull(FileUtils.getNameOrExtension(new File("testingSet/Teoman/Gönülçelen/Teoman - Gönülçelen.mp3"), false));
	}
}