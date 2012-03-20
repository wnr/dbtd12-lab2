import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class FileParser {
	private LinkedList<FunctionalDependency> list;
	private Scanner sc;

	public FileParser() {
		list = new LinkedList<FunctionalDependency>();
	}
	
	public FileParser(String fileLocation) throws FileNotFoundException {
		new FileParser();
		
		File file = new File(fileLocation);
		sc = new Scanner(new FileReader(file));
		
		while (sc.hasNextLine()) {
			processLine(sc.nextLine());
		}
		
		sc.close();
	}
	
	public void processLine(String string) {
		String[] stringArray = string.split("->");
		String[] fromArray = stringArray[0].split(",");
		String[] toArray = stringArray[1].split(",");
		
		list.add(new FunctionalDependency(fromArray, toArray));
	}
}
