package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Problem673 {
	private static String solve(String input) {
		if(input.equals(""))
			return "Yes";
		
		LinkedList<Character> stack = new LinkedList<Character>();
		for(int i = 0; i < input.length(); ++i) {
			Character ch = input.charAt(i);
			if(ch == '[' || ch == '(') {
				stack.push(ch);
			}
			else {
				if(stack.isEmpty()) {
					return "No";
				}
				if(ch == ']') {
					if(stack.pop() != '[')
						return "No";
				}
				else { //ch == )
					if(stack.pop() != '(')
						return "No";
				}
			}
		}
		if(!stack.isEmpty()) {
			return "No";
		}
		
		return "Yes";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem614.in"));
		String line = "";
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; ++i) {
			System.out.println(solve(br.readLine()));
		}
	}
}
