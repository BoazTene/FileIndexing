package DataSorter;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import DataBase.DataBase;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;
import DataSorter.Filters.NameFilter;


/**
 * 
 * @author Itay Bar Nissim @
 * 
 */
public class SortByFilter {

	public static void main(String[] args) throws SQLException {
		List<String> filesList = new ArrayList<>();
		listf("C:/Users/user/", filesList);
		System.out.println(filesList.size());
		Iterator<String> itr = null;
		itr = filesList.iterator();
		List<MyFile> fileNames = new ArrayList<MyFile>();
		NameFilter nameFilter = new NameFilter(filesList);
		nameFilter.addIndex();
		
//		typeFilter typeFilter = new typeFilter(filesList);
//		typeFilter.addIndex();
		
	}
	
	public static void listf(String directoryName, List<String> files) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file.getPath());
	            } else if (file.isDirectory()) {
	                listf(file.getAbsolutePath(), files);
	            }
	        }
    }
}