package chapter7;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10139 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	private static class Factor {
		public long base;
		public long exponent;
		
		public Factor(long base, long exponent) {
			this.base = base;
			this.exponent = exponent;
		}
	}
	
	private static List<Factor> primeFactorization(long n) {
		List<Factor> primes = new ArrayList<Factor>();
		
		long k = n;
		long ct = 0;
		long end = (long)Math.sqrt(k)+1;
		
		for(long i = 2; i < end && k != 1; ++i) {
			ct = 0;
			
			while(k%i == 0) {
				ct++;
				k/=i;
			}
			
			if(ct != 0) {
				primes.add(new Factor(i, ct));
			}
		}
		
		if(k != 1) {
			primes.add(new Factor(k, 1));
		}
		
		return primes;
	}
	
	private static boolean dividesFactorial(long n, long m) {
		List<Factor> factors = primeFactorization(m);
		
		for(Factor factor : factors) {
			if(factor.exponent > getPowers(n, factor.base)) {
				return false;
			}
		}
		
		return true;
	}
	
	static int getPowers (long n, long p) {
		int res = 0;
		
		for (long power = p; power <= n; power *= p) {
			res += n / power;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			long n = Integer.parseInt(st.nextToken());
			long m = Integer.parseInt(st.nextToken());
			
			if(m != 0 && (n >= m || dividesFactorial(n, m))) {
				pw.println(m + " divides " + n + "!");
			} else {
				pw.println(m + " does not divide " + n + "!");
			}
		}
		
		pw.flush();
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
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10139.in";
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