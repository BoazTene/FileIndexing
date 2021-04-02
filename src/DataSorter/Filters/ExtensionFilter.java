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
//	private List<MyFile> filesList;
//	private List<ArrayList<MyFile>> filteredByExtension;
	private String tableName;
	private String path;
	
	public ExtensionFilter(String tableName, String path) {
		this.tableName = tableName;
		this.path = path;
		
//		this.filesList = filesList;
//		filteredByExtension = new ArrayList<ArrayList<MyFile>>();

	}

	@Override
	public void addIndex() {
//		Iterator<MyFile> itr = null;
//		ArrayList<MyFile> newExtension;
//		ArrayList<MyFile> currentExtension;
//		itr = filesList.iterator();
//		while (itr.hasNext()) {
//			MyFile thisFile = itr.next();
//			Extension extension = thisFile.getExtension();
//			int pointer = extension.getExtensionPointer();
//			if (extension.getExtensionsList().isAddedToList() == false) {
//				newExtension = new ArrayList<MyFile>();
//				currentExtension = newExtension;
//				filteredByExtension.add(newExtension);
//				
//			}
//			else
//				currentExtension= filteredByExtension.get(pointer);
//			currentExtension.add(thisFile);
//			
		String extension = "";
		
		int i = path.lastIndexOf('.');

			extension = path.substring(i+1);
			tableName+=extension;

		}
	public String getTableName() {
		return this.tableName;

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}
}

