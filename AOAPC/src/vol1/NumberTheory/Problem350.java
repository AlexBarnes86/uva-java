package vol1.NumberTheory;

import java.util.HashMap;
import java.util.Scanner;

public class Problem350 {
	public static int solve(int Z, int I, int M, int L) {
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		int n = (Z*L+I) % M;
		int ct = 1;
		visited.put(L, ct);
		
		while(!visited.containsKey(n)) {
			visited.put(n, ++ct);
			n = (Z*n+I) % M;
		}
		
		ct = 1;
		int end = n;
		while((n = (Z*n+I) % M) != end)
			ct++;
		
		return ct;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String line = "";
		int N = 1;
		while((line = sc.nextLine()) != null) {
			String [] tokens = line.trim().split("\\s+");
			int Z = Integer.parseInt(tokens[0]);
			int I = Integer.parseInt(tokens[1]);
			int M = Integer.parseInt(tokens[2]);
			int L = Integer.parseInt(tokens[3]);
			//System.out.println("DEBUG: " + Z + " " + I + " " + M + " " + L);
			if(Z == 0 && I == 0 && M == 0 && L == 0)
				break;
			System.out.println("Case " + N + ": " + solve(Z, I, M, L));
			N++;
		}
	}
}
