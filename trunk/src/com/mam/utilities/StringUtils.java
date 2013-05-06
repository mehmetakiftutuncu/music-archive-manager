package com.mam.utilities;

import java.io.File;

/**
 * StringUtils is the utility class for handling string operations of MAM
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class StringUtils
{
	/**
	 * Extracts the title of the song from the given file name
	 * 
	 * @param fileName File name from which the title will be extracted
	 * @param separator Pattern that separates tokens in the file name
	 * @param tokenIndex Indicates which token is the title in file name
	 * 
	 * @return Title of the song extracted from the given file name
	 */
	public static String extractTitleFromFileName(String fileName, String separator, int tokenIndex)
	{
		if(fileName == null)
		{
			throw new IllegalArgumentException("File name must not be null!");
		}
		
		if(separator == null || separator.equals(""))
		{
			throw new IllegalArgumentException("Separator must not be empty or null!");
		}
		
		if(tokenIndex < 0)
		{
			throw new IllegalArgumentException("Token index must be greater than 0!");
		}
		
		String result = "";
		
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
	 * Gets the name/extension of the file
	 * 
	 * @param file File object from which the name/extension is going to be read
	 * @param isName Control flag
	 * 
	 * @return If isName is true, name of the file;
	 *         if isName is false, extension of the file; null if the file doesn't have any extension
	 */
	public static String getNameOrExtension(File file, boolean isName)
	{
		if(file.getName().contains("."))
		{
			int end = file.getName().lastIndexOf(".");
			
			if(isName)
			{
				return file.getName().substring(0, end);
			}
			else
			{
				return file.getName().substring(end, file.getName().length());
			}
		}
		
		return null;
	}
}