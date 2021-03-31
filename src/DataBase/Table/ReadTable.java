package DataBase.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;

import DataBase.DataBase;


/**
 * This class is used to read data from table.
 * 
 * @author Boaz Tene
 *
 */
public class ReadTable {
	private DataBase db;
	private Statement stmt;
	private Table table;
	
	/**
	 * This is class constructor.
	 * 
	 * Its initializing the db, tablel, stmt fields.
	 * 
	 * @param db - A DataBase instance.
	 * @param table - A Table instance.
	 * @throws SQLException
	 */
	public ReadTable(DataBase db, Table table) throws SQLException {
		this.db = db;
		this.table = table;
		this.stmt = this.db.getConnection().createStatement();
	}
	
	/**
	 * This method returns an array of every row that matches the given conditon.
	 * 
	 * @param column - The column name.
	 * @param value - The value of the column name.
	 * @return
	 * @throws SQLException
	 */
	public String[][] getByColumn(String column, String value) throws SQLException {
		int count = getRowCount(column, value);
		
		ResultSet rs = this.stmt.executeQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
				this.table.getDbName(), column, value));
		
		int columnCount = rs.getMetaData().getColumnCount();

		String[][] result = new String[count][columnCount];

		rs.next();

		for (int i = 1; i <= count; i++) {
			for (int j = 1; j <= columnCount; j++) {
				result[i-1][j-1] = rs.getString(j);
			}

			rs.next();
		}

		return result;
	}
	
	
	/**
	 * This method returns an array of every row that matches the given conditon.
	 * 
	 * @param column - The column name.
	 * @param value - The value of the column name.
	 * @return
	 * @throws SQLException
	 */
	public String[][] getLikeByColumn(String column, String value) throws SQLException {
		int count = getRowCount(column, value);
		
//		ResultSet rs = this.stmt.executeQuery(String.format("SELECT * FROM %s WHERE %s = '%s'",
//				this.table.getDbName(), column, value));
		PreparedStatement ps = this.table.getDataBase().getConnection().prepareStatement(
				String.format("SELECT * FROM %s WHERE %s LIKE ?",
						this.table.getDbName(), column));
		ps.setString(1, "%" + value + "%");
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString("value"));
		}
		
		return null;
	}
	
	/**
	 * This method return the number of rows that matches a specific condition.
	 * 
	 * @param column - The column name.
	 * @param value - The value of the column name.
	 * @return 
	 * @throws SQLException
	 */
	private int getRowCount(String column, String value)  {
		int count = 0;
		ResultSet res;
		try {
			res = this.stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE %s = '%s'",
					this.table.getDbName(), column, value));
			 while (res.next()){
	            count = res.getInt(1);
	        }
		        
	        res.close();
		        
		} catch (SQLException e) {
			System.out.println("Column: " + column + " Value: " + value + " Sql: " + String.format("SELECT COUNT(*) FROM %s WHERE %s = '%s'",
					this.table.getDbName(), column, value));
			
			e.printStackTrace();
		}
        
       
        return count;
	}
}
