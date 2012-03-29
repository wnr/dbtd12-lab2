package core;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class FileParser {
	
	/**
	 * LŠser in alla beroenden från en given fil.
	 */
	public static HashSet<FunctionalDependency> parseFunctionalDependencies(String filename){
		Scanner sc = null;
		HashSet<FunctionalDependency> list = new HashSet<FunctionalDependency>();
		
		try {
			File file = new File(filename);
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
		
		return list;
	}
	
	/**
	 * HjŠlpfunktion till parseFunctionalDependencies
	 */
	private static void processLine(String string, HashSet<FunctionalDependency> list) {
		String[] stringArray = string.split("->");
		String[] fromArray = stringArray[0].split(",");
		String[] toArray = stringArray[1].split(",");
		
		list.add(new FunctionalDependency(fromArray, toArray));
	}	
}
