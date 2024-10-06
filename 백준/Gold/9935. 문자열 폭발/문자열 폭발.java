import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		String bomb = sc.next();
		Stack<Character> stack = new Stack();
		
		for(int i = 0 ; i < str.length() ; i++) {
			stack.push(str.charAt(i));
			if(stack.size()>=bomb.length()) {
				boolean check = false;
				
				for(int j = 0 ; j < bomb.length() ; j++) {
					if(stack.get(stack.size() -bomb.length() + j) != bomb.charAt(j)) {
						check = true;
						break;
					}
				}
				if(!check) {
					for(int j = 0 ; j < bomb.length() ; j++) {
						stack.pop();
					}
				}	
			}

		}
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}else {
			StringBuilder sb = new StringBuilder();
			for(Character c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}

	}

}
