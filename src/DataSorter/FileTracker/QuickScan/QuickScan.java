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

/**
 * This class handles the quickscan.
 * The quickscan runs in a separate thread, it detects windows file events (File Modified, File Create, File Delete and File Overflow)
 * The quickscan helps us to save time and not run always the hardScan which actually scans the entire computer.
 *
 * @author Boaz Tene
 */
public class QuickScan extends Thread {
    private static final Path[] NOT_ALLOWED = {Paths.get("C:\\Users\\user\\Documents\\Projects\\FileIndexing\\test")};
    private final Entry entry;
    private final Overflow overflow;
    private final WatchDir watchDir;

    /**
     * This is the class Constructor.
     *
     * @param filters - The filter to scan by.
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * This main method to run when starting the thread.
     * It starts the detection.
     */
    public void run() {
			try {
				this.watchDir.processEvents(this.entry, this.overflow);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}

    }
}

