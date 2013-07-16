package chapter7;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10042 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	private static boolean[] seive(int n) {
		boolean[] primes = new boolean[n];
		int end = (int)Math.sqrt(n)+1;
		
		for(int i = 2; i <= end; ++i) {
			if(!primes[i]) {
				for(int j = i+i; j < n; j += i) {
					primes[j] = true;
				}
			}
		}
		
		primes[0] = true;
		primes[1] = true;
		
		return primes;
	}
	
	private static class Factor {
		public long base;
		public long exponent;
		
		public Factor(long base, long exponent) {
			this.base = base;
			this.exponent = exponent;
		}
		
		public String toString() {
			return base + "^" + exponent;
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
	
	private static int sumDigits(long i) {
		long n = i;
		int sum = 0;
		while(n > 0) {
			sum += n % 10;
			n /= 10;
		}
		
		return sum;
	}
	
//	public static void main(String[] args) {
//		int max = 1000000;
////		debug("Finding primes");
//		boolean[] primes = seive(max);
////		debug("Done finding primes");
//		int ct = 0;
//		for(int i = 2; i < max; ++i) {
//			if(primes[i]) {
//				int sumPhoneNumber = sumDigits(i);
//				List<Factor> factors = primeFactorization(i);
//				int sumFactors = 0;
//				for(Factor factor : factors) {
//					sumFactors += (sumDigits(factor.base)*factor.exponent);
//				}
//				if(sumPhoneNumber == sumFactors) {
////					debug(i + ", " + factors + ", " + sumPhoneNumber + ", " + sumFactors);
//					ct++;
//				}
//			}
//		}
//		System.out.println("Found " + ct + " Smith Numbers");
//	}
	
//	public static void main(String[] args) {
//		System.out.println("Finding Primes");
//		int max = 1000000000;
//		int shortStop = max / 100;
//		int longStop = max / 10;
//		
//		boolean [] primes = seive(max);
//		
//		System.out.println("Finding lies");
//		
//		int ct = 7;
//		boolean wrong = true;
//		while(wrong) {
//			ct++;
//			int i = 1;
//			for(i = 2; i < max; ++i) {
//				if(i % shortStop == 0) {
//					System.out.print(".");
//				}
//				if(i % longStop == 0) {
//					System.out.println();
//				}
//				if(primes[i] == (new BigInteger(i+"").isProbablePrime(ct))) {
//					System.out.println(ct + ": Big Integer Lied on " + i);
//					break;
//				}
//			}
//			
//			if(i == max) {
//				System.out.println(ct + " works");
//				wrong = false;
//			}
//		}
//	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		int cases = sc.nextInt();
		
		for(int ct = 0; ct < cases; ++ct) {
			int n = sc.nextInt();
			int i = n+1;
			while(true) {
				int sumPhoneNumber = sumDigits(i);
				List<Factor> factors = primeFactorization(i);
				int sumFactors = 0;
				for(Factor factor : factors) {
					sumFactors += (sumDigits(factor.base)*factor.exponent);
				}
				//9 is minimally sufficient for all integers up to 1000000000 (tested against a sieve)
				if(sumPhoneNumber == sumFactors && !(new BigInteger(i+"").isProbablePrime(9))) {
					pw.println(i);
					break;
				}
				i++;
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
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10042.in";
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