package core;

import java.util.HashSet;

public class Main {
	private HashSet<FunctionalDependency> fdList;
	private HashSet<Relation> rList;
	private Printer printer;
	
	public Main() {
		fdList = FileParser.parseFunctionalDependencies("./files/input3" +
				".dep");
		printer = new Printer();
	
		printer.printFunctionalDependencies(fdList);
		
		System.out.println("\n\n");
		
		printer.printRelations(Normalizer.makeBCNF(fdList));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
