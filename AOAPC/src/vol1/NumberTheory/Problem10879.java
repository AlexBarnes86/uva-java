package vol1.NumberTheory;

import java.util.Scanner;

public class Problem10879 {
	public static void solve(String line) {
		int n = Integer.parseInt(line.trim());
		int ct = 0;
		for(int i = 2; i < 10000000; ++i) { //in reality this will never go past 3163
			if(n % i == 0) {
				//System.out.println("\nDEBUG: " + n + " divisible by " + i);
				ct++;
				if(ct == 1) {
					System.out.print(i + " * " + n/i + " = ");
				} else if(ct == 2) {
					System.out.println(i + " * " + n/i);
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine().trim());
		for(int i = 0; i < n; ++i) {
			String line = sc.nextLine().trim();
			System.out.print("Case #" + (i+1) + ": " + line + " = ");
			solve(line);
		}
	}
}
