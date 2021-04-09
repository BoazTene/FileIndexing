import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		
		
		Filter[] filters = new Filter[2];
		filters[0] = new NameFilter();
		filters[1] = new ExtensionFilter();
		
		
//		Path path = Paths.get("C:/Users/user/Documents/Projects/FileIndexing");
//		WatchDir watchDir = new WatchDir(path);
//		Entry entry = new EntryEvents();
//		watchDir.processEvents(entry, null);
//		System.out.println(Arrays.toString(filters[1].classify("config")));
		SortByFilter dataSorter = new SortByFilter(filters, "C:\\Users\\User\\Documents\\לימודים\\Hello1000");
		
		
//		Search search = new Search(filters, "Maze");
//		System.out.println("Results:");
//		String[] result = search.search();
//		
//		for (int i = 0; i < result.length; i++) {
//			System.out.println((i+1) + ". " + result[i]);
//		}
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