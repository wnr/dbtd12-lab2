package core;

import java.util.HashSet;

public class Main {
	private HashSet<FunctionalDependency> dependencies;
	private HashSet<Relation> relations;
	private Printer printer;
	
	public Main() {
		printer = new Printer();
		
		//l�ser in alla beroenden fr�n en given fil.
		dependencies = FileParser.parseFunctionalDependencies("./files/input3.dep");
		
		//skriver ut alla k�nda beroenden
		printer.printFunctionalDependencies(dependencies);
		
		System.out.println("\n\n");
		
		//ber�knar BCNF utifr�n de k�nda beroenden och skriver ut resultatet.
		printer.printRelations(Normalizer.makeBCNF(dependencies));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
