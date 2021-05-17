package DataBase.Table;


import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataBase.DataBase;


/**
 * This class is used to write data to the table.
 * 
 * @author Boaz Tene
 *
 */
public class WriteTable {
	private final Table table;
	private final PreparedStatement pstmt;
	private static final int tryAfterFailed = 50;
	private static final int sleepBetweenTries = 500;
	
	/**
	 * This is class constructor.
	 * 
	 * @param table - A Table instance.
	 * @throws SQLException
	 */
	public WriteTable (Table table) throws SQLException { 
		this.table = table;
		this.pstmt = preparedStatement();
	}
	
	/**
	 * This method prepare the Statement to write,
	 * to be able to write to the database you need to create prepare Statment.
	 * 
	 * The Prepare statement has the table name, the names of the columns and number of values to write.
	 * 
	 * @return - A PreparedStatement instance.
	 * @throws SQLException
	 */
	private PreparedStatement preparedStatement() throws SQLException {
		String columns = "";
		String values = "";
		
		for (int i = 0; i < table.getColumns().length; i++) {
			if (i != 0) columns += ",";
			if (i != 0) values += ",";

			columns += table.getColumns()[i][0];
			values += "?";
		}
		
		String sql = String.format("INSERT INTO %s(%s) VALUES (%s)", this.table.getDbName(), columns, values);
		
		DataBase db = this.table.getDataBase();
		return db.getConnection().prepareStatement(sql);
	}

	public void deleteRow(String[] sColumn) throws SQLException {
		String sql =String.format("DELETE FROM %s WHERE %s = ?;", this.table.getDbName(), this.table.getColumns()[0][0]);

		PreparedStatement pstmt = this.table.getDataBase().getConnection().prepareStatement(sql);
		pstmt.setString(1, sColumn[0]);
		pstmt.executeUpdate();
	}

	/**
	 * This method adds row to the table.
	 * 
	 * @param sColumn - Array, whom elements are the value of each column.
	 * @throws SQLException
	 */
	public void newRow(String[] sColumn) throws SQLException {
		ReadTable rt = new ReadTable(this.table.getDataBase(), this.table);
		if (rt.getByColumn(this.table.getColumns()[0][0], sColumn[0]).length != 0 ) {
	        String sql =String.format("DELETE FROM %s WHERE %s = ?;", this.table.getDbName(), this.table.getColumns()[0][0]);

	        PreparedStatement pstmt = this.table.getDataBase().getConnection().prepareStatement(sql);
	        pstmt.setString(1, sColumn[0]);
	        pstmt.executeUpdate();
		}
		
		for (int i = 0; i < sColumn.length; i++) {
			this.pstmt.setString(i+1, sColumn[i]);
		}

		for (int i = 0; i < tryAfterFailed; i++) {
			try {
				this.pstmt.executeUpdate();
				break;
			} catch (SQLException e) {
				try {
					Thread.sleep(sleepBetweenTries);
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
			}
		}

	}
}
