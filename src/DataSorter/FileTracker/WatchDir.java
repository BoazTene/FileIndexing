package DataSorter.FileTracker;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

import DataSorter.FileTracker.EntryHandlers.*;

public class WatchDir {
	private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private RegisterDirectory registerDirectory;
	
	@SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }
	
	public WatchDir(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
 
        System.out.format("Scanning %s ...\n", dir);
        this.registerDirectory = new RegisterDirectory(watcher, keys, dir);
        this.registerDirectory.registerAll(dir);
        System.out.println("Done.");
    }
	
	/**
	 * This method watis until new key is being occured.
	 * 
	 * @return - Instance of the key.
	 */
	private WatchKey waitForKey() {
		WatchKey key;
        try {
            key = watcher.take();
        } catch (InterruptedException x) {
            return null;
        }
        
        return key;
	}
	
	/**
     * Process all events for keys queued to the watcher
     */
    public void processEvents(Entry entry, Overflow overflow) {
        for (;;) {

            // wait for key to be signalled
            WatchKey key = waitForKey();
            assert key != null;
            
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                	if (overflow != null) {
                		overflow.overflow();
                	}
                    continue;
                }
                
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
                
                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if ((kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            this.registerDirectory.registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
                
                if (entry != null) {
                	entry.newEntry(kind, child);	
                }
                
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

}
