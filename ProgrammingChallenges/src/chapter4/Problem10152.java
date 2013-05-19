package chapter4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem10152 {	
	private static ArrayList<String> solve(ArrayList<String> orig, ArrayList<String> ordered) {
		ArrayList<String> sol = new ArrayList<String>();
		for(int i = ordered.size() - 1; i > 0; --i) {
			int largest_idx = orig.indexOf(ordered.get(i));
			int next_largest_idx = orig.indexOf(ordered.get(i-1));
			if(next_largest_idx > largest_idx) {
				sol.add(orig.get(next_largest_idx));
				orig.add(0, orig.remove(next_largest_idx));
			}
		}
		
		return sol;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("input/vol2/Problem10152.in"));
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; ++i) {
			int m = Integer.parseInt(br.readLine());
			ArrayList<String> orig = new ArrayList<String>();
			ArrayList<String> ordered = new ArrayList<String>();
			for(int j = 0; j < m; ++j) {
				orig.add(br.readLine());
			}
			for(int j = 0; j < m; ++j) {
				ordered.add(br.readLine());
			}
			ArrayList<String> solution = solve(orig, ordered);
			for(String str : solution) {
				System.out.println(str);
			}
			System.out.println();
		}
	}
}
