package chapter5;

import java.io.PrintWriter;
import java.util.Scanner;

public class Problem10127 {
	private static final Scanner sc = new Scanner(System.in);
	
	// Say we have repeating decimal 0.d0d1...dmd0d1...dmd0d1...dm...
	// and x = d0d1...dm
	// and n = 0.xxxx...
	// and b = base in which we are working (base 10)
	// Then n*b^m = x.xxx...
	// and n*b^m - x = n
	// Thus n is of the form
	//    x / (b^m-1)
	
	// n*x = 111...1 (some number of ones)
	// 9*n*x = 999...9
	// 1 / 9*n = x / 999...9
	// The number of digits of x is m, the number of digits of 999...9 is also m because b^m-1
	// find the length of x by performing 1/9n until it repeats (until it equals 1 again)
	// e.g. perform manual long division with n = 3 on paper until we perform 190-189 = 1 (thus we know the continued fraction repeats at this point)
	public static int solve(int n) {
		int divisor = 9*n; //9*n because it is one less than the base in which we are working with.
		
		int base = 10;
		int start = base;
		int ones = 1;
		
		while((start %= divisor) != 1) {
			start *= base;
			ones++;
		}
		
		return ones;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			pw.println(solve(Integer.parseInt(sc.nextLine())));
		}
		
		pw.flush();
	}
}