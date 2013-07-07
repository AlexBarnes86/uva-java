package chapter7;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TODO: This solution is a bit of a cheat, but anything goes on programming challenges.
//Most texts discussing number theory will discuss how to find Carmichael numbers.
//Note, this solution still took over 1.5 seconds. There must be very large input!
public class Problem10006 {
	private static final List<Integer> carmichaelNumbers = Arrays.asList(561, 1105, 1729, 2465, 2821, 6601, 8911, 10585, 15841, 29341, 41041, 46657, 52633, 62745, 63973, 75361);
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	public static void main(String[] args) {
		int n;
		while((n = sc.nextInt()) != 0) {
			if(carmichaelNumbers.contains(n)) {
				System.out.println("The number " + n + " is a Carmichael number.");
			}
			else {
				System.out.println(n + " is normal.");
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
				String fileName = "ProgrammingChallenges/input/chapter7/Problem10006.in";
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