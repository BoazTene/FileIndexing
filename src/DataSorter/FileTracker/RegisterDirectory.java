package DataSorter.FileTracker;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

/**
 * This class is used to register directories.
 * 
 * 
 * @author Boaz Tene
 *
 */
public class RegisterDirectory {
	private Path startPath;
	private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private boolean trace = true;
    		
	public RegisterDirectory(WatchService watcher, Map<WatchKey,Path> keys, Path startPath) {
		this.startPath = startPath;
		this.watcher = watcher;
		this.keys = keys;
	}
	
	private WatchKey getWatchKey(Path dir) throws IOException {
    	return dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	}
	
	/**
     * Register the given directory with the WatchService
     */
    public void register(Path dir) throws IOException {
    	WatchKey key = getWatchKey(dir);
        keys.put(key, dir);
    }

    
    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    public void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
