package core;

import java.util.LinkedList;

public class Printer {
	
	public Printer() {
		
	}
	
	public void printFunctionalDependencies(LinkedList<FunctionalDependency> list) {
		for (FunctionalDependency fd : list) {
			System.out.println(fd.toString());
		}
	}
	
	public void printRelations(LinkedList<Relation> list) {
		for (Relation fd : list) {
			System.out.println(fd.toString());
		}
	}
}
