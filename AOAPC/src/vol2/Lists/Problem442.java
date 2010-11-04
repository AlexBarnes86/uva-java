package vol2.Lists;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

public class Problem442 {
	static class Matrix {
		public int M, N;
		
		public Matrix() {
			M = N = 0;
		}
		
		public Matrix(int m, int n) {
			M = m;
			N = n;
		}
		
		public int countMults(Matrix m) {
			return M * N * m.N;
		}
		
		public String toString() {
			return "[" + M + ", " + N + "]";
		}
	}
	
	public static void solve(HashMap<Character, Matrix> matrices, String line) {
		LinkedList<Character> stack = new LinkedList<Character>();
		Matrix temp = null;
		int total = 0;
		
		for(int i = 0; i < line.length(); ++i) {
			char ch = line.charAt(i);
			if(ch != ')') {
				stack.push(ch);
				System.out.println("DEBUG: adding " + ch);
			}
			else {
				LinkedList<Matrix> queue = new LinkedList<Matrix>();
				System.out.println("DEBUG: stack " + stack);
				while((ch = stack.pop()) != '(') {
					//System.out.println("DEBUG: popping " + ch);
					if(ch == '*') {
						queue.addFirst(temp);
					}
					else {
						queue.addFirst(matrices.get(ch));
					}
				}
				
				System.out.println("DEBUG: queue " + queue);
				
				Matrix lhs = queue.removeFirst();
				while(!queue.isEmpty()) {
					Matrix rhs = queue.removeFirst();
					if(lhs.N != rhs.M) {
						System.out.print("DEBUG: 1st ");
						System.out.println("error");
						return;
					}
					total += lhs.countMults(rhs);
					lhs = new Matrix(lhs.M, rhs.N);
				}
				stack.push('*');
				temp = lhs;
			}
		}
		
		//after all parens dealt with
		LinkedList<Matrix> queue = new LinkedList<Matrix>();
		char ch;
		while(!stack.isEmpty()) {
			ch = stack.pop();
			if(ch == '*') {
				queue.addFirst(temp);
			}
			else {
				queue.addFirst(matrices.get(ch));
			}
		}
		Matrix lhs = queue.removeFirst();
		while(!queue.isEmpty()) {
			Matrix rhs = queue.removeFirst();
			if(lhs.N != rhs.M) {
				System.out.print("DEBUG: 2nd ");
				System.out.println("error");
				return;
			}
			total += lhs.countMults(rhs);
			lhs = new Matrix(lhs.M, rhs.N);
		}
		System.out.println(total);
	}
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem442.in"));
		String line = "";
		
		int N = Integer.parseInt(br.readLine());
		HashMap<Character, Matrix> matrices = new HashMap<Character, Matrix>();
		for(int i = 0; i < N; ++i) {
			line = br.readLine();
			String [] tokens = line.trim().split("\\s+");
			int m = Integer.parseInt(tokens[1]);
			int n = Integer.parseInt(tokens[2]);
			matrices.put(new Character(tokens[0].charAt(0)), new Matrix(m, n));
		}
		
		while((line = br.readLine()) != null) {
			solve(matrices, line);
		}
	}
}
