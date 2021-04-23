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
	private String[][] columns = {{"value", "text"}, {"score", "integer"}};
	private final String name = "fl";

	// constructor - gets file list and analyzing the properties
	public NameFilter(List<String> filesList) throws SQLException {
		this.filesList = filesList;
		this.dataBase = new DataBase("db/DataBase.db");

	}
	
	// Constructor -  which analyzes the properties (the data base)
	public NameFilter() throws SQLException {
		this.dataBase = new DataBase("db/DataBase.db");
	}
	
	// this function gets an table name and an array with the values of all the columns
	public void addToTable(String tableName, String[] data) throws SQLException {
		Table table = new Table(this.dataBase, tableName, this.columns);
		WriteTable wt = new WriteTable(table);
		wt.newRow(data);
	}
	
	@Override
	
	public void addIndex() throws SQLException {
		Iterator<String> itr = null;
		ExtensionFilter extensionFilter;
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



	// this function gets an char and arrays of chars, it returns the index of the char in the array
	public int getIndexOf(char toSearch, char[] tab ) {
	  for( int i=0; i< tab.length ; i ++ )
	    if( tab[ i ] == toSearch)
	     return i;

	  return 0;
	}
	
	// this function gets a char, and returns its name (in words)
	public String numberToStringNumber(char chr) {
		final char[] unacceptableChars = {
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', '/', '?',
				'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
				'.', '>', ',', '<', ';', ':', '\'', '"'};
		
		final String[] values = {
				"one", 
				"two",
				"three",
				"four",
				"five",
				"six",
				"seven",
				"eight",
				"nine",
				"zero",
				"hyphen",
				"dash",
				"equal",
				"plus",
				"obrackets",
				"obraces",
				"cbrackets",
				"cbraces",
				"backslash",
				"vertical",
				"slash",
				"quation",
				"ampersand",
				"exclamation",
				"atsign",
				"hashtag",
				"dolar",
				"precentage",
				"power",
				"and",
				"mul",
				"oparentheses",
				"cparentheses",
				"",
				"bigger",
				"comma",
				"less",
				"semicolon",
				"colon",
				"apostrophe",
				"ellipsis"
		};
		
		
		
		
		try {
			return values[getIndexOf(chr, unacceptableChars)];
		} catch (Exception e) {
			return "";
		}		
	}
	
	@Override
	/**
	 * The method gets a path to file and returns the name of the table which it contains.
	 * 
	 * @param query - A Path to a file.
	 * @return - A array of the classify result and the filter exentison: 'fl' (First Letter)
	 */
	public String[] classify(String query) {
		String result = "none";
		
		char[] unacceptableChars = {
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', '/', '?',
				'~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
				'.', '>', ',', '<', ';', ':', '\'', '"'};	
		char firstChar = query.substring(query.lastIndexOf("\\")+1).charAt(0);
		
		if(charContains(unacceptableChars, firstChar)) {
			result = (String) numberToStringNumber(firstChar);
		}

		
		if (result == "none") {
			result = String.valueOf(firstChar);
		} 
		
		return new String[]{result, "fl"};
	}
	// this function gets an array of chars and a char, it returns if the given char is in the given array
	private boolean charContains(char[] array, char target) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target) return true;
		}
		
		return false;
	}

	@Override
	// this function returns the name of the filter
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
