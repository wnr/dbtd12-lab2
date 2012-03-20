package core;

import java.util.HashSet;
import java.util.Iterator;

public class Closure {
	HashSet<FunctionalDependency> dependencies;
	HashSet<String> closureAttributes;
	HashSet<String>	startAttributes;

	public Closure(HashSet<String> startAttributes, HashSet<FunctionalDependency> knownDependencies){
		dependencies = new HashSet<FunctionalDependency>();
		closureAttributes = new HashSet<String>();
		this.startAttributes = startAttributes;
		this.closureAttributes = startAttributes;
		
		computeClosure(knownDependencies);
	}
	
	public void computeClosure(HashSet<FunctionalDependency> knownDependencies){
		Iterator<FunctionalDependency> it = knownDependencies.iterator();
		while(it.hasNext()){
			FunctionalDependency dep = it.next();
			if(attributesInLeftSide(closureAttributes, dep)){
				
			}
		}
	}
	
	private boolean attributesInLeftSide(HashSet<String> attributes, FunctionalDependency dependency){
		return true;
	}
}
