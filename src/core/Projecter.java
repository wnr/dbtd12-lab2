package core;

import java.util.HashSet;
import java.util.Iterator;

public class Projecter {
	public static HashSet<FunctionalDependency> projDependenciesByAttributes(HashSet<String> attributes, HashSet<FunctionalDependency> known){
		HashSet<FunctionalDependency> dependencies = new HashSet<FunctionalDependency>();
		
		
		HashSet<HashSet<String>> subSet = createSubSet(attributes);
		
		Iterator<HashSet<String>> it = subSet.iterator();
		
		//går igenom varje set av attributer.
		while(it.hasNext()){
			HashSet<String> set = it.next();
			
			//kolla så att vårt set inte innehåller annat än vårt hölje av attributer
			if(attributes.containsAll(set)){
			
				//räkna ut höljet för vårt utvalda set.
				Closure closure = new Closure(set, known);
				
				//för varje attribut i höljet ska ett beroende skapas på formen <vårt utvalda set> -> <ett attribut ur höljet>
				//och lÃ¤gga till beroendet i listan med kÃ¤nda beroenden.
				Iterator<String> it2 = closure.getClosureAttributes().iterator();
				Outer:
				while(it2.hasNext()){
					String rightAttribute = it2.next();
					//Kontrollera att vårt set inte har det utvalda attributet ur höljet samt att det utvalda attributet verkligen finns i vår projektion av attributer
					if(!set.contains(rightAttribute) && attributes.contains(rightAttribute)){
						Iterator<FunctionalDependency> it3 = dependencies.iterator();
						while(it3.hasNext()){
							FunctionalDependency currentDep = it3.next();
							//Kontrollera om vi redan har lagt till ett beroende med samma högerattribut
							if(currentDep.getRight(1).equals(rightAttribute)){
								//Om de tillagda beroende innehåller hela vårt set, ta bort detta beroende.
								if(currentDep.getLeftList().containsAll(set)){
									it3.remove();
								// Om vårt set innehåller hela vårt tillagda beroende så ta fram en ny rightattribute.
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
