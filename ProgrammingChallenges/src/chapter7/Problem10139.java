package chapter7;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10139 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	private static Map<Integer, Integer> primeFactorization(int n) {
		Map<Integer, Integer> primes = new HashMap<Integer, Integer>();
		int k = n;
		
		for(int i = 2; i < k; ++i) {
			while(k%i == 0) {
				if(primes.containsKey(i)) {
					primes.put(i, primes.get(i)+1);
				} else {
					primes.put(i, 1);
				}
				k/=i;
			}
		}
		
		if(k != 1) {
			primes.put(k, 1);
		}
		
		return primes;
	}
	
	private static boolean dividesFactorial(int n, int m) {
		Map<Integer, Integer> factors = primeFactorization(m);
		
		for(Map.Entry<Integer, Integer> entry : factors.entrySet()) {
			if(entry.getValue() > (n / entry.getKey())) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		while(sc.hasNext()) {
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(m != 0 && dividesFactorial(n, m)) {
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