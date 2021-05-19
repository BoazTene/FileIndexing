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


		quickScan(filters);
		System.out.println("Hello this is the speed search.\nWith this tool you can search a file through your entire computer.");
		hardScan(filters);
//
		search(filters);

	}

	public static void quickScan(Filter[] filters) throws SQLException, IOException {
		System.out.println("Please wait while we are loading...");
		QuickScan quickScan = new QuickScan(filters);
		System.out.println("Finished loading.");

		quickScan.start();
	}

	public static void hardScan(Filter[] filters) throws SQLException {
		System.out.println("Scanning your computer, it could take a while.\n" +
				"You can search at the same time, but if this is your first time you won't find all result");

		SortByFilter sortByFilter = new SortByFilter(filters, false);
		sortByFilter.start();

	}

	public static void search(Filter[] filters) throws SQLException {
		String query = "";
		Scanner scanner = new Scanner(System.in);

		while (!query.equals("exist")) {
			System.out.print("Enter Query: ");
			query = scanner.nextLine();

			if (query.charAt(0) == ' ' || query.equals("")) {
				System.out.println("Can't accept this input.\nPlease try again.");
				continue;
			}

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
}