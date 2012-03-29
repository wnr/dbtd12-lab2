package core;

import java.util.HashSet;
import java.util.Iterator;

public class Projecter {
	public static HashSet<FunctionalDependency> projDependenciesByAttributes(HashSet<String> attributes, HashSet<FunctionalDependency> known){
		HashSet<FunctionalDependency> dependencies = new HashSet<FunctionalDependency>();
		
		
		HashSet<HashSet<String>> subSet = createSubSet(attributes);
		
		Iterator<HashSet<String>> it = subSet.iterator();
		
		//g�r igenom varje set av attributer.
		while(it.hasNext()){
			HashSet<String> set = it.next();
			
			//kolla s� att v�rt set inte inneh�ller annat �n v�rt h�lje av attributer
			if(attributes.containsAll(set)){
			
				//r�kna ut h�ljet f�r v�rt utvalda set.
				Closure closure = new Closure(set, known);
				
				//f�r varje attribut i h�ljet ska ett beroende skapas p� formen <v�rt utvalda set> -> <ett attribut ur h�ljet>
				//och lägga till beroendet i listan med kända beroenden.
				Iterator<String> it2 = closure.getClosureAttributes().iterator();
				Outer:
				while(it2.hasNext()){
					String rightAttribute = it2.next();
					//Kontrollera att v�rt set inte har det utvalda attributet ur h�ljet samt att det utvalda attributet verkligen finns i v�r projektion av attributer
					if(!set.contains(rightAttribute) && attributes.contains(rightAttribute)){
						Iterator<FunctionalDependency> it3 = dependencies.iterator();
						while(it3.hasNext()){
							FunctionalDependency currentDep = it3.next();
							//Kontrollera om vi redan har lagt till ett beroende med samma h�gerattribut
							if(currentDep.getRight(1).equals(rightAttribute)){
								//Om de tillagda beroende inneh�ller hela v�rt set, ta bort detta beroende.
								if(currentDep.getLeftList().containsAll(set)){
									it3.remove();
								// Om v�rt set inneh�ller hela v�rt tillagda beroende s� ta fram en ny rightattribute.
								} else if(set.containsAll(currentDep.getLeftList())){
									continue Outer;
								}
							}
						}
						dependencies.add(new FunctionalDependency(set, rightAttribute));
					}
				}
			}
		}
		
		return dependencies;
	}

	private static HashSet<HashSet<String>> createSubSet(HashSet<String> attributes) {
		return createSubSetHelper(attributes, attributes.size());
	}

	private static HashSet<HashSet<String>> createSubSetHelper(HashSet<String> attributes, int size) {
		HashSet<HashSet<String>> tempSubSet = new HashSet<HashSet<String>>();
		
		for(String attr : attributes){
			
			
			if(size > 1){
				HashSet<HashSet<String>> temp = createSubSetHelper(attributes, size-1);
				
				for(HashSet<String> t : temp){
					t.add(attr);
				}
				
				tempSubSet.addAll(temp);
			}else{
				HashSet<String> temp = new HashSet<String>();
				temp.add(attr);
				tempSubSet.add(temp);
			}	
		}
		return tempSubSet;
	}
}
