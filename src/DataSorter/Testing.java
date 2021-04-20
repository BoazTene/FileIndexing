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

