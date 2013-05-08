package chapter3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem10082 {
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
				String debugFile = "ProgrammingChallenges/input/chapter3/Problem10082.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static final Map<Character, Character> shifted = getKeys();
	
	public static final Map<Character, Character> getKeys() {
		Map<Character, Character> keys = new HashMap<Character, Character>();
		
		String [] rows = {"`1234567890-=", "QWERTYUIOP[]\\", "ASDFGHJKL;'", "ZXCVBNM,./"};
		
		for(String row : rows) {
			for(int i = 1; i < row.length(); ++i) {
				keys.put(row.charAt(i), row.charAt(i-1));
			}
		}
		keys.put(' ', ' ');
		
		return keys;
	}
	
	public static String shift(String input) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); ++i) {
			sb.append(shifted.get(input.charAt(i)));
		}
		
		return sb.toString();
	}
	
	public static void main(String args[]) {
		String line;
		while(sc.hasNext() &&  (line = sc.nextLine()) != null) {
			System.out.println(shift(line));
		}
	}
}