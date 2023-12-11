package com.fileexplorer.drives;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import com.fileexplorer.search.SearchManagerFactory;
import com.fileexplorer.searchhistory.SearchHistoryManager;
public class SearchEngineConsole {
	// display a welcome message
  static Properties prop=new Properties();
	public static void displayWelcomeMessage() {
		System.out.println("Welcome to our search engine");
	}
	// Identify the drives
	public static List<String> findDrives() {
		try {
			prop.load(new FileInputStream("C:\\Users\\rayala.akhil\\eclipse-workspace\\Search_Engine_Project\\src\\Data.properties"));
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		List<String> drives = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Choice: ALL or ACTIVE");
		drives = DriveFinderFactory.create(prop.getProperty("choice1")).findDrives();
		return drives;
	}
	// Display the Drives
	// Display Search Result
	public static List<String> displaySearchResult(String hi) {
		String fileName = hi;
		List<String> drives = new ArrayList<>();
		Scanner sc1 = new Scanner(System.in);
		/*
		 * System.out.println("Enter file name"); Scanner sc=new Scanner(System.in);
		 * fileName = sc.nextLine();
		 */
		/* System.out.println("Mention the number of drives to search from"); */
		int num =Integer.parseInt(prop.getProperty("num1")) ;
	//	System.out.println("Mention the name of drives to search from");
		for (int i = 0; i < num; i++) {
			String s = prop.getProperty("drives1");
			drives.add(s.concat(":\\"));
		}

		return searchForFile(fileName, drives);
	}

	// search for a file
	// implement multi threading

	public static List<String> searchForFile(String fileName, List<String> drives) {
		List<String> listOfDirectory = new ArrayList<>();
		List<String> searchFromLog = searchLogCheck(fileName);
  
		if(searchFromLog.size()==0) {
			listOfDirectory = SearchManagerFactory.create().search(fileName, drives);
			SearchHistoryManager sh = new SearchHistoryManager();
			sh.logSearchResult(listOfDirectory);
			return listOfDirectory;
			}
		else {
			return searchFromLog;
		}
	}
	public static List<String> searchLogCheck(String fileName){
		SearchHistoryManager sh =new SearchHistoryManager();
		List<String> logs= sh.logCheck(fileName);
		return logs;
	}
	// Search Result Logger using a text file
	// Optimise your code such that if a file is already present in the logger text
	//file then don't do multi threading instead give result from your text file.
}
