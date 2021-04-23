package DataBase;

import java.nio.file.Paths;
import java.sql.*;

/**
 * This class represents a database.
 * 
 * @author Boaz Tene
 *
 */
public class DataBase {
	public static final String dbPath = "db/DataBase.db";
	private final Path path;
	private final Connection connection;
	private final Statement stmt;

	/**
	 * Class constructor.
	 * 
	 * Sets the path field to new Path object.
	 * 
	 * Creates/connects to the database and save the connection to the connection
	 * field.
	 *
	 * @throws SQLException
	 */
	public DataBase () throws SQLException {
		this.path = new Path(dbPath);


		this.connection = createDb();

		this.stmt = this.connection.createStatement();
	}

	// this function returns all the tabeles's names
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
	 * This method creates + connect to non-existing DataBase.
	 * 
	 * @return - The connection to the DataBase.
	 * @throws SQLException
	 */
	private Connection createDb() throws SQLException {
		return DriverManager.getConnection(this.path.getDbURl());
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

	// this function returns the connection
	public Connection getConnection() {
		return this.connection;
	}

	// this function returns the path of the data base
	public String getPath() {
		return Paths.get(dbPath).toAbsolutePath().toString();
	}
}