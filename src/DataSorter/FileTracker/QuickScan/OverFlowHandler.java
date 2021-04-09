package DataSorter.FileTracker.QuickScan;

import DataSorter.FileTracker.EntryHandlers.Overflow;

public class OverFlowHandler implements Overflow {
    @Override
    public void overflow() {
        System.out.println("Overflow occurred!");
    }
}
