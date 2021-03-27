import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import DataBase.DataBase;
import DataBase.Table.ReadTable;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;

public class Main {
	public static void main(String[] args) {
//	    LinkedList<String> test = new LinkedList<String>();
//	    test.add(0);
//	    System.out.println(test);
		try {
			DataBase DataBase = new DataBase("C:/Users/user/Documents/Projects/FileIndexing/db/test.db");
			System.out.println("New DataBase created!");
			String[][] columns = {{"firstname", "text"}, {"middlename", "text"}, {"lastName", "text"}};
			Table table = new Table(DataBase, "test", columns);
			WriteTable wt = new WriteTable(table);
//			
//			String[] sColumn = {"Boaz", "Tene", "lahoh"};
//			wt.newRow(sColumn);
//			
			ReadTable rt = new ReadTable(DataBase, table);
			rt.getByColumn("middlename", "9");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
