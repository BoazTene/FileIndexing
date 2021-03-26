package DataBase.Table;

import java.sql.SQLException;

import DataBase.DataBase;


/**
 * This Class represents Table.
 * 
 * @author Boaz Tene
 *
 */
public class Table {
	private DataBase dataBase;
	private String dbName;
	private String[] columns;
	
	/**
	 * Class constructor for an existing table.
	 * 
	 * @param dataBase - The dataBase Object.
	 * @param name - The Database name.
	 */
	public Table(DataBase dataBase, String name) {
		this.dataBase = dataBase;
		this.dbName = name;
	}
	
	/**
	 * Class constructor for non-existing table.
	 * 
	 * 
	 * @param dataBase - The dataBase Object.
	 * @param name - The DataBase name.
	 * @param columns - String array of each column for example: ["id integer", "name text"]
	 * @throws SQLException
	 */
	public Table(DataBase dataBase, String name, String[] columns) throws SQLException {
		this.dataBase = dataBase;
		this.dbName = name;
		this.columns = columns;
		
		createTable();
	}
	
	/**
	 * This method creates a new table inside the DataBase.
	 * 
	 * <br>
	 * 
 	 * <a href="https://www.w3schools.com/sql/sql_create_table.asp">Checkout</a> for more details about Table Creation.
	 * 
	 * @throws SQLException
	 */
	public void createTable() throws SQLException {
		String sql = String.format("CREATE TABLE IF NOT EXISTS %s (\n", this.dbName);
		
		for (int i = 0; i < this.columns.length; i++) {
			sql += this.columns[i];
			
			if (i+1 < this.columns.length) sql += ",";
			
			sql += "\n";
		}
		
		sql += ");";
		
		System.out.println(sql);
		
		this.dataBase.execute(sql);
	}
}
