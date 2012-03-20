package core;
import java.util.HashSet;
import java.util.Iterator;

public class FunctionalDependency {
	private HashSet<String> fromList;
	private HashSet<String> toList;
	
	public FunctionalDependency(){
		fromList = new HashSet<String>();
		toList = new HashSet<String>();
	}
	
	public FunctionalDependency(String[] from, String[] to){
		this();
		
		for(int i = 0; i < from.length; i++){
			addFrom(from[i].trim());
		}
		
		for(int i = 0; i < to.length; i++){
			addTo(to[i].trim());
		}
	}
	
	public void addFrom(String from){
		fromList.add(from);
	}
	
	public void addTo(String to){
		toList.add(to);
	}
	
	public String getFrom(int index){
		return getListElement(fromList, index);
	}
	
	public String getTo(int index){
		return getListElement(toList, index);
	}
	
	public HashSet<String> getFromList(){
		return fromList;
	}
	
	public HashSet<String> getToList(){
		return toList;
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
}
