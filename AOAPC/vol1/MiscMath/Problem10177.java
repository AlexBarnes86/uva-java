package AOAPC.vol1.MiscMath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problem10177 {
	public static int squares(int n, int d) { //Power Sum
		int sum = 0;
		for(int i = 1; i <= n; ++i) {
			sum += (int) Math.pow(i, d);
		}
		return sum;
	}
	
	public static BigInteger rectangles(int n, int d) {
		BigInteger sum = new BigInteger(n*(n+1)/2 + "");
		sum = sum.pow(d);
		return sum.subtract(new BigInteger(squares(n, d)+""));
	}
	
	public static void solve(int n) {
		for(int d = 2; d <= 4; ++d) {
			System.out.print(squares(n, d) + " " + rectangles(n, d));
			if(d != 4)
				System.out.print(" ");
		}
		System.out.println();
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		while((line = br.readLine()) != null) {
			solve(Integer.parseInt(line));
		}
	}
}
