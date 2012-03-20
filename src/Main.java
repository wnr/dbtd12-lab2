import java.util.HashMap;
import java.util.LinkedList;


public class Main {

	private HashMap<LinkedList<String>, LinkedList<String>> list;
	
	public void parse(String string) {
		LinkedList<String> from = new LinkedList<String>();
		LinkedList<String> to = new LinkedList<String>();
		
		String[] stringArray = string.split("->");
		String[] s0 = stringArray[0].split(",");
		String[] s1 = stringArray[1].split(",");
		
		//add from
		for (String s : s0) {
			from.add(s.trim());
		}
		
		//add to
		for (String s : s1) {
			to.add(s.trim());
		}
		
		list.put(from, to);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
