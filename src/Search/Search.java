package Search;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DataBase.DataBase;
import DataBase.Table.ReadTable;
import DataBase.Table.Table;
import DataSorter.Filters.Filter;

public class Search implements Runnable{
	private String tableName;
	private DataBase dataBase;
	private Table table;
	private String query;
	private Filter[] filters;
	private List<String> result;

	// constructor - gets filters to search with, and a query
	// it also analyzing the needed properties
	public Search(Filter[] filters, String query) throws SQLException {
		this.result = new ArrayList<>();
		this.filters = filters;
		Classify classify = new Classify(filters, query);
		this.tableName = classify.GetTableNameByFilters();
		this.dataBase = new DataBase();
		this.query = query;
		this.table = new Table(this.dataBase, this.tableName);
	}
	
	// this function searches by the given query in the tables which have a match to it, it returns array of results
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
	
	// this function is used to search in tables that are potentially could have a match to the query
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

	@Override
	// this function is used for threading
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
