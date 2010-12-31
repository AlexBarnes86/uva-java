package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.LinkedList;

public class Problem327 {
	public static void solve(String line) {
		boolean [] postAdd = new boolean[26];
		boolean [] postSub = new boolean[26];
		
		int [] vars = new int [26];
		for(int i = 0; i < vars.length; ++i)
			vars[i] = -999;
		
		int total = 0;
		LinkedList<Character> stack = new LinkedList<Character>();

		for(int i = 0; i < line.length(); ++i) {
			char ch = line.charAt(i);
			if(Character.isWhitespace(ch)) {
				if(stack.peek() != null && stack.peek() != ' ')
					stack.push(' ');
				continue;
			}
			
			if(ch == '-') {
				if(stack.peek() != null && stack.peek() == '-') {
					stack.pop();
					if(stack.peek() != null && stack.peek() == ' ')
						stack.pop();
					if(stack.peek() != null && stack.peek() >= 'a' && stack.peek() <= 'z')
						postSub[stack.pop()-'a'] = true;
					else
						stack.push('D');
				} //not ++
				else
					stack.push('-');
			}
			else if(ch == '+') {
				if(stack.peek() != null && stack.peek() == '+') {
					stack.pop();
					if(stack.peek() != null && stack.peek() == ' ')
						stack.pop();
					if(stack.peek() != null && stack.peek() >= 'a' && stack.peek() <= 'z')
						postAdd[stack.pop()-'a'] = true;
					else
						stack.push('U');
				} //not ++
				else
					stack.push('+');
			}
			else {
				int pos = ch - 'a';
				vars[pos] = pos+1;
				
				if(stack.peek() != null && stack.peek() == ' ')
					stack.pop();
				
				if(stack.peek() != null && stack.peek() == 'U') {
					vars[pos]++;
					stack.pop();
				}
				else if(stack.peek() != null && stack.peek() == 'D') {
					vars[pos]--;
					stack.pop();
				}
				
				if(stack.peek() != null && stack.peek() == ' ')
					stack.pop();
				
				if(stack.peek() != null && stack.peek() == '-') {
					stack.pop();
					total-=vars[pos];
				}
				else {//if(stack.peek() == '+')
					if(stack.peek() != null)
						stack.pop();
					total+=vars[pos];
				}
				stack.push(ch);
			}
			
		}
		
		System.out.println("Expression: " + line);
		System.out.println("    value = " + total);
		for(int i = 0; i < vars.length; ++i) {
			if(vars[i] == -999)
				continue;
			if(postSub[i])
				vars[i]--;
			if(postAdd[i])
				vars[i]++;
			System.out.println("    " + (char)('a'+i) + " = " + vars[i]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem327-2.in"));
		String line = "";
		while((line = br.readLine()) != null) {
			solve(line);
		}
	}
}
