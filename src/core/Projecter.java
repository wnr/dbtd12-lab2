package core;

import java.util.HashSet;
import java.util.Iterator;

public class Projecter {
	public static HashSet<FunctionalDependency> projDependenciesByAttributes(HashSet<String> attributes, HashSet<FunctionalDependency> known){
		HashSet<FunctionalDependency> dependencies = new HashSet<FunctionalDependency>();
		
		Iterator<FunctionalDependency> it = known.iterator();
		
		//gå igenom varje känt beroende
		while(it.hasNext()){
			FunctionalDependency dep = it.next();
			
			//kolla så att vänstra sidan på nuvaranda beroendet finns i attributes
			if(attributes.containsAll(dep.getLeftList())){
			
				//räkna ut höljet för beroendet
				Closure closure = new Closure(dep.getLeftList(), known);
				
				//för varje attribut i höljet ska ett beroende skapas på formen <vänstra sida av nuvarande beroende> -> <ett attribut ur höljet>
				//och lägga till beroendet i listan med kända beroenden.
				Iterator<String> it2 = closure.getClosureAttributes().iterator();
				while(it2.hasNext()){
					String rightAttribute = it2.next();
					
					if(!dep.getLeftList().contains(rightAttribute) && attributes.contains(rightAttribute)){
						dependencies.add(new FunctionalDependency(dep.getLeftList(), rightAttribute));
					}
				}
			}
		}
		
		return dependencies;
	}
}
