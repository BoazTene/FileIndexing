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
	private DataBase dataBase;
	private String[][] columns = {{"value", "text"}};
	
	
	public SortByFilter(Filter[] filters, String directoryName) throws SQLException {
		this.dataBase = new DataBase("db/FirstLetter.db");

		this.files = new ArrayList<>();
		listf(directoryName);
		int index = 0	;

		for (int i = index; i < this.files.size(); i++) {
			String file = this.files.get(i);
			System.out.println(index);
			index++;
			Classify classify = new Classify(filters, file);
			System.out.println(classify.GetTableNameByFilters());
			addToTable(classify.GetTableNameByFilters(), new String[]{file});
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