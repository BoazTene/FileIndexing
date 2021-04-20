package DataSorter.Filters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Filter {
	
	public void addIndex() throws SQLException;
	public String[] classify(String query);
	public String getName();

}
