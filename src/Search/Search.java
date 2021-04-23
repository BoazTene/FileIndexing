package Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DataBase.DataBase;
import DataBase.Table.ReadTable;
import DataBase.Table.Table;
import DataSorter.Filters.Filter;


/**
 * This class is the main Search class.
 * The class can search through the entire database a file.
 *
 * @author Boaz Tene
 */
public class Search implements Runnable {
	private final String tableName;
	private final DataBase dataBase;
	private final Table table;
	private final String query;
	private final List<String> result;


	/**
	 * constructor - gets filters to search with, and a query
	 * it also analyzing the needed properties.
	 *
	 * @param filters
	 * @param query
	 * @throws SQLException
	 */
	public Search(Filter[] filters, String query) throws SQLException {
		this.result = new ArrayList<>();
		Classify classify = new Classify(filters, query);
		this.tableName = classify.GetTableNameByFilters();
		this.dataBase = new DataBase();
		this.query = query;
		this.table = new Table(this.dataBase, this.tableName);
	}

	/**
	 * this function searches by the given query in the tables which have a match to it, it returns array of results
	 *
	 * @return
	 * @throws SQLException
	 */
	public String[] search() throws SQLException {
		ReadTable rt  = new ReadTable(this.dataBase, this.table);

//		if (this.tableName.split(this.filters[this.filters.length-1].getName(), 2)[1].equals("")) {
		List<String> results = new ArrayList<String>(Arrays.asList(rt.getLikeByColumn("value", this.query)));
		List<String> temp = Arrays.asList(searchLikeTables());
		for (String x : temp){
			   if (!results.contains(x))
			      results.add(x);
			   	  this.result.add(x);
		}


		return results.toArray(new String[0]);
	}

	/**
	 * this function is used to search in tables that are potentially could have a match to the query
	 *
	 * @return
	 * @throws SQLException
	 */
	public String[] searchLikeTables() throws SQLException {
		System.out.println(this.tableName);
		String[] tables = this.dataBase.getTables();

		for (int i = 0; i < tables.length; i++) {
			try {
				if (tables[i].contains(this.tableName) || tables[i].contains(this.tableName.split("ext")[1]) ) {
					Table table = new Table(this.dataBase, tables[i]);
					ReadTable rt  = new ReadTable(this.dataBase, table);
					this.result.addAll(Arrays.asList(rt.getLikeByColumn("value", this.query)));
				}
			} catch (Exception e) {
				
			}
			
		}

		return (String[]) this.result.toArray(new String[0]);
	}

	/**
	 * this method is used for threading
	 */
	@Override
	public void run() {
		try {
			search();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	
	public String getLastResult(){
		try {
			String result = this.result.get(0);
			this.result.remove(0);

			return result;
		} catch (Exception e) {
			return "";
		}

	}
}
