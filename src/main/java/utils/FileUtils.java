package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class FileUtils {
	
	public static String read(String filename) {
		StringBuilder string = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line = br.readLine()) != null) {
				string.append(line);
				string.append("\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return string.toString();
	}
	
	public static ArrayList<String> review(String folderString) {
		File folder = new File(folderString);
		File[] filesList = folder.listFiles();
		ArrayList<String> fileReview = new ArrayList<String>(); 
		
		for (int i = 0; i < filesList.length; i++)
			fileReview.add(filesList[i].getName());

		return fileReview;
	}
}
