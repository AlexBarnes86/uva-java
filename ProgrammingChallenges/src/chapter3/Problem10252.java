package chapter3;

import java.io.File;
import java.util.Scanner;

public class Problem10252 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_INPUT = false;
	
	public static void debug(Object msg) {
		if(DEBUG) {
			if(msg != null) {
				System.out.println(msg.toString());
			}
			else {
				System.out.println(msg);
			}
		}
	}
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		if(!DEBUG_INPUT) {
			return new Scanner(System.in);
		}
		else {
			try {
				String debugFile = "ProgrammingChallenges/input/chapter3/Problem10252.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static void main(String args[]) {
		String a = "";
		String b = "";
		while(sc.hasNext()) {
			int[] aCt = new int[26];
			int[] xCt = new int[26];
			
			a = sc.nextLine();
			b = sc.nextLine();
			
			for(int i = 0; i < a.length(); ++i) {
				aCt[a.charAt(i)-'a']++;
			}
			
			for(int i = 0; i < b.length(); ++i) {
				if(aCt[b.charAt(i)-'a'] > 0) {
					aCt[b.charAt(i)-'a']--;
					xCt[b.charAt(i)-'a']++;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < xCt.length; ++i) {
				for(int j = 0; j < xCt[i]; ++j) {
					sb.append((char)(((int)'a')+i));
				}
			}
			
			System.out.println(sb.toString());
		}
	}
}