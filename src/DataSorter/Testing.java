package DataSorter;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Testing {
	public static void main(String[] args) {
		String[][] result = new String[6][];
		//		result[5][0] = "Hello";
		//		result[5][1] = "Mate";
		String extension = "";
		String fileName = "C:/Users/User/Documents/μιξεγιν/ticTa.ctoe.png";
		int i = fileName.lastIndexOf('.');

			extension = fileName.substring(i+1);

			System.out.println(extension);

		//		MyFile file1 = new MyFile("file", "txt", true);
		//		MyFile file2 = new MyFile("itay", "png", true);
		//		MyFile file3 = new MyFile("yossi", "txt", false);
		//		MyFile file4 = new MyFile("boaz", "png", false);
		//		MyFile file5 = new MyFile("yoav", "png", false);
		//		MyFile file6 = new MyFile("beni", "txt", true);
		//		MyFile file7 = new MyFile("goni", "txt", true);
		//		MyFile file8 = new MyFile("gilad", "txt", false);
		//		MyFile file9 = new MyFile("fredy", "png", true);
		//
		//		List<MyFile> filesList = new ArrayList<MyFile>();
		//		filesList.add(file1);
		//		filesList.add(file2);
		//		filesList.add(file3);
		//		filesList.add(file4);
		//		filesList.add(file5);
		//		filesList.add(file6);
		//		filesList.add(file7);
		//		filesList.add(file8);
		//		filesList.add(file9);
		//		Iterator<MyFile> itr = null;
		//		itr = filesList.iterator();
		//		List<MyFile> fileNames = new ArrayList<MyFile>();
		//		List<MyFile>[] alphabet = new List[128];
		//		while (itr.hasNext()) {
		//			MyFile thisFile = itr.next();
		//			char firtLetter = thisFile.getName().charAt(0);
		//			if (alphabet[firtLetter] == null)
		//				alphabet[firtLetter] = new ArrayList<MyFile>();
		//			alphabet[firtLetter].add(thisFile);
		//		}
	}

}
