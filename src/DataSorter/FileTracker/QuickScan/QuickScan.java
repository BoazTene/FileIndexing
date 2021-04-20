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
    private final Entry entry;
    private final Overflow overflow;
    private final WatchDir watchDir;

    public QuickScan(Filter[] filters, String dbName, String dir, Path[] notAllowed) throws IOException, SQLException {
        Path dir1 = Paths.get(dir);
        this.entry = new EntryHandler(new DataBase(dbName), filters);
        this.overflow = new OverFlowHandler();
        this.watchDir = new WatchDir(dir1, notAllowed);
    }

    public void start() throws SQLException, IOException {
        this.watchDir.processEvents(this.entry, this.overflow);
    }
}
