package DataSorter.Filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataSorter.MyFile;


/**
 * 
 * Filter file extenstion.
 * 
 * @author Itay Bar Nissim
 *
 */
public class ExtensionFilter implements Filter{
	private List<MyFile> filesList;
	private List<ArrayList<MyFile>> filteredByExtension;

	public ExtensionFilter(List<MyFile> filesList) {
		this.filesList = filesList;
		filteredByExtension = new ArrayList<ArrayList<MyFile>>();

	}

	@Override
	public void addIndex() {
		Iterator<MyFile> itr = null;
		ArrayList<MyFile> newExtension;
		ArrayList<MyFile> currentExtension;
		itr = filesList.iterator();
		while (itr.hasNext()) {
			MyFile thisFile = itr.next();
			Extension extension = thisFile.getExtension();
			int pointer = extension.getExtensionPointer();
			if (extension.getExtensionsList().isAddedToList() == false) {
				newExtension = new ArrayList<MyFile>();
				currentExtension = newExtension;
				filteredByExtension.add(newExtension);
				
			}
			else
				currentExtension= filteredByExtension.get(pointer);
			currentExtension.add(thisFile);
			

		}

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] classify(String query) {
		// TODO Auto-generated method stub
		return null;
	}
}

