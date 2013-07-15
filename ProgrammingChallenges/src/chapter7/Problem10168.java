package chapter7;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem10168 {
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
	
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		final int MAX = 10000001;
		boolean[] primes = seive(MAX);
		
		while(sc.hasNext()) {
			int n = sc.nextInt();
			
			if(n < 8) {
				pw.println("Impossible.");
			}
			else {
				if(n % 2 == 0) {
					pw.print("2 2 ");
					n -= 4;
				}
				else {
					pw.print("2 3 ");
					n -= 5;
				}
				
				int i;
				for(i = 2; i < n; ++i) {
					if(!primes[i] && !primes[n-i]) {
						break;
					}
				}
				
				pw.println(i + " " + (n-i));
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
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10168.in";
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