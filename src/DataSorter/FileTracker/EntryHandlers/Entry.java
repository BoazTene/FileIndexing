package DataSorter.FileTracker.EntryHandlers;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.sql.SQLException;

/**
 * This interface is the first argument to be passed in the WatchDir Object.
 * The interace is build to handle the ENTRY_CREATE, ENTRY_MODIFIED, ENTRY_DELETE events.
 * 
 * @author Boaz Tene
 *
 */
public interface Entry {
	/**
	 * Each time a new event(ENTRY_CREATE, ENTRY_MODIFIED, ENTRY_DELETE) is occured 
	 * this method is being called.
	 * 
	 * @param kind - The kind of the event that occured can be: ENTRY_CREATE, ENTRY_MODIFIED, ENTRY_DELETE.
	 * @param path - The path of the event's file.
	 */
	public void newEntry(WatchEvent.Kind kind, Path path) throws SQLException;
}
