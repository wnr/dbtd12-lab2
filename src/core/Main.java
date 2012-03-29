package core;

import java.util.HashSet;

public class Main {
	private HashSet<FunctionalDependency> dependencies;
	private HashSet<Relation> relations;
	private Printer printer;
	
	public Main() {
		printer = new Printer();
		
		//läser in alla beroenden från en given fil.
		dependencies = FileParser.parseFunctionalDependencies("./files/input3.dep");
		
		//skriver ut alla kända beroenden
		printer.printFunctionalDependencies(dependencies);
		
		System.out.println("\n\n");
		
		//beräknar BCNF utifrån de kända beroenden och skriver ut resultatet.
		printer.printRelations(Normalizer.makeBCNF(dependencies));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
