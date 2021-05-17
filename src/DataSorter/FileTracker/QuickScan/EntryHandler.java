package DataSorter.FileTracker.QuickScan;

import DataBase.DataBase;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;
import DataSorter.FileTracker.EntryHandlers.Entry;
import DataSorter.Filters.Filter;
import Score.Filters.LastModified.LastModified;
import Score.Filters.Owner.Owner;
import Score.Filters.ScoreFilter;
import Score.Score;
import Search.Classify;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.sql.SQLException;
import java.util.Arrays;


/**
 * This Class is the EntryHandler, for the quickscan. Basically This class handles modified create and detelete events.
 *
 * @author Boaz Tene
 */
public class EntryHandler implements Entry {
    private static final ScoreFilter[] SCORE_FILTERS = {new LastModified(), new Owner()};
    private final Filter[] filters;
    private final DataBase dataBase;
    private final String[] unacceptable;

    /**
     * This is the constructor.
     *
     * @param dataBase
     * @param filters
     */
    public EntryHandler(DataBase dataBase, Filter[] filters) {
        this.filters = filters;
        this.dataBase = dataBase;
        this.unacceptable = new String[]{this.dataBase.getPath(), this.dataBase.getPath() + "-journal"};
    }

    /**
     * This method adds new row to the table.
     *
     * @param tableName The table name, you wants to add data
     * @param data The data you wont to add.
     * @throws SQLException
     */
    private void addToTable(String tableName, String[] data) throws SQLException {
        Table table = new Table(this.dataBase, tableName);
        WriteTable wt = new WriteTable(table);
        wt.newRow(data);
    }

    /**
     * This method checks if it should scan the file.
     *
     * @param dir The file
     * @return boolean
     */
    private boolean checkUnacceptable(Path dir) {
        String path = dir.toAbsolutePath().toString();
        return Arrays.asList(this.unacceptable).contains(path);
    }

    /**
     * This function called when new Event is occurred.
     * @param kind - The kind of the event that occured can be: ENTRY_CREATE, ENTRY_MODIFIED, ENTRY_DELETE.
     * @param path - The path of the event's file.
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void newEntry(WatchEvent.Kind kind, Path path) throws SQLException, IOException {
        if (checkUnacceptable(path)) return;

        if ("ENTRY_CREATE".equals(kind.toString())) {
            Classify classify = new Classify(filters, path.toAbsolutePath().toString());
            addToTable(classify.GetTableNameByFilters(), new String[]{path.toString(), String.valueOf(new Score(SCORE_FILTERS, path.toString()).getScore())});
        } else if ("ENTRY_DELETE".equals(kind.toString())) {
            Classify classify = new Classify(filters, path.toAbsolutePath().toString());

            Table table = new Table(this.dataBase, classify.GetTableNameByFilters());
            WriteTable wt = new WriteTable(table);
            wt.deleteRow(new String[]{path.toAbsolutePath().toString(), String.valueOf(new Score(SCORE_FILTERS, path.toString()).getScore())});
        } else if ("ENTRY_MODIFIED".equals(kind.toString())) {}
    }
}
