package DataSorter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

interface Filter {
	
	public void addIndex() throws SQLException;
	public void search();

}
