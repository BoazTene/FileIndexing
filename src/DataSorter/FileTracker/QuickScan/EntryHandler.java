package DataSorter.FileTracker.QuickScan;

import DataBase.DataBase;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;
import DataSorter.FileTracker.EntryHandlers.Entry;
import DataSorter.Filters.Filter;
import Search.Classify;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.sql.SQLException;
import java.util.Arrays;

public class EntryHandler implements Entry {
    private Filter[] filters;
    private DataBase dataBase;
    private String[][] columns = {{"value", "text"}};
    private String[] unacceptable;

    public EntryHandler(DataBase dataBase, Filter[] filters) {
        this.filters = filters;
        this.dataBase = dataBase;
        this.unacceptable = new String[]{this.dataBase.getPath(), this.dataBase.getPath() + "-journal"};
        System.out.println(Arrays.toString(this.unacceptable));
    }

    private void addToTable(String tableName, String[] data) throws SQLException {
        Table table = new Table(this.dataBase, tableName, this.columns);
        WriteTable wt = new WriteTable(table);
        wt.newRow(data);
    }

    private boolean checkUnacceptable(Path dir) {
        String path = dir.toAbsolutePath().toString();
        return Arrays.asList(this.unacceptable).contains(path);
    }

    @Override
    public void newEntry(WatchEvent.Kind kind, Path path) throws SQLException {
        if (checkUnacceptable(path)) return;

        if ("ENTRY_CREATE".equals(kind.toString())) {
            Classify classify = new Classify(filters, path.toAbsolutePath().toString());
            System.out.println(classify.GetTableNameByFilters());
            addToTable(classify.GetTableNameByFilters(), new String[]{path.toString()});
        } else if ("ENTRY_DELETE".equals(kind.toString())) {
            Classify classify = new Classify(filters, path.toAbsolutePath().toString());

            Table table = new Table(this.dataBase, classify.GetTableNameByFilters(), this.columns);
            WriteTable wt = new WriteTable(table);
            wt.deleteRow(new String[]{path.toAbsolutePath().toString()});
        } else if ("ENTRY_MODIFIED".equals(kind.toString())) {
            System.out.println("Modified.");
        }
    }
}
