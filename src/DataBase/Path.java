package DataBase;


/**
 * Change filePath to a sqlite format.
 * 
 * The Sqlite URLs are written in this format: "jdbc:sqlite:C:/this/is/example.db"
 * 
 * @author Boaz Tene
 *
 */
public class Path {
	private String dbPath;
	private static final String JDBC_URL_FORMAT = "jdbc:sqlite:";

	/**
	 * Class constructor.
	 * 
	 * @param filePath - the initial value of the filePath field.
	 */
	public Path(String filePath) {
		this.dbPath = filePath;

	}
	
	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getDbURl() {
		return JDBC_URL_FORMAT + getDbPath();
	}
}
