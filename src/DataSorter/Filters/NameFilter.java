package DataSorter.Filters;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import DataBase.DataBase;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;


/**
 * 
 * Filter alphabetic order by first letter.
 * 
 * 
 * @author Itay Bar Nissim
 *
 */
public class NameFilter implements Filter{
	private List<String> filesList;
	private DataBase dataBase;
	private String[][] columns = {{"value", "text"}};

	public NameFilter(List<String> filesList) throws SQLException {
		this.filesList = filesList;
//		this.dataBase = new DataBase("C:/Users/user/Documents/Projects/FileIndexing/db/FirstLetter.db");
		this.dataBase = new DataBase("db/FirstLetter.db");

	}
	
	public NameFilter() throws SQLException {
		this.dataBase = new DataBase("db/FirstLetter.db");
	}
	
	public void addToTable(String tableName, String[] data) throws SQLException {
		Table table = new Table(this.dataBase, tableName, this.columns);
		WriteTable wt = new WriteTable(table);
		wt.newRow(data);
	}
	
	@Override
	public void addIndex() throws SQLException {
		Iterator<String> itr = null;
		itr = filesList.iterator();
		int lahoh = 0;
		
		while (itr.hasNext()) {
			String thisFile = itr.next();
			String[] data = {thisFile};
			lahoh++;
			System.out.println("lahoh: " + lahoh);
			addToTable(classify(thisFile)[0], data);
		}

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}
	
	@Override
	/**
	 * The method gets a path to file and returns the name of the table which it contains.
	 * 
	 * @param query - A Path to a file.
	 * @return - A array of the classify result and the filter exentison: 'fl' (First Letter)
	 */
	public String[] classify(String query) {
		String result = "";
		
		char[] unacceptableChars = {
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', '/', '?',
				'~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
				'.', '>', ',', '<', ';', ':', '\'', '"'};
		
		char firstChar = query.substring(query.lastIndexOf("\\")+1).charAt(0);
		int index = 1;
		while (charContains(unacceptableChars, firstChar)) {
			try {
				firstChar = query.substring(query.lastIndexOf("\\")+1).charAt(index);
			} catch (Exception e) {
				result = "onlyNumbers";
				break;
			}
			index++;
		}
		
		if (result == "") {
			result = String.valueOf(firstChar);
		} 
		
		return new String[]{result, ""};
	}
	
	private boolean charContains(char[] array, char target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) return true;
		}
		
		return false;
	}
}
