package DataSorter.FileTracker.EntryHandlers; 

/**
 * The interface handles the OVERFLOW event.
 * 
 * @author Boaz Tene
 *
 */
public interface Overflow {
	/**
	 * This method will be called each time a OVERFLOW even is occurred.
	 */
	void overflow();
}
