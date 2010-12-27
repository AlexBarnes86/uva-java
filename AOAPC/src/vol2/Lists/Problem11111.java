package vol2.Lists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Problem11111 {
	static class Matrishoka {
		long val;
		long sum;
		
		public Matrishoka(long val) {
			this.val = val;
			sum = 0;
		}
		
		public boolean add(long child) {
			sum += child;
			if(sum >= val)
				return false;
			return true;
		}
	}
	public static String solve(String line) {
		LinkedList<Matrishoka> stack = new LinkedList<Matrishoka>();
		String[] tokens = line.trim().split("\\s+");
		for(String token : tokens) {
			long val = Long.parseLong(token);
			if(val < 0) {
				stack.push(new Matrishoka(-val));
			}
			else {
				if(stack.isEmpty()) {
					return ":-( Try again.";
				}
				if(val != stack.pop().val) {
					return ":-( Try again.";
				}
				if(!stack.isEmpty() && !stack.peek().add(val)) {
					return ":-( Try again.";
				}
			}
		}
		if(!stack.isEmpty())
			return ":-( Try again.";
		return ":-) Matrioshka!";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem11111.in"));
		String line = "";
		while((line = br.readLine()) != null) {
			System.out.println(solve(line));
		}
	}
}
