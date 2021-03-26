import java.sql.SQLException;

import DataBase.DataBase;
import DataBase.Table.Table;

public class Main {
	public static void main(String[] args) {
		try {
			DataBase DataBase = new DataBase("C:/Users/user/Documents/Projects/FileIndexing/db/test.db");
			System.out.println("New DataBase created!");
			String[] columns = {"firstname text", "lastName text", "bornYear int"};
			Table table = new Table(DataBase, "TableTest", columns);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
