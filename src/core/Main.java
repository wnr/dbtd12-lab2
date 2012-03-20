package core;

import java.util.LinkedList;

public class Main {
	private LinkedList<FunctionalDependency> fdList;
	private LinkedList<Relation> rList;
	private Printer p;
	
	public Main() {
		fdList = new LinkedList<FunctionalDependency>();
		rList = new LinkedList<Relation>();
		p = new Printer();
		
//		new FileParser("./files/test.txt", fdList);
		new FileParser("./files/test.txt", rList);
		
//		p.printFunctionalDependencies(fdList);
		
		p.printRelations(rList);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
