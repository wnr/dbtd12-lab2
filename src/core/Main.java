package core;
import java.util.LinkedList;

public class Main {
	private LinkedList<FunctionalDependency> list;
	
	public Main() {
		list = new LinkedList<FunctionalDependency>();
		
		new FileParser("./files/test.txt", list);
		
		printInfo();
	}
	
	public void printInfo() {
		for (FunctionalDependency fd : list) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("(");
			for (String s : fd.getFromList()) {
				sb.append(s + ", ");
			}
			sb.setLength(sb.length()-2);
			
			sb.append(", ");
			
			for (String s : fd.getToList()) {
				sb.append(s + ", ");
			}
			sb.setLength(sb.length()-2);
			sb.append(")");
			
			System.out.println(sb.toString());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
