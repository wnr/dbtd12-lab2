package core;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Representerar ett funktionellt beroende
 */
public class FunctionalDependency {
	private HashSet<String> leftList;
	private HashSet<String> rightList;
	
	public FunctionalDependency(){
		leftList = new HashSet<String>();
		rightList = new HashSet<String>();
	}
	
	public FunctionalDependency(String[] left, String[] right){
		this();
		
		for(int i = 0; i < left.length; i++){
			addLeft(left[i].trim());
		}
		
		for(int i = 0; i < right.length; i++){
			addRight(right[i].trim());
		}
	}
	
	public FunctionalDependency(HashSet<String> left, HashSet<String> right){
		this();
		leftList.addAll(left);
		rightList.addAll(right);
	}
	
	public FunctionalDependency(HashSet<String> left, String right){
		this();
		leftList.addAll(left);
		addRight(right);
	}
	
	public void addLeft(String left){
		leftList.add(left);
	}
	
	public void addRight(String right){
		rightList.add(right);
	}
	
	public String getLeft(int index){
		return getListElement(leftList, index);
	}
	
	public String getRight(int index){
		return getListElement(rightList, index);
	}
	
	public HashSet<String> getLeftList(){
		return leftList;
	}
	
	public HashSet<String> getRightList(){
		return rightList;
	}
	
	
	/**
	 * Returnerar element på plats index.
	 * OBS: Första elementet ligger på plats 1.
	 */
	private String getListElement(HashSet<String> list, int index){
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			if(index == 0){
				return it.next();
			}
			
			index--;
		}
		
		throw new IllegalArgumentException();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (String s : leftList) {
			sb.append(s + ", ");
		}
		sb.setLength(sb.length()-2);
		
		sb.append(" -> ");
		
		for (String s : rightList) {
			sb.append(s + ", ");
		}
		sb.setLength(sb.length()-2);
		
		return sb.toString();
	}
}
