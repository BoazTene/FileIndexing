import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import DataBase.Table.SortTable;
import DataSorter.FileTracker.QuickScan.QuickScan;
import DataBase.DataBase;
import DataBase.Table.Table;
import DataSorter.SortByFilter;
import DataSorter.Filters.ExtensionFilter;
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Search;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class Main {

	public static boolean isAdmin() {
	    String[] groups = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
	    for (String group : groups) {
	        if (group.equals("S-1-5-32-544"))
	            return true;
	    }
	    return false;
	}

	public static void main(String[] args) throws SQLException, IOException {

		Filter[] filters = new Filter[2];
		filters[0] = new NameFilter();
		filters[1] = new ExtensionFilter();
//
		String path = "db/DataBase.db";
//		String[][] columns = {{"value", "text"}, {"score", "integer"}} ;

//		DataBase db = new DataBase(path);
//		Table table = new Table(db, "flaextjs", columns);
//		ReadTable rt = new ReadTable(db, table);
//		String[][] result = rt.getByColumn("'1'",  "1");
//		System.out.println(Arrays.deepToString(result));
		quickScan(filters);
//		sortTable("flzextts", path);
//		sortAllTable(path);
//		hardScan(filters);
//		search(filters);

	}

	public static void quickScan(Filter[] filters) throws SQLException, IOException {
		QuickScan quickScan = new QuickScan(filters);
		quickScan.start();
	}

	public static void sortTable(String tableName) throws SQLException {
		DataBase dataBase = new DataBase();
		Table table = new Table(dataBase, tableName);
		SortTable st = new SortTable(dataBase, table);
		st.sortBy("score");
	}

	public static void sortAllTable(String path) throws SQLException {
		DataBase dataBase = new DataBase();
		SortTable st = new SortTable(dataBase);
		st.sortAll();
	}

	public static void hardScan(Filter[] filters) throws SQLException {
		new SortByFilter(filters, false).start();
	}

	public static void search(Filter[] filters) throws SQLException {
		String query = "";
		Scanner scanner = new Scanner(System.in);

		while (!query.equals("exist")) {
			System.out.print("Enter Query: ");
			query = scanner.nextLine();

			Search search = new Search(filters, query);
			FutureTask<String>
					task = new FutureTask<>(search,
					"");

			ExecutorService executor = Executors.newFixedThreadPool(1);

			// submit futureTask1 to ExecutorService
			executor.submit(task);
			while (!task.isDone()) {
				String result = search.getLastResult();
				if (result!=null && !result.equals("")) {
					System.out.println(result);
				}
			}

			System.out.println("Finished");
			executor.shutdown();

		}
	}

	public static void listf(String directoryName, List<String> files) {
		File directory = new File(directoryName);

		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList != null)
			for (File file : fList) {
				if (file.isFile()) {
					files.add(file.getPath());
				} else if (file.isDirectory()) {
					listf(file.getAbsolutePath(), files);
				}
			}
	}
}