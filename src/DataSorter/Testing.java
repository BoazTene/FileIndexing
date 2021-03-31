package DataSorter;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

//import DataBase.Path;


public class Testing {

    public static void main(String[] args) throws IOException {
    	Path dir = Paths.get("C:/Users/user/Documents/Projects/FileIndexing/db");
    	WatchService watcher = FileSystems.getDefault().newWatchService();
    	try {
    	    WatchKey key = dir.register(watcher,
    	                           ENTRY_CREATE,
    	                           ENTRY_DELETE);
    	} catch (IOException x) {
    	    System.err.println(x);
    	}
    	
    	for (;;) {

    	    // wait for key to be signaled
    	    WatchKey key;
    	    try {
    	        key = watcher.take();
    	    } catch (InterruptedException x) {
    	        return;
    	    }

    	    for (WatchEvent<?> event: key.pollEvents()) {
    	        WatchEvent.Kind<?> kind = event.kind();
    	        System.out.println(kind);

    	        // This key is registered only
    	        // for ENTRY_CREATE events,
    	        // but an OVERFLOW event can
    	        // occur regardless if events
    	        // are lost or discarded.
    	        if (kind == OVERFLOW) {
    	            continue;
    	        }

    	        // The filename is the
    	        // context of the event.
    	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
    	        Path filename = ev.context();
    	        System.out.println(filename.getFileName());

    	        // Verify that the new
    	        //  file is a text file.
    	        try {
    	            // Resolve the filename against the directory.
    	            // If the filename is "test" and the directory is "foo",
    	            // the resolved name is "test/foo".
    	            Path child = dir.resolve(filename);
    	            System.out.println(Files.probeContentType(child));
    	            if (!Files.probeContentType(child).equals("text/plain")) {
    	                System.err.format("New file '%s'" +
    	                    " is not a plain text file.%n", filename);
    	                continue;
    	            }
    	        } catch (Exception x) {
//    	            System.err.println(x);
    	            continue;
    	        }

    	        // Email the file to the
    	        //  specified email alias.
    	        System.out.format("Emailing file %s%n", filename);
    	        //Details left to reader....
    	    }

    	    // Reset the key -- this step is critical if you want to
    	    // receive further watch events.  If the key is no longer valid,
    	    // the directory is inaccessible so exit the loop.
    	    boolean valid = key.reset();
    	    if (!valid) {
    	        break;
    	    }
    	}
    }
}