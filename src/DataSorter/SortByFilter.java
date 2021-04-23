package DataSorter;

import java.io.File;
import java.io.IOException;
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
import Score.Filters.LastModified.LastModified;
import Score.Filters.Owner.Owner;
import Score.Filters.ScoreFilter;
import Score.Score;
import Search.Classify;


/**
 * 
 * @author Itay Bar Nissim @
 * 
 */
public class SortByFilter extends Thread{
	private final Filter[] filters;
	private final List<String> files;
	private final DataBase dataBase;
	private static final ScoreFilter[] SCORE_FILTERS = {new LastModified(), new Owner()};
	private final boolean system;

	public SortByFilter(Filter[] filters, boolean system) throws SQLException {
		this.dataBase = new DataBase();
		this.system = system;
		this.files = new ArrayList<>();
		this.filters = filters;
	}

	private void sort() throws IOException, SQLException {
		File[] drivers = File.listRoots();
		assert drivers != null && drivers.length > 0;
		for (File driver : drivers) {
			listf(driver.getAbsolutePath());
		}

		int index = 0;

		for (int i = index; i < this.files.size(); i++) {
			String file = this.files.get(i);

			Classify classify = new Classify(filters, file);
			addToTable(classify.GetTableNameByFilters(), new String[]{file, String.valueOf(new Score(SCORE_FILTERS, file).getScore())});

			index++;
		}
	}
	
	public void addToTable(String tableName, String[] data) throws SQLException {
		Table table = new Table(this.dataBase, tableName);
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

	public void run() {
		try {
			this.sort();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void listf(String directoryName) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if (fList != null) {
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
}