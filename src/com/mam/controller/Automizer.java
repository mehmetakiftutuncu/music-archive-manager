package com.mam.controller;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import com.mam.utilities.FileUtils;

/**
 * Automizer is the class for scanning the music archive and performing automatic operations
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 */
public abstract class Automizer
{
	/**	The root directory of the music archive */
	protected String myArchiveDirectory;
	
	/**	The progress bar in the main frame */
	protected JProgressBar myProgressBar;
	
	/** Text area for the logging in the main frame */
	protected JTextArea myOutputLog;
	
	/**	Start time of the operation */
	private long startTime;
	
	/**
	 * Instantiates an Automizer object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 * @param progressBar The progress bar in the main frame
	 * @param outputLog Text area for the logging in the main frame
	 */
	protected Automizer(String archiveDirectory, JProgressBar progressBar, JTextArea outputLog)
	{
		myArchiveDirectory = archiveDirectory;
		myProgressBar = progressBar;
		myOutputLog = outputLog;
	}
	
	/**
	 * Performs the automatic operation on the specified item
	 * 
	 * @param currentArtist File object representing the artist (outer-most folder in the archive)
	 * @param currentAlbum File object representing the album (middle folder in the archive)
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 */
	public abstract void process(File currentArtist, File currentAlbum, File currentSong);
	
	/**
	 * Runs the automatic process
	 */
	public void run()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				log("===== STARTING ======");
				log("");
				
				startTime = System.currentTimeMillis();
				
				myProgressBar.setValue(0);
				myProgressBar.setMaximum(countFiles(new File(myArchiveDirectory)));
				
				ArrayList<File> artistFolders = FileUtils.getSubDirectories(new File(myArchiveDirectory));
				
				// If there are artist folders
				if(artistFolders.size() > 0)
				{
					// For every artist
					for(File currentArtist : artistFolders)
					{
						ArrayList<File> albumFolders = FileUtils.getSubDirectories(currentArtist);
						
						// If there are album folders
						if(albumFolders.size() > 0)
						{
							// For each album
							for(File currentAlbum : albumFolders)
							{
								// For each file
								for(File currentSong : FileUtils.getFilesInDirectory(currentAlbum))
								{
									process(currentArtist, currentAlbum, currentSong);
								}
							}
						}
						else
						{
							// For each file
							for(File currentSong : FileUtils.getFilesInDirectory(currentArtist))
							{
								process(currentArtist, null, currentSong);
							}
						}
					}
				}
				else
				{
					// For each file
					for(File currentSong : FileUtils.getFilesInDirectory(new File(myArchiveDirectory)))
					{
						process(null, null, currentSong);
					}
				}
				
				log(String.format("===== FINISHED %d items in %d milliseconds ======", myProgressBar.getMaximum(), (System.currentTimeMillis() - startTime)));
				log("");
				myProgressBar.setValue(0);
			}
		}).start();
	}
	
	/**
	 * Adds one line to the output log
	 * 
	 * @param message Message to log
	 */
	public void log(String message)
	{
		myOutputLog.append(message + "\n");
		System.out.println(message);
		myOutputLog.setCaretPosition(myOutputLog.getText().length());
	}
	
	/**
	 * Counts mp3 files in a directory recursively
	 * 
	 * @param folder Current folder
	 * 
	 * @return Total number of mp3 files in the given directory
	 */
	public int countFiles(File folder)
	{
		int count = 0;
		
        File[] files = folder.listFiles();
        for(File file: files)
        {
            if(file.isFile())
            {
            	if(file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3"))
            	{
            		count++;
            	}
            }
            else
            {
                count += countFiles(file);
            }
        }

        return count;
    }
}