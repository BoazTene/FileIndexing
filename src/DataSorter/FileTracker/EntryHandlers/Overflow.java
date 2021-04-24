package DataSorter.FileTracker.EntryHandlers; 

/**
 * This interface is the second argument to be passed in the WatchDir Object.
 * The interace is build to handle the OVERFLOW event.
 * 
 * @author Boaz Tene
 *
 */
public interface Overflow {
	/**
	 * This method will be called each time a OVERFLOW even is occured.
	 */
	void overflow();
}
