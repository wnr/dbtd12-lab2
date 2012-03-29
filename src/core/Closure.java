package core;

import java.util.HashSet;
import java.util.Iterator;

/**
 *Representerar ett hölje till givna attribut
 */
public class Closure {
	HashSet<String>	startAttributes;
	HashSet<String> closureAttributes; //innehåller det beräknade höljet av startAttributes
	
	public Closure(){
		this.startAttributes = new HashSet<String>();
		this.closureAttributes = new HashSet<String>();
	}
	
	@SuppressWarnings("unchecked")
	public Closure(HashSet<String> startAttributes, HashSet<FunctionalDependency> knownDependencies){
		this.startAttributes = (HashSet<String>) startAttributes.clone();
		this.closureAttributes = (HashSet<String>) startAttributes.clone();
		
		//beräkna höljet direkt
		computeClosure(knownDependencies);
	}
	
	public void addStartAttribute(String s){
		startAttributes.add(s);
		closureAttributes.add(s);
	}
	
	@SuppressWarnings("unchecked")
	public void computeClosure(HashSet<FunctionalDependency> knownDependencies){
		
		//kopiera så att vi inte ändrar knownDependencies
		HashSet<FunctionalDependency> known = (HashSet<FunctionalDependency>) knownDependencies.clone();
		
		Iterator<FunctionalDependency> it = known.iterator();
		
		//iterera igenom alla kända beroenden
		while(it.hasNext()){
			FunctionalDependency dep = it.next();
			
			//om alla attribut i vänstra sidan av beroendet finns i det nuvarande beräknade höljet
			if(attributesInLeftSide(closureAttributes, dep)){
				
				//strunta i att lägga till om de högra attributen av beroendet redan finns i det nuvarande beräknade höljet
				if(closureAttributes.containsAll(dep.getRightList())){
					continue;
				}
				
				//annars lägg till alla de högra attributen i beroendet till det nuvarande beräknade höljet
				//OBS: closureAttributes är en HashSet så alla dubletter försvinner
				closureAttributes.addAll(dep.getRightList());
				
				//måste beräkna häljet rekursivt då vi nu har lagt till beroenden i det nuvarande beräknade höljet.
				//OBS: I och med att vi arbetar med HashSets så kan det vi lägger till hamna vart som helst i listan,
				//vilket innebär att vi måste göra om hela beräkningen på detta sätt. Det hade vi sluppit om vi istället
				//arbetat med LinkedList då det vi lägger till skulle hamna sist i listan.
				computeClosure(known);
			}
		}
	}
	
	
	/**
	 * Kollar om alla attribut i vänstra sidan av beroendet finns i de givna attributen
	 */
	private boolean attributesInLeftSide(HashSet<String> attributes, FunctionalDependency dependency){
		return attributes.containsAll(dependency.getLeftList());
	}
	
	public HashSet<String> getClosureAttributes() {
		return closureAttributes;
	}
	
	public HashSet<String> getStartAttributes(){
		return startAttributes;
	}
	
	@Override
	public String toString() {
		String temp = "Start: ";
		
		for(String s : startAttributes){
			temp += s;
		}
		
		temp += "\nClosure: ";
		
		for(String s : closureAttributes){
			temp += s;
		}
		
		return temp;
	}
}
