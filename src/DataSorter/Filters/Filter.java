package DataSorter.Filters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/* this is an interface which is used for building each filter
 * @author -  itay Bar Nissim
 */ 
public interface Filter {
	
	public void addIndex() throws SQLException;
	public String[] classify(String query);
	public String getName();

}
