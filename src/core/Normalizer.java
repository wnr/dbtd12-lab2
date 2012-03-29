package core;

import java.util.HashSet;
import java.util.Iterator;

import com.sun.org.apache.xpath.internal.functions.Function;



public class Normalizer {
	public static boolean isSuperKey(HashSet<String> attributes, HashSet<String> allAttributes, HashSet<FunctionalDependency> dependencies){
		
		Closure closure = new Closure(attributes, dependencies);
		return closure.getClosureAttributes().containsAll(allAttributes);
	}
	
	@SuppressWarnings("unchecked")
	public static HashSet<Relation> makeBCNF(HashSet<FunctionalDependency> dependencies){
		//Skapa "minsta m�jliga" relationen utifr�n de givna beroendena.
		Relation relation = computeRelation(dependencies);
		
		return makeBCNFHelper(relation, dependencies);
	}
	
	@SuppressWarnings("unchecked")
	private static HashSet<Relation> makeBCNFHelper(Relation relation, HashSet<FunctionalDependency> dependencies){
		HashSet<Relation> relations = new HashSet<Relation>();
		
//		System.out.println(relation.toString() + "..." + dependencies.toString());
		
		Iterator<FunctionalDependency> it = dependencies.iterator();
		while(it.hasNext()){
			
			//ber�kna h�ljet av beroendet.
			Closure closure = new Closure(it.next().getLeftList(), dependencies);
			
			//om boroendet inte var en supernyckel (eller inneh�ll en kandidatnyckel)
			//samt att relationen var st�rre �n 2 attribut
			//OBS att om det inte finns n�gra beroenden s� kommer vi inte ens in i while-satsen.
			if(!isSuperKey(closure.getClosureAttributes(), relation.getRelationList(), dependencies) && relation.getRelationList().size() > 2){
				Relation r1 = new Relation(closure.getClosureAttributes());
				
				HashSet<String> rest = (HashSet<String>) relation.getRelationList().clone();
				rest.removeAll(closure.getClosureAttributes());
				rest.addAll(closure.getStartAttributes());
				Relation r2 = new Relation(rest);
				
				//rekursera vidare ned med uppdelningen r1 och r2 f�r att till slut samla ihop alla relationer.
				relations.addAll(makeBCNFHelper(r1, Projecter.projDependenciesByAttributes(r1.getRelationList(), dependencies)));
				relations.addAll(makeBCNFHelper(r2, Projecter.projDependenciesByAttributes(r2.getRelationList(), dependencies)));
				return relations;
			}
		}
		
		relations.add(relation);
		return relations;
	}
	
	private static Relation computeRelation(HashSet<FunctionalDependency> dep){
		Relation relation = new Relation();
		
		Iterator<FunctionalDependency> it = dep.iterator();
		while(it.hasNext()){
			FunctionalDependency d = it.next();
			relation.addAttributes(d.getLeftList());
			relation.addAttributes(d.getRightList());
		}
		
		return relation;
	}
}
