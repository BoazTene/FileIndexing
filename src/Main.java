import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import DataSorter.FileTracker.QuickScan.QuickScan;
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
		String path = "db/DataBase.db";


		hardScan(filters);
		quickScan(filters);
//
		search(filters);

	}

	public static void quickScan(Filter[] filters) throws SQLException, IOException {
		QuickScan quickScan = new QuickScan(filters);

		quickScan.start();
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