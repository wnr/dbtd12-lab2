package core;

import java.util.HashSet;
import java.util.Iterator;

/**
 *Representerar ett h�lje till givna attribut
 */
public class Closure {
	HashSet<String>	startAttributes;
	HashSet<String> closureAttributes; //inneh�ller det ber�knade h�ljet av startAttributes
	
	public Closure(){
		this.startAttributes = new HashSet<String>();
		this.closureAttributes = new HashSet<String>();
	}
	
	@SuppressWarnings("unchecked")
	public Closure(HashSet<String> startAttributes, HashSet<FunctionalDependency> knownDependencies){
		this.startAttributes = (HashSet<String>) startAttributes.clone();
		this.closureAttributes = (HashSet<String>) startAttributes.clone();
		
		//ber�kna h�ljet direkt
		computeClosure(knownDependencies);
	}
	
	public void addStartAttribute(String s){
		startAttributes.add(s);
		closureAttributes.add(s);
	}
	
	@SuppressWarnings("unchecked")
	public void computeClosure(HashSet<FunctionalDependency> knownDependencies){
		
		//kopiera s� att vi inte �ndrar knownDependencies
		HashSet<FunctionalDependency> known = (HashSet<FunctionalDependency>) knownDependencies.clone();
		
		Iterator<FunctionalDependency> it = known.iterator();
		
		//iterera igenom alla k�nda beroenden
		while(it.hasNext()){
			FunctionalDependency dep = it.next();
			
			//om alla attribut i v�nstra sidan av beroendet finns i det nuvarande ber�knade h�ljet
			if(attributesInLeftSide(closureAttributes, dep)){
				
				//strunta i att l�gga till om de h�gra attributen av beroendet redan finns i det nuvarande ber�knade h�ljet
				if(closureAttributes.containsAll(dep.getRightList())){
					continue;
				}
				
				//annars l�gg till alla de h�gra attributen i beroendet till det nuvarande ber�knade h�ljet
				//OBS: closureAttributes �r en HashSet s� alla dubletter f�rsvinner
				closureAttributes.addAll(dep.getRightList());
				
				//m�ste ber�kna h�ljet rekursivt d� vi nu har lagt till beroenden i det nuvarande ber�knade h�ljet.
				//OBS: I och med att vi arbetar med HashSets s� kan det vi l�gger till hamna vart som helst i listan,
				//vilket inneb�r att vi m�ste g�ra om hela ber�kningen p� detta s�tt. Det hade vi sluppit om vi ist�llet
				//arbetat med LinkedList d� det vi l�gger till skulle hamna sist i listan.
				computeClosure(known);
			}
		}
	}
	
	
	/**
	 * Kollar om alla attribut i v�nstra sidan av beroendet finns i de givna attributen
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
