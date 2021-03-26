package DataBase;

import java.sql.Statement;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class represents a database.
 * 
 * @author Boaz Tene
 *
 */
public class DataBase {
	private Path path;
	private Connection connection;
	private Statement stmt;
	
	/**
	 * Class constructor.
	 * 
	 * Sets the path field to new Path object.
	 * 
	 * Creates/connects to the database and save the connection to the connection field.
	 * 
	 * @param dbPath - The path to the DataBase.
	 * @throws SQLException
	 */
	public DataBase (String dbPath) throws SQLException {
		this.path = new Path(dbPath);
		
		if (isDataBaseExists()) {
			this.connection = connectToDb();
		} else {
			this.connection = createDb();
		}
		
		this.stmt = this.connection.createStatement();
	}
	
	/**
	 * This method connects to existing DataBase.
	 * 
	 * @return - The connection to the DataBase
	 * @throws SQLException
	 */
	private Connection connectToDb() throws SQLException {
		return new ConnectDataBase(this.path.getDbPath()).getConnection();
	}
	
	/**
	 * This method creates + connect to non-existing DataBase.
	 * 
	 * @return - The connection to the DataBase.
	 * @throws SQLException
	 */
	private Connection createDb() throws SQLException {
		return new CreateDataBase(this.path.getDbPath()).getConnection();
	}
	
	/**
	 * This method executes an SQL command on the DataBase.
	 * 
	 * @param sql - The SQL command to execute. (String)
	 * @throws SQLException
	 */
	public void execute(String sql) throws SQLException {
		this.stmt.execute(sql);
	}
	
	/**
	 * Checks if a database is already exists.
	 * 
	 * @return true if database already exists, false if the database isn't exists.
	 */
	private boolean isDataBaseExists() {
		return new File(this.path.getDbPath()).exists();
	}
}