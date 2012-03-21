package core;
import java.util.HashSet;
import java.util.Iterator;

public class Relation {
	private HashSet<String> list;
	
	public Relation(){
		list = new HashSet<String>();
	}
	
	public Relation(HashSet<String> attributes){
		this();
		
		list.addAll(attributes);
	}
	
	public Relation(String[] from, String[] to){
		this();
		
		for(int i = 0; i < from.length; i++){
			add(from[i].trim());
		}
		
		for(int i = 0; i < to.length; i++){
			add(to[i].trim());
		}
	}
	
	public void add(String attribute){
		list.add(attribute);
	}
	
	public String getIndex(int index){
		return getListElement(list, index);
	}
	
	public HashSet<String> getRelationList(){
		return list;
	}

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
		
		sb.append("(");
		for (String s : list) {
			sb.append(s + ", ");
		}
		
		sb.setLength(sb.length()-2);
		sb.append(")");
		
		return sb.toString();
	}

	public void addAttributes(HashSet<String> attr) {
		list.addAll(attr);
		
	}
}
