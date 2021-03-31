package Search;

import java.sql.SQLException;
import java.util.Arrays;

import DataBase.DataBase;
import DataBase.Table.ReadTable;
import DataBase.Table.Table;
import DataSorter.Filters.Filter;

public class Search {
	private String tableName;
	private DataBase dataBase;
	private Table table;
	private String query;
	private String[][] columns = {{"value", "text"}};

	public Search(String query, Filter[] filters) throws SQLException {
		Classify classify = new Classify(filters, query);
		this.tableName = classify.GetTableNameByFilters();
		System.out.println(this.tableName);
		this.dataBase = new DataBase("db/FirstLetter.db");
		this.query = query;
		this.table = new Table(this.dataBase, this.tableName, this.columns);
	}
	
	public String[] search() throws SQLException {
		ReadTable rt  = new ReadTable(this.dataBase, this.table);
		String[][] result = rt.getLikeByColumn("value", this.query);
		System.out.println(Arrays.deepToString(result));
		return null;
	}
}
