package chapter5;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10105 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = true;
	public static final Scanner sc = getScanner();
	
	private static BigInteger factorial(int n) {
		BigInteger fact = BigInteger.ONE;
		
		for(int i = 2; i <= n; ++i) {
			fact = fact.multiply(new BigInteger(String.valueOf(i)));
		}
		
		return fact;
	}
	
	private static BigInteger multinomialCoefficient(int n, List<Integer> exponents) {
		BigInteger numerator = factorial(n);
		BigInteger denominator = BigInteger.ONE;
		for(Integer exp : exponents) {
			denominator = denominator.multiply(factorial(exp));
		}
		
		return numerator.divide(denominator);
	}
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(sc.nextLine());
			List<Integer> exponents = new ArrayList<Integer>();
			while(st.hasMoreTokens()) {
				Integer exp = Integer.parseInt(st.nextToken());
				if(exp != 0) {
					exponents.add(exp);
				}
			}
			
			System.out.println(multinomialCoefficient(n, exponents));
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
				String fileName = "ProgrammingChallenges/input/chapter5/Problem10105.in";
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