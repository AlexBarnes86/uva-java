package chapter5;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem10018 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_INPUT = true;
	public static final Scanner sc = getScanner();
	
	private static String solve(String number) {
		int ct = 1;
		BigInteger forward = new BigInteger(number);
		while(true) {
			BigInteger reverse = new BigInteger(new StringBuffer(forward.toString()).reverse().toString());
			BigInteger add = forward.add(reverse);
			
			if(add.toString().equals(new StringBuffer(add.toString()).reverse().toString())) {
				return ct + " " + add;
			}
			forward = add;
			ct++;
		}
	}
	
	public static void main(String args[]) {
		PrintWriter pw = new PrintWriter(System.out);
		
		int cases = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < cases; ++i) {
			pw.println(solve(sc.nextLine()));
		}
		
		pw.flush();
	}
	
	public static void debug(Object msg) {
		if(DEBUG) {
			if(msg != null) {
				System.out.println("DEBUG: " + msg.toString());
			}
			else {
				System.out.println("DEBUG: " + msg);
			}
		}
	}
	
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter5/Problem10018.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
}