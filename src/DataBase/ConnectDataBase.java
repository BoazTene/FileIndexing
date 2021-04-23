//package DataBase;
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
///**
// * This Class connects to Existing Database by path.
// *
// * @author Boaz Tene
// *
// */
//public class ConnectDataBase {
//	private Connection connection;
//	private Path path;
//
//	/**
//	 * Class constructor.
//	 *
//	 * Sets the path and connection fields.
//	 *
//	 * @param dbPath - The path to the database
//	 * @throws SQLException
//	 */
//	public ConnectDataBase(String dbPath) throws SQLException {
//		this.path = new Path(dbPath);
//
//		if (isDataBaseExists()) {
//			this.connection = connect();
//		}
//	}
//
//	/**
//	 * This method connects to the database.
//	 *
//	 * @return - The connection to the database.
//	 * @throws SQLException
//	 */
//	private Connection connect() throws SQLException {
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
