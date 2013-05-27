package com.mam.controller;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 * General controller for the Music Archive Manager
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public class MAM
{
	/**	The root directory of the music archive */
	protected String myArchiveDirectory;
	
	/**	The progress bar in the main frame */
	protected JProgressBar myProgressBar;
	
	/** Text area for the logging in the main frame */
	protected JTextArea myOutputLog;
	
	/** Run button in the main frame */
	protected JButton myRunButton;
	
	/**
	 * Constructor for MAM
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 * @param runButton Run button in the main frame
	 */
	public MAM(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog, JButton runButton)
	{
		myArchiveDirectory = archiveDirectory;
		myProgressBar = progressBar;
		myOutputLog = outputLog;
		myRunButton = runButton;
	}

	/**
	 * Performs auto-tagging on the given music archive directory
	 * 
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param tryFileNameFirst If this is true, information derived from file name will be checked before file location
	 * @param customArtistName This will be written as artist tag when there is none available
	 * @param customAlbumName This will be written as album tag when there is none available
	 * @param customTitleName This will be written as title tag when there is none available
	 */
	public void autoTag(boolean toUppercase, boolean tryFileNameFirst, String customArtistName, String customAlbumName, String customTitleName)
	{
		new AutoTagger(myArchiveDirectory, myProgressBar, myOutputLog, myRunButton,
						toUppercase,
						tryFileNameFirst,
						customArtistName,
						customAlbumName,
						customTitleName).run();
	}
	
	/**
	 * Performs auto-naming on the given music archive directory
	 * 
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param includeAlbum If this is true, album will be written as well while renaming music files
	 * @param customArtistName This will be used as artist tag when there is none available
	 * @param customAlbumName This will be used as album tag when there is none available
	 * @param customTitleName This will be used as title tag when there is none available
	 */
	public void autoName(boolean toUppercase, boolean includeAlbum, String customArtistName, String customAlbumName, String customTitleName)
	{
		new AutoNamer(myArchiveDirectory, myProgressBar, myOutputLog, myRunButton,
						toUppercase,
						includeAlbum,
						customArtistName,
						customAlbumName,
						customTitleName).run();
	}
	
	/**
	 * Performs auto-filing on the given music archive directory
	 * 
	 * @param toUppercase If this is true, first letter of each word will be upper-case
	 * @param generateAlbum If this is true, folders for each album is going to be generated inside each artist
	 * @param customArtistName This will be used as artist tag when there is none available
	 * @param customAlbumName This will be used as album tag when there is none available
	 */
	public void autoFile(boolean toUppercase, boolean generateAlbum, String customArtistName, String customAlbumName)
	{
		new AutoFiler(myArchiveDirectory, myProgressBar, myOutputLog, myRunButton,
						toUppercase,
						generateAlbum,
						customArtistName,
						customAlbumName).run();
	}
}