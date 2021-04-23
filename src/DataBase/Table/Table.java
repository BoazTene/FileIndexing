package DataBase.Table;

import java.sql.SQLException;

import org.sqlite.SQLiteErrorCode;

import DataBase.DataBase;


/**
 * This Class represents Table.
 * 
 * @author Boaz Tene
 *
 */
public class Table {
	public static String[][] columns = {{"value", "text"}, {"score", "integer"}};

	private DataBase dataBase;
	private String dbName;

	/**
	 * Class constructor.
	 * 
	 * 
	 * @param dataBase - The dataBase Object.
	 * @param name - The DataBase name.
	 * @throws SQLException
	 */
	public Table(DataBase dataBase, String name) throws SQLException {
		this.dataBase = dataBase;
		this.dbName = name;

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
	private void createTable() throws SQLException {
		String sql = String.format("CREATE TABLE IF NOT EXISTS %s (\n", dbName);
		
		for (int i = 0; i < columns.length; i++) {
			sql += columns[i][0] + " " + columns[i][1];
			
			if (i+1 < columns.length) sql += ",";
			
			sql += "\n";
		}
		
		sql += ");";
		
		try {
			this.dataBase.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(sql);
			throw new SQLException("Eror Table 61");
		}
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String[][] getColumns() {
		return columns;
	}

	public void setColumns(String[][] columns) {
		columns = columns;
	}

	public DataBase getDataBase() {
		return dataBase;
	}

}
