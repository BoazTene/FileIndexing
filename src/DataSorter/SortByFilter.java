package DataSorter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.sql.SQLException;
import java.util.*;

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
	private DataBase dataBase;

	private String[][] columns = {{"value", "text"}, {"score", "integer"}} ;
	private final boolean system;

	
	
	public SortByFilter(Filter[] filters, String directoryName, boolean system) throws SQLException {
		this.dataBase = new DataBase("db/FirstLetter.db");
		this.system = system;
		this.files = new ArrayList<>();
		listf(directoryName);
		int index = 0	;

		for (int i = index; i < this.files.size(); i++) {
			String file = this.files.get(i);
			
			System.out.println(index);
			index++;
			
			Classify classify = new Classify(filters, file);
			System.out.println(classify.GetTableNameByFilters());
			Random r = new Random();
			addToTable(classify.GetTableNameByFilters(), new String[]{file, String.valueOf((char)(r.nextInt(26) + 'a'))});
		}
//		for (String file : this.files) {
//			System.out.println(index);
//			index++;
//			Classify classify = new Classify(filters, file);
//			System.out.println(classify.GetTableNameByFilters());
//			addToTable(classify.GetTableNameByFilters(), new String[]{file});
//		}
	}
	
	public void addToTable(String tableName, String[] data) throws SQLException {
		Table table = new Table(this.dataBase, tableName, this.columns);
		WriteTable wt = new WriteTable(table);
		wt.newRow(data);
	}
	
	private String getOwnerName(String filePath) {
		Path path = Paths.get(filePath);
		  
        // Create object having the file attribute
        FileOwnerAttributeView file = Files.getFileAttributeView(path, 
                                        FileOwnerAttributeView.class);
  
        // Exception Handling to avoid any errors
        try {
            // Taking owner name from the file
            java.nio.file.attribute.UserPrincipal user = file.getOwner();
  
            // Printing the owner's name
            return user.getName().split("\\\\", 2)[1];
        } catch (Exception e) {
            return "";
        }
	}
	
	public void listf(String directoryName) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	            	if (!this.system & getOwnerName(file.getName()).equals("SYSTEM")) {
	    				continue;
	            	}
	                files.add(file.getPath());
	            } else if (file.isDirectory()) {
	            	files.add(file.getPath());
	                listf(file.getAbsolutePath());
	            }
	        }
    }
}