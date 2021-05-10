package gui;

import DataSorter.Filters.ExtensionFilter;
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Search;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


/**
 * This class is the gui part of the Search.
 */
public class GuiSearch {
    private static Filter[] filters;

    static {
        try {
            filters = new Filter[]{new NameFilter(), new ExtensionFilter()};
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the static search method, it adds the search result to the search Result component.
     * @param path The String to search.
     */
    public static void search(String path){
        gui.Components.SearchResults.resetResults();

        Search search = null;
        try {
            search = new Search(filters, path);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert search != null;
        FutureTask<String>
                task = new FutureTask<>(search,
                "");

        ExecutorService executor = Executors.newFixedThreadPool(1);

        // submit futureTask1 to ExecutorService
        executor.submit(task);

        while (!task.isDone()) {
            String result = search.getLastResult();
            if (result!=null && !result.equals("")) {
                gui.Components.SearchResults.addResult(result);
            }
        }

        gui.Components.SearchResults.addResult("Search ended!");
    }
}
