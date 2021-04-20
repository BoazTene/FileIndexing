package DataSorter.FileTracker;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
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
    private Path[] notAllowed;
    		
	public RegisterDirectory(WatchService watcher, Map<WatchKey,Path> keys, Path startPath, Path[] notAllowed) {
		this.notAllowed = notAllowed;
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
     * This method will return true if the given path isn't in the notAllowed field, else false.
     * @param path - A path to directory check if authorized.
     * @return - Return boolean.
     */
    private boolean notAllowedCheck(Path path) {
        for (Path notAllowedPath: this.notAllowed) {
            if (notAllowedPath.equals(path)) return false;
        }

        return true;
    }
    
    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    public void registerAll(final Path start) throws IOException, SecurityException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (Files.isDirectory(dir) && !notAllowedCheck(dir)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }

                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
