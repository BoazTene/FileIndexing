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
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Classify;
import Search.Search;


/**
 * 
 * @author Itay Bar Nissim @
 * 
 */
public class SortByFilter {
	private List<String> files;
	
//	public static void main(String[] args) throws SQLException {
//		List<String> filesList = new ArrayList<>();		
//		listf("C:/", filesList);
//		System.out.println(filesList.size());
//		Iterator<String> itr = null;
//		itr = filesList.iterator();
//		List<MyFile> fileNames = new ArrayList<MyFile>();
//		
//		NameFilter nameFilter = new NameFilter(filesList);
//		nameFilter.addIndex();
//		
////		Filter[] filters = new Filter[1];
////		filters[0] = new NameFilter();
//		
////		Search search = new Search("אמ", filters);
////		search.search();
////		typeFilter typeFilter = new typeFilter(filesList);
////		typeFilter.addIndex();
//		
//	}
	
	public SortByFilter(String directoryName) throws SQLException {
		this.files = new ArrayList<>();
		listf(directoryName);
		
		NameFilter nameFilter = new NameFilter(this.files);
		nameFilter.addIndex();
	}
	
	public void listf(String directoryName) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file.getPath());
	            } else if (file.isDirectory()) {
	            	files.add(file.getPath());
	                listf(file.getAbsolutePath());
	            }
	        }
    }
}