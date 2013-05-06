package com.mam.controller;

import javax.swing.JProgressBar;

/**
 * General controller for the Music Archive Manager
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class MAM
{
	/**	The progress bar in the main frame */
	protected JProgressBar myProgressBar;
	
	public MAM(JProgressBar progressBar)
	{
		myProgressBar = progressBar;
	}

	/**
	 * Performs auto-tagging on the given music archive directory
	 * 
	 * @param musicArchive Path of the music archive directory
	 */
	public void autoTag(String musicArchive)
	{
		new AutoTagger(musicArchive, myProgressBar).run();
	}
	
	/**
	 * Performs auto-naming on the given music archive directory
	 * 
	 * @param musicArchive Path of the music archive directory
	 */
	public void autoName(String musicArchive)
	{
		new AutoNamer(musicArchive, myProgressBar).run();
	}
	
	/**
	 * Performs auto-filing on the given music archive directory
	 * 
	 * @param musicArchive Path of the music archive directory
	 */
	public void autoFile(String musicArchive)
	{
		new AutoFiler(musicArchive, myProgressBar).run();
	}
}