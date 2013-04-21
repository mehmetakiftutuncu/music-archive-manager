package com.mam.controller;

import java.io.File;
import java.util.ArrayList;

import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;

import com.mam.model.SongTags;

/**
 * AutoTagger is the class for handling auto-tagging feature of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class AutoTagger
{
	/**	The root directory of the music archive */
	private String myArchiveDirectory;
	
	/**
	 * Instantiates an AutoTagger object
	 * 
	 * @param archiveDirectory The root directory of the music archive
	 */
	public AutoTagger(String archiveDirectory)
	{
		myArchiveDirectory = archiveDirectory;
	}
	
	/**
	 * Extracts the title of the song from the given file name
	 * 
	 * @param fileName File name from which the title will be extracted
	 * @param separator Pattern that separates tokens in the file name
	 * @param tokenIndex Indicates which token is the title in file name
	 * 
	 * @return Title of the song extracted from the given file name
	 */
	public String extractTitleFromFileName(String fileName, String separator, int tokenIndex)
	{
		if(fileName == null || fileName.equals(""))
		{
			throw new IllegalArgumentException("File name must not be empty or null!");
		}
		
		if(separator == null || separator.equals(""))
		{
			throw new IllegalArgumentException("Separator must not be empty or null!");
		}
		
		if(tokenIndex < 0)
		{
			throw new IllegalArgumentException("Token index must be greater than 0!");
		}
		
		
		String result = null;
		
		String[] tokens;
		try
		{
			tokens = fileName.split(separator);
		}
		catch(Exception e)
		{
			System.out.println("File name couldn't be split!");
			e.printStackTrace();
			
			return null;
		}
		
		try
		{
			if(tokens[tokenIndex].contains("."))
			{
				int end = tokens[tokenIndex].lastIndexOf(".");
				result = tokens[tokenIndex].substring(0, end);
			}
			else
			{
				result = tokens[tokenIndex];
			}
		}
		catch(Exception e)
		{
			System.out.println("Token index was out of bounds!");
			e.printStackTrace();
			
			return null;
		}
		
		return result.trim();
	}
	
	/**
	 * Gets the list of sub directories of the given directory
	 *   
	 * @param root Directory from which the sub directories will be found
	 * 
	 * @return The list of sub directories of the given directory
	 */
	public ArrayList<File> getSubDirectories(File root)
	{
		ArrayList<File> subDirectories = null;
		
		if(root.isDirectory())
		{
			subDirectories = new ArrayList<File>();
			for(File file : root.listFiles())
			{
				if(file.isDirectory())
				{
					subDirectories.add(file);
		        }
		    }
		}
		
		return subDirectories;
	}
	
	/**
	 * Gets the list of .mp3 files in the given directory
	 *   
	 * @param root Directory from which the files will be found
	 * 
	 * @return The list of all .mp3 files in the given directory
	 */
	public ArrayList<File> getFilesInDirectory(File root)
	{
		ArrayList<File> files = null;
		
		if(root.isDirectory())
		{
			files = new ArrayList<File>();
			for(File file : root.listFiles())
			{
				if(!file.isDirectory())
				{
					if(file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3"))
					{
						files.add(file);
					}
		        }
		    }
		}
		
		return files;
	}
	
	/**
	 * Updates tags of a song
	 * 
	 * @param song File object representing the song
	 * @param tags Tags that is going to be written to the file
	 * 
	 * @return true if successful, false if any error occurs
	 */
	public boolean tagSong(File song, SongTags tags)
	{
		try
		{
			MediaFile mediaFile = new MP3File(song);

	        ID3V1_0Tag id3Tags = new ID3V1_0Tag();
	        id3Tags.setArtist(tags.getArtist());
	        id3Tags.setTitle(tags.getTitle());
	        id3Tags.setAlbum(tags.getAlbum());
	        
	        mediaFile.removeTags();
	       
	        mediaFile.setID3Tag(id3Tags);
	        
	        mediaFile.sync();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Tags couldn't be written!");
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Runs the auto-tagging process
	 */
	public void run()
	{
		for(File currentArtist : getSubDirectories(new File(myArchiveDirectory)))
		{
			for(File currentAlbum : getSubDirectories(currentArtist))
			{
				for(File currentSong : getFilesInDirectory(currentAlbum))
				{
					SongTags tags = new SongTags(	currentArtist.getName(),
													extractTitleFromFileName(currentSong.getName(), "\\-", 1),
													currentAlbum.getName());
					
					System.out.println("===== UPDATING TAG =====");
					System.out.println("File\t\t: "  + currentSong.getAbsolutePath());
					System.out.print("Tags");
					System.out.println("\tArtist\t: " + tags.getArtist());
					System.out.println("\tTitle\t: " + tags.getTitle());
					System.out.println("\tAlbum\t: " + tags.getAlbum());
					System.out.println();
					
					tagSong(currentSong, tags);
				}
			}
		}
	}
}