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
	private String dbURl;
	private final String JDBC_URL = "jdbc:sqlite:";

	/**
	 * Class constructor.
	 * 
	 * @param filePath - the initial value of the filePath field.
	 */
	public Path(String filePath) {
		this.dbPath = filePath;
		
		setDbURL();
	}
	
	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getDbURl() {
		return dbURl;
	}

	private void setDbURL() {
		this.dbURl = this.JDBC_URL + getDbPath();
	}
}
