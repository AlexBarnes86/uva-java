package chapter5;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10077 {
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_FILE = false;
	public static final Scanner sc = getScanner();
	
	public static String solve(int m, int n) {
		debug(m + " / " + n);
		if(m == 0 || n == 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		if(m > n) {
			for(int i = 0; i < m / n; ++i) {
				sb.append("R");
			}
			debug(sb);
			return sb.append(solve(m % n, n)).toString();
		}
		else {
			for(int i = 0; i < n / m; ++i) {
				sb.append("L");
			}
			debug(sb);
			return sb.append(solve(m, n % m)).toString();
		}
	}
	
	public static void main(String[] args) {
		while(sc.hasNext()) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m == 1 && n == 1) {
				return;
			}
			
			String soln = solve(m, n);
			System.out.println(soln.substring(0, soln.length()-1));
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
				String fileName = "ProgrammingChallenges/input/chapter5/Problem10077.in";
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