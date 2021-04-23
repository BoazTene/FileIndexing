//package DataBase;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
///**
// * Creates new sqlite database if its not already exists.
// * A connection to the Database will be stored at the connection field.
// *
// * @author Boaz Tene
// *
// */
//public class CreateDataBase {
//	private final Path path;
//	private final Connection connection;
//
//	/**
//	 * Class constructor.
//	 *
//	 * @param dbPath - The path to the new database.
//	 * @throws SQLException
//	 */
//	public CreateDataBase(String dbPath) throws SQLException {
//		this.path = new Path(dbPath);
//
//		this.connection = create();
//	}
//
//	/**
//	 * This method creates new database on the specified path.
//	 *
//	 * @return - Returns the connection to the new database.
//	 * @throws SQLException
//	 */
//	private Connection create() throws SQLException {
//		return DriverManager.getConnection(this.path.getDbURl());
//	}
//
//	/**
//	 * Checks if a database is already exists.
//	 *
//	 * @return true if database already exists, false if the database isn't exists.
//	 */
//	private boolean isDataBaseExists() {
//		return new File(this.path.getDbPath()).exists();
//	}
//
//	public Connection getConnection() {
//		return connection;
//	}
//}
