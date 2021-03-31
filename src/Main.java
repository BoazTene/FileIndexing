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

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {
		SortByFilter dataSorter = new SortByFilter("C:/");
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
