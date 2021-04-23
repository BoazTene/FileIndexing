package DataSorter.FileTracker.QuickScan;

import DataBase.DataBase;
import DataSorter.FileTracker.EntryHandlers.*;
import DataSorter.FileTracker.WatchDir;
import DataSorter.Filters.Filter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class QuickScan extends Thread {
    private static final Path[] NOT_ALLOWED = {Paths.get("C:\\Users\\user\\Documents\\Projects\\FileIndexing\\test")};
    private final Entry entry;
    private final Overflow overflow;
    private final WatchDir watchDir;

    public QuickScan(Filter[] filters) throws IOException, SQLException {
        DataBase dataBase = new DataBase();
        File[] temp = File.listRoots();
        Path[] drivers = new Path[temp.length];

        assert drivers.length > 0;
        int i = 0;
        for (File driver : temp) {
            drivers[i] = driver.toPath();
            i++;
        }

        this.entry = new EntryHandler(dataBase, filters);
        this.overflow = new OverFlowHandler();
        this.watchDir = new WatchDir(drivers, NOT_ALLOWED);
    }

    public void run() {
			try {
				this.watchDir.processEvents(this.entry, this.overflow);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}

    }
}

