import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import DataSorter.FileTracker.QuickScan.QuickScan;
import com.sun.security.auth.UserPrincipal;

import DataBase.DataBase;
import DataBase.Table.ReadTable;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;
import DataSorter.SortByFilter;
import DataSorter.FileTracker.*;
import DataSorter.FileTracker.EntryHandlers.Entry;
import DataSorter.Filters.ExtensionFilter;
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Search;
import DataSorter.FileTracker.*;
import DataSorter.FileTracker.QuickScan.EntryHandler;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {
	public static boolean isAdmin() {
	    String groups[] = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
	    for (String group : groups) {
	        if (group.equals("S-1-5-32-544"))
	            return true;
	    }
	    return false;
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		
		Filter[] filters = new Filter[2];
		filters[0] = new NameFilter();
		filters[1] = new ExtensionFilter();

		QuickScan quickScan = new QuickScan(filters,"db/FirstLetter.db", "C:/Users/user/Documents/Projects/FileIndexing");
		quickScan.start();
////		Path path = Paths.get("C:/Users/user/Documents/Projects/FileIndexing");
////		WatchDir watchDir = new WatchDir(path);
////		Entry entry = new EntryEvents();
////		watchDir.processEvents(entry, null);
////		System.out.println(Arrays.toString(filters[1].classify("config")));
//		SortByFilter dataSorter = new SortByFilter(filters, "C:\\Windows\\Logs", false);
		
		String query = "";
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 100; i++) {
			System.out.println("");
		}
		
		while (!query.equals("exist")) {
			System.out.print("Enter Query: ");
			query = scanner.nextLine();
			
			Search search = new Search(filters, query);
			System.out.println("Results:");
			String[] result = search.search();
			
			for (int i = 0; i < result.length; i++) {
				System.out.println((i+1) + ". " + result[i]);
			}
			
			System.out.print("Press Enter to search again...");
			scanner.nextLine();
			
			for (int i = 0; i < 100; i++) {
				System.out.println("");
			}
		}
		
//		System.out.println(Arrays.deepToString));
//	    LinkedList<String> test = new LinkedList<String>();
//	    test.add(0);
//	    System.out.println(test);

//		List<String> files = new ArrayList<>();
//		listf("C:/Users/user/Documents/Projects/FileIndexing", files);
//		System.out.println(files.size());
//		System.out.println(Arrays.toString(files.toArray()));
	

	}

	public static void listf(String directoryName, List<String> files) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null)
			for (File file : fList) {
				if (file.isFile()) {
					files.add(file.getPath());
				} else if (file.isDirectory()) {
					listf(file.getAbsolutePath(), files);
				}
			}
	}
}


class EntryEvents implements Entry {

	@Override
	public void newEntry(Kind kind, Path path) {
		// TODO Auto-generated method stub
		System.out.println("Path: " + path.getFileName() + ", Kind: " + kind);
	}
	
}