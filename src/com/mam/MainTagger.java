package com.mam;

import com.mam.controller.MAM;

/**
 * Main class of MAM for auto-tagging
 * 
 * @author Ezgi Hacihalil
 * @author Mehmet Akif Tutuncu
 * @author Rhahadian Bima Saputra
 */
public class MainTagger
{
	public static void main(String[] args)
	{
		MAM mam = new MAM();
		mam.autoTag("testingSet");
	}
}