package com.mam.controller;

/**
 * General controller for the Music Archive Manager
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class MAM
{
	/**
	 * Performs auto-tagging on the given music archive directory
	 * 
	 * @param musicArchive Path of the music archive directory
	 */
	public void autoTag(String musicArchive)
	{
		new AutoTagger(musicArchive).run();
	}
}