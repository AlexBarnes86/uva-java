package chapter7;

import java.io.File;
import java.util.Scanner;

public class Problem10090 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	//return array [d, a, b] such that d = gcd(p, q), ap + bq = d
	private static long[] gcd(long p, long q) {
		if (q == 0) {
			return new long[] { p, 1, 0 };
		}
		
		long[] vals = gcd(q, p % q);
		long d = vals[0];
		long a = vals[2];
		long b = vals[1] - (p / q) * vals[2];
		
		return new long[] { d, a, b };
	}
	
	private static long[] solve(long c1, long n1, long c2, long n2, long n) {
		long[] s = gcd(n1, n2);
		long gcd = s[0], m1 = s[1], m2 = s[2];
		long x, y;
		
		if (n % gcd != 0) {
			return null;
		}
		
		m1 *= n / gcd;
		m2 *= n / gcd;
		n2 /= gcd;
		n1 /= gcd;
		
		long c = (long)Math.ceil(-(double) m1 / n2), f = (long)Math.floor((double) m2 / n1);
		if (c > f) {
			return null;
		}
		
		long cost = c1 * n2 - c2 * n1;
		
		if (cost * c < cost * f) {
			x = m1 + n2 * c;
			y = m2 - n1 * c;
		}
		else {
			x = m1 + n2 * f;
			y = m2 - n1 * f;
		}
		
		return new long[] {x, y};
	}
	
	public static void main(String[] args) {
		while (sc.hasNext()) {
			int N = sc.nextInt();
			if(N != 0) {
				int c1 = sc.nextInt(), n1 = sc.nextInt(), c2 = sc.nextInt(), n2 = sc.nextInt();
				long[] solution = solve(c1, n1, c2, n2, N);
				if (solution != null) {
					System.out.println(solution[0] + " " + solution[1]);
				}
				else {
					System.out.println("failed");
				}
			}
		}
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			System.out.print("DEBUG: ");
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10090.in";
				Scanner sc = new Scanner(new File(fileName));
				System.out.println("Reading from: " + fileName);
				return sc;
			}
			else {
				return new Scanner(System.in);
			}
		}
		catch(Exception e) {
			return new Scanner(System.in);
		}
	}
}