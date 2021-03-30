package DataSorter;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import DataBase.DataBase;
import DataBase.Table.Table;
import DataBase.Table.WriteTable;

/**
 * 
 * @author Itay Bar Nissim @
 * 
 */
public class SortByFilter {

	public static void main(String[] args) throws SQLException {
//		MyFile file1 = new MyFile("file", new Extension("txt"), true);
//		MyFile file2 = new MyFile("itay", new Extension("txt"), true);
//		MyFile file3 = new MyFile("yossi", new Extension("txt"), false);
//		MyFile file4 = new MyFile("balon", new Extension("txt"), false);
//		MyFile file5 = new MyFile("yoav", new Extension("txt"), false);
//		MyFile file6 = new MyFile("beni", new Extension("txt"), true);
//		MyFile file7 = new MyFile("goni", new Extension("txt"), true);
//		MyFile file8 = new MyFile("gilad", new Extension("txt"), false);
//		MyFile file9 = new MyFile("fredy", new Extension("txt"), true);
		List<String> filesList = new ArrayList<>();
		listf("C:/Users/user/", filesList);
		System.out.println(filesList.size());

//		List<String> filesList = new ArrayList<String>();
//		filesList.add(file1);
//		filesList.add(file2);
//		filesList.add(file3);
//		filesList.add(file4);
//		filesList.add(file5);
//		filesList.add(file6);
//		filesList.add(file7);
//		filesList.add(file8);
//		filesList.add(file9);
		Iterator<String> itr = null;
		itr = filesList.iterator();
		List<MyFile> fileNames = new ArrayList<MyFile>();
		nameFilter nameFilter = new nameFilter(filesList);
		nameFilter.addIndex();
		
//		typeFilter typeFilter = new typeFilter(filesList);
//		typeFilter.addIndex();
		
	}
	
	public static void listf(String directoryName, List<String> files) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file.getPath());
	            } else if (file.isDirectory()) {
	                listf(file.getAbsolutePath(), files);
	            }
	        }
	    }
}

/**
 * 
 * Filter alphabetic order by first letter.
 * 
 * 
 * @author Itay Bar Nissim
 *
 */
class nameFilter implements Filter {
	private List<String> filesList;
	private DataBase dataBase;
	private String[][] columns = {{"value", "text"}};

	public nameFilter(List<String> filesList) throws SQLException {
		this.filesList = filesList;
		this.dataBase = new DataBase("C:/Users/user/Documents/Projects/FileIndexing/db/FirstLetter.db");
		
	}
	
	public void addToTable(String tableName, String[] data) throws SQLException {
		Table table = new Table(this.dataBase, tableName, this.columns);
		WriteTable wt = new WriteTable(table);
		wt.newRow(data);
	}
	
	@Override
	public void addIndex() throws SQLException {
		Iterator<String> itr = null;
		itr = filesList.iterator();
		int lahoh = 500000;
		while (itr.hasNext()) {
			String thisFile = itr.next();
			String[] data = {thisFile};
			lahoh++;
			System.out.println("lahoh: " + lahoh);
			char firstChar = thisFile.substring(thisFile.lastIndexOf("\\")+1).charAt(0);
			int i = 500000;
			while(true) {
				i++;
				try {
					addToTable(String.valueOf(firstChar), data);
					break;
				} catch (Exception e) {
					try {
						firstChar = thisFile.substring(thisFile.lastIndexOf("\\")+1).charAt(i);
					} catch (Exception er) {
						addToTable("onlyNumbers", data);
						break;
					}
				}
			}
			
		}

	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}

//class NameFilter {
//	private List<MyFile> filesList;
//	private String[][] result;
//	private int numberOfGroups;
//
//	public NameFilter(int numberOfGroups) {
//		this.numberOfGroups = numberOfGroups;
//		this.result = new String[numberOfGroups][];
//	}
//	
//	private int classify(String query) {
//		return query.charAt(0);
//	}
//	
//	public void addIndex(String query) {
//		System.out.println(classify(query));
//	}
//	
////	public List<MyFile>[] getArray() {
////		return this.alphabetArray;
////	}
//
//	public void search() {
//		// TODO Auto-generated method stub
//
//	}
//}

/**
 * 
 * Filter file extenstion.
 * 
 * @author Itay Bar Nissim
 *
 */
class typeFilter implements Filter {
	private List<MyFile> filesList;
	private List<ArrayList<MyFile>> filteredByExtension;

	public typeFilter(List<MyFile> filesList) {
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

}

//class pathFilter implements Filter {
//
//	@Override
//	public void startFiltring(List<MyFile> filesList, List<MyFile>[] alphabet) {
//		
//		
//	}
//
//}
//class creationDateFilter implements Filter {
//
//	@Override
//	public void startFiltring(List<MyFile> filesList, List<MyFile>[] alphabet) {
//		
//		
//	}
//
//}
//class lastUseFilter implements Filter {
//
//	@Override
//	public void startFiltring(List<MyFile> filesList, List<MyFile>[] alphabet) {
//		
//		
//	}
//
//}
//class folderFilter implements Filter {
//
//	@Override
//	public void startFiltring(List<MyFile> filesList, List<MyFile>[] alphabet) {
//		
//		
//	}
//
//}
//
//
//public void combineLists(List<File> filtered) {
//	
//}
