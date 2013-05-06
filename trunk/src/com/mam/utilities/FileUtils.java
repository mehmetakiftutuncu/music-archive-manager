package com.mam.utilities;

import java.io.File;
import java.util.ArrayList;

import com.mam.model.SongTags;

/**
 * FileUtils is the utility class for handling file operations of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class FileUtils
{
	/**
	 * Gets the list of sub directories of the given directory
	 *   
	 * @param root Directory from which the sub directories will be found
	 * 
	 * @return The list of sub directories of the given directory
	 */
	public static ArrayList<File> getSubDirectories(File root)
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
	public static ArrayList<File> getFilesInDirectory(File root)
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
	 * Updates the file name according to tags and the directory of the given file
	 * 
	 * @param currentArtist File object representing the artist (outer-most folder in the archive)
	 * @param tags Tags of the current song
	 * @param currentAlbum File object representing the album (middle folder in the archive)
	 * @param currentSong File object representing the song (inner-most folder in the archive)
	 * 
	 * @return A reference to the new file, null if any error occurs
	 */
	public static File updateFileName(File currentSong, SongTags tags, File currentArtist, File currentAlbum)
	{
		String path = currentSong.getAbsolutePath();
		int index = path.lastIndexOf(currentSong.getName());
		path = path.substring(0, index);
		
		String name = "";
		if(tags.getArtist() != null)
		{
			name = tags.getArtist();
		}
		else
		{
			name = currentArtist != null ? currentArtist.getName() : "";
		}
		name += " - " + tags.getTitle() + StringUtils.getNameOrExtension(currentSong, false);
		
		File newFile = new File(path + name);
		
		if(currentSong.renameTo(newFile))
		{
			return newFile;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Moves a file
	 * 
	 * @param source Source file
	 * @param destinationPath Destination path
	 * @param newFileName New name of the file
	 * 
	 * @return true if successful, false if any error occurs
	 */
	public static boolean moveFile(File source, File destinationPath, String newFileName)
	{
		if(!destinationPath.exists())
		{
			if(destinationPath.mkdirs())
			{
				if(source.renameTo(new File(destinationPath.getAbsolutePath() + "/" + newFileName)))
				{
					return true;
				}
			}
		}
		else
		{
			if(source.renameTo(new File(destinationPath.getAbsolutePath() + "/" + newFileName)))
			{
				return true;
			}
		}
		
		return false;
	}
}