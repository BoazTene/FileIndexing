package DataSorter.Filters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Filter {
	
	public void addIndex() throws SQLException;
	public void search();
	public String[] classify(String query);

}
