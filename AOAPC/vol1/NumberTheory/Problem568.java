package AOAPC.vol1.NumberTheory;

import java.util.Scanner;

public class Problem568 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String line = "";
		int [] sol = new int[10001];
		sol[0] = 1;
		for(int i = 1; i < 10001; ++i) {
			sol[i] = sol[i-1]*i;
			while(sol[i] % 10 == 0)
				sol[i] /= 10;
			sol[i] = sol[i] % 100000;
		}
		
		while(sc.hasNext()) {
			line = sc.nextLine().trim();
			int n = Integer.parseInt(line);
			System.out.printf("%5d -> %d\n", n, (sol[n]%10));
		}
	}
}
