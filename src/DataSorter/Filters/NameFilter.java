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
			char firstChar = thisFile.substring(thisFile.lastIndexOf("\\")+1).charAt(0);
			extensionFilter = new ExtensionFilter(String.valueOf(firstChar), thisFile);
			extensionFilter.addIndex();
			System.out.println(extensionFilter.getTableName());
			int i = 0;
			while(true) {
				i++;
				try {
					
					addToTable(extensionFilter.getTableName(), data);
					break;
				} catch (Exception e) {
					try {
						firstChar = thisFile.substring(thisFile.lastIndexOf("\\")+1).charAt(i);
					} catch (Exception er) {
						addToTable("onlyNumbers", data);
						break;
					}
				}
			}
			
		}

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}
}
