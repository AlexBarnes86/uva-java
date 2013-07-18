package chapter6;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem10198 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	public static void main(String [] args) {
		PrintWriter pw = new PrintWriter(System.out);
		
		List<BigInteger> count = new ArrayList<BigInteger>();
		count.add(BigInteger.valueOf(1));
		count.add(BigInteger.valueOf(2));
		count.add(BigInteger.valueOf(5));
		count.add(BigInteger.valueOf(13));
		
		for(int i = 4; i <= 1000; ++i) {
			count.add(count.get(i-1).add(count.get(i-1).add(count.get(i-2)).add(count.get(i-3))));
		}
		
		while(sc.hasNext()) {
			int n = sc.nextInt();
			pw.println(count.get(n));
		}
		
		pw.flush();
	}
	
	public static BigInteger binomial(final int N, final int K) {
		BigInteger ret = BigInteger.ONE;
		for (int k = 0; k < K; k++) {
			ret = ret.multiply(BigInteger.valueOf(N-k)).divide(BigInteger.valueOf(k+1));
		}
		
		return ret;
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
				String fileName = "ProgrammingChallenges/input/chapter6/Problem10198.in";
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