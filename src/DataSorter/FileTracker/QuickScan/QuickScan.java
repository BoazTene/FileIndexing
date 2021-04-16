package DataSorter.FileTracker.QuickScan;

import DataBase.DataBase;
import DataSorter.FileTracker.EntryHandlers.*;
import DataSorter.FileTracker.WatchDir;
import DataSorter.Filters.Filter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class QuickScan {
    private final Path dir;
    private Entry entry;
    private Overflow overflow;
    private WatchDir watchDir;
    private DataBase dataBase;
    private Filter[] filters;

    public QuickScan(Filter[] filters, String dbName, String dir, Path[] notAllowed) throws IOException, SQLException {
        this.dataBase = new DataBase(dbName);
        this.filters = filters;
        this.dir = Paths.get(dir);
        this.entry = new EntryHandler(this.dataBase, filters);
        this.overflow = new OverFlowHandler();
        this.watchDir = new WatchDir(this.dir, notAllowed);
    }

    public void start() throws SQLException {
        this.watchDir.processEvents(this.entry, this.overflow);
    }
}
