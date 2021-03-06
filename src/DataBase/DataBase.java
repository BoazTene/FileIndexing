package DataBase;

import java.nio.file.Paths;
import java.sql.Statement;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents a database.
 * 
 * @author Boaz Tene
 *
 */
public class DataBase {
	private final Path path;
	private final Connection connection;
	private final Statement stmt;
	private final java.nio.file.Path dbPath;
	

	/**
	 * Class constructor.
	 * 
	 * Sets the path field to new Path object.
	 * 
	 * Creates/connects to the database and save the connection to the connection
	 * field.
	 * 
	 * @param dbPath - The path to the DataBase.
	 * @throws SQLException
	 */


	public DataBase (String dbPath) throws SQLException {
		this.dbPath = Paths.get(dbPath);

		this.path = new Path(dbPath);

		if (isDataBaseExists()) {
			this.connection = connectToDb();
		} else {
			this.connection = createDb();
		}

		this.stmt = this.connection.createStatement();
	}

	public String[] getTables() throws SQLException {
		DatabaseMetaData metaData = this.connection.getMetaData();
		String[] types = { "TABLE" };
		// Retrieving the columns in the database
		ResultSet tables = metaData.getTables(null, null, "%", types);

		int size = 0;
		while (tables.next()) {
			size++;
		}

		tables = metaData.getTables(null, null, "%", types);
		tables.next();

		String[] result = new String[size];

		for (int i = 0; i < size; i++) {
			result[i] = tables.getString("TABLE_NAME");
			tables.next();
		}

		return result;
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

	public Connection getConnection() {
		return this.connection;
	}

	public String getPath() {
		return this.dbPath.toAbsolutePath().toString();
	}
}