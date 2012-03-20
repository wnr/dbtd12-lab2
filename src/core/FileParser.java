package core;
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class FileParser {
	private Scanner sc;
	
	public FileParser(String fileLocation, LinkedList<FunctionalDependency> list) {
		try {
			File file = new File(fileLocation);
			sc = new Scanner(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			while (sc.hasNextLine()) {
				processLine(sc.nextLine(), list);
			}
			
			sc.close();
		}
	}
	
	public void processLine(String string, LinkedList<FunctionalDependency> list) {
		String[] stringArray = string.split("->");
		String[] fromArray = stringArray[0].split(",");
		String[] toArray = stringArray[1].split(",");
		
		list.add(new FunctionalDependency(fromArray, toArray));
	}
}
