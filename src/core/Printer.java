package core;

import java.util.HashSet;
import java.util.LinkedList;

public class Printer {
	
	public Printer() {
		
	}
	
	public void printFunctionalDependencies(HashSet<FunctionalDependency> list) {
		for (FunctionalDependency fd : list) {
			System.out.println(fd.toString());
		}
	}
	
	public void printRelations(LinkedList<Relation> list) {
		for (Relation fd : list) {
			System.out.println(fd.toString());
		}
	}
	
	public void printRelation(Relation relation) {
		System.out.println(relation.toString());
	}
}
