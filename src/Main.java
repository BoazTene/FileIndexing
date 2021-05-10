
 
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import DataSorter.FileTracker.QuickScan.QuickScan;
import DataSorter.SortByFilter;
import DataSorter.Filters.ExtensionFilter;
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Search;
import gui.GFrame;
import gui.Gui;
import gui.frames.SearchFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

	public static boolean sAdmin() {
	    String[] groups = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
	    for (String group : groups) {
	        if (group.equals("S-1-5-32-544"))
	            return true;
	    }
	    return false;
	}

	public static void main(String[] args) throws SQLException, IOException, InterruptedException {

		Filter[] filters = new Filter[2];
		filters[0] = new NameFilter();
		filters[1] = new ExtensionFilter();
		String path = "db/DataBase.db";

		Gui gui = new Gui();
		GFrame search = new SearchFrame();
		gui.setFrame(search);

		hardScan(filters);
		quickScan(filters);
		
		


	}

	public static void quickScan(Filter[] filters) throws SQLException, IOException {
		QuickScan quickScan = new QuickScan(filters);

		quickScan.start();
	}

	public static void hardScan(Filter[] filters) throws SQLException {
		new SortByFilter(filters, false).start();
	}

}