package com.fileexplorer.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.fileexplorer.drives.SearchEngineConsole;
import com.fileexplorer.exceptions.InvalidDriveChoiceException;
import com.fileexplorer.exceptions.InvalidRootFinderException;

public class SearchEngineMain {

	public List<String> mainSearch(String filename) throws InvalidDriveChoiceException, InvalidRootFinderException {
		final Logger logger = Logger.getLogger(SearchEngineMain.class);
		try {
			SearchEngineConsole.displayWelcomeMessage();

			List<String> list = SearchEngineConsole.findDrives();

			list.stream().forEach(System.out::println);

			SearchEngineConsole.displaySearchResult(filename);
			String username = new com.sun.security.auth.module.NTSystem().getName();
			logger.info("searched by" + username);

		} catch (InvalidDriveChoiceException e) {
			logger.error("error in entered files");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SearchEngineConsole.displaySearchResult(filename);
	}

}
