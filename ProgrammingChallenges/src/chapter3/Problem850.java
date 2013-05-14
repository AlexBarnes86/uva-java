package chapter3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem850 {
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
				String debugFile = "ProgrammingChallenges/input/chapter3/Problem850.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static final String NO_SOLUTION = "No solution.";
	public static final String FOX = "the quick brown fox jumps over the lazy dog";
	
	private static List<List<Integer>> appearances = getAppearances(FOX);
	
	private static List<List<Integer>> getAppearances(String line) {
		List<List<Integer>> firstPositions = new ArrayList<List<Integer>>();
		
		for(char ch = 'a'; ch <= 'z'; ++ch) {
			firstPositions.add(new ArrayList<Integer>());
		}
		firstPositions.add(new ArrayList<Integer>()); // ' '
		
		for(int i = 0; i < line.length(); ++i) {
			int chIdx = -1;
			if(line.charAt(i) != ' ') {
				chIdx = line.charAt(i) - 'a';
			}
			else {
				chIdx = firstPositions.size()-1;
			}
			firstPositions.get(chIdx).add(i);
		}
		
		return firstPositions;
	}
	
	public static void main(String args[]) {
		int cases = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //blank line between cases
		
		for(int i = 0; i < cases; ++i) {
			ArrayList<String> input = new ArrayList<String>();
			String line = null;
			Map<Character, Character> code = null;
			
			while(sc.hasNext() && (line = sc.nextLine()) != null) {
				if(line.isEmpty()) {
					break;
				}
				
				if(code == null && foxMatch(line)) {
					code = getKey(line);
				}
				
				input.add(line);
			}
			
			if(i != 0) {
				System.out.println();
			}
			
			if(code == null) {
				System.out.println(NO_SOLUTION);
			}
			else {
				System.out.println(decrypt(input, code));
			}
		}
	}
	
	private static String decrypt(ArrayList<String> input, Map<Character, Character> code) {
		StringBuilder sb = new StringBuilder();
		for(String line : input) {
			for(char ch : line.toCharArray()) {
				sb.append(code.get(ch));
			}
			sb.append("\n");
		}
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	private static Map<Character, Character> getKey(String line) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		
		for(int i = 0; i < FOX.length(); ++i) {
			map.put(line.charAt(i), FOX.charAt(i));
		}
		
		return map;
	}
	
	//0         1         2         3         4
	//0123456789012345678901234567890123456789012
	//the quick brown fox jumps over the lazy dog
	private static boolean foxMatch(String line) {
		if(line.length() != FOX.length()) {
			return false;
		}
		
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put(' ', ' ');
		
		for(int i = 0; i < line.length(); ++i) {
			Character letter = map.get(line.charAt(i));
			
			if(letter == null) {
				for(char ch = 'a'; ch <= 'z'; ++ch) { // look for the corresponding character for matching up position appearances
					List<Integer> positions = appearances.get(ch - 'a');
					if(positions.size() > 0 && positions.get(0) == i && !map.containsValue(ch)) {
						map.put(line.charAt(i), ch);
						break;
					}
				}
			}
			
			letter = map.get(line.charAt(i));
			if(letter == null) {
				return false;
			}
			int chIdx = -1;
			if(letter.equals(' ')) {
				chIdx = appearances.size()-1;
			}
			else {
				chIdx = letter - 'a';
			}
			
			if(!appearances.get(chIdx).contains(i)) {
				return false;
			}
		}
		
		return true;
	}
}