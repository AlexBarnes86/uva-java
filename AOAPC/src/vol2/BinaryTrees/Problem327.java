package vol2.BinaryTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Problem327 {
	public static LinkedList<String> tokenize(String line) {
		line = line.replaceAll("(\\+\\+|--|[a-z])", " $1 ").trim();
		String [] tokens = line.split("\\s+");
		LinkedList<String> res = new LinkedList<String>();
		Collections.addAll(res, tokens);
		return res;
	}
	
	public static int getVal(char ch) {
		return ch - 'a' + 1;
	}
	
	public static void solve(String line) {
		LinkedList<String> tokens = tokenize(line);
		HashMap<Character, Integer> vars = new HashMap<Character, Integer>();
		LinkedList<Character> postAdd = new LinkedList<Character>();
		LinkedList<Character> postSub = new LinkedList<Character>();
		
		int total = 0;
		char prev = ' ';
		boolean sub = false; //used when we have a-++c
		
		while(!tokens.isEmpty()) {
			String token = tokens.removeFirst();
			if(token.equals("--")) {
				if(prev >= 'a' && prev <= 'z') {
					postSub.add(prev);
				}
				else {
					prev = 'D';
				}
			}
			else if(token.equals("++")) {
				if(prev >= 'a' && prev <= 'z') {
					postAdd.add(prev);
				}
				prev = 'U';
			}
			else if(token.equals("+")) {
				prev = '+';
				sub = false;
			}
			else if(token.equals("-")) {
				prev = '-';
				sub = true;
			}
			else { //a-z, appears at most once in expr
				char ch = token.charAt(0);
				if(vars.get(token) == null) {
					vars.put(ch, getVal(ch));
				}
				
				switch(prev) {
				case 'U':
					vars.put(ch, vars.get(ch) + 1);
					if(sub) {
						total-=vars.get(ch);
					}
					else
						total+=vars.get(ch);
					break;
				case 'D':
					vars.put(ch, vars.get(ch) - 1);
					if(sub) {
						total-=vars.get(ch);
					}
					else
						total+=vars.get(ch);
					break;
				case '+':
					total+=vars.get(ch);
					break;
				case '-':
					total-=vars.get(ch);
					break;
				case ' ':
					total += vars.get(ch);
					break;
				}
				prev = ch;
			}
		}
		
		while(!postAdd.isEmpty()) {
			char ch = postAdd.pop();
			vars.put(ch, vars.get(ch)+1);
		}
		
		while(!postSub.isEmpty()) {
			char ch = postSub.pop();
			vars.put(ch, vars.get(ch)-1);
		}
		
		System.out.println("Expression: " + line);
		System.out.println("    value = " + total);
		ArrayList<Character> sortedKeys = new ArrayList<Character>();
		for(Character ch : vars.keySet()) {
			sortedKeys.add(ch);
		}
		Collections.sort(sortedKeys);
		for(Character ch : sortedKeys) {
			System.out.println("    " + ch + " = " + vars.get(ch));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input/vol2/BinaryTrees/Problem327.in"));
		String line = "";
		while((line = br.readLine()) != null) {
			solve(line);
		}
	}
}
