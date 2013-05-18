package chapter3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Problem10132 {
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
				String debugFile = "ProgrammingChallenges/input/chapter3/Problem10132.in";
				System.out.println("Reading from debug file: " + debugFile);
				return new Scanner(new File(debugFile));
			}
			catch(Exception e) {
				System.out.println("Failed to read file, defaulting to System.in");
				return new Scanner(System.in);
			}
		}
	}
	
	public static String solve(List<String> fragments) {
		if(fragments.size() == 0) {
			return "";
		}
		
		Collections.sort(fragments, new Comparator<String> () {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.length() - arg1.length();
			}
		});
		
		int min = fragments.get(0).length();
		int max = fragments.get(fragments.size()-1).length();
		List<String> minStrings = new ArrayList<String>(); //these should only ever contain up to two strings each
		List<String> maxStrings = new ArrayList<String>();
		
		for(int j = 0; j < fragments.size(); ++j) {
			if(fragments.get(j).length() != min) {
				break;
			}
			if(!minStrings.contains(fragments.get(j))) {
				minStrings.add(fragments.get(j));
			}
		}
		
		for(int j = fragments.size()-1; j >= 0; --j) {
			if(fragments.get(j).length() != max) {
				break;
			}
			if(!maxStrings.contains(fragments.get(j))) {
				maxStrings.add(fragments.get(j));
			}
		}
		
		List<String> possibilities = new ArrayList<String>();
		for(String big : maxStrings) {
			for(String small : minStrings) {
				possibilities.add(big + small);
				possibilities.add(small + big);
			}
		}
		
//		debug("Fragments:");
//		debug(fragments);
//		debug("");
//		debug("Possibilities:");
//		debug(possibilities);
		
		for(String possibility : possibilities) {
			boolean allWork = true;
//			debug("Testing possibility: " + possibility);
			for(String fragment : fragments) {
//				debug("Testing fragment: " + fragment);
				if(!(possibility.startsWith(fragment) || possibility.endsWith(fragment))) {
//					debug("break");
					allWork = false;
					break;
				}
			}
			
			if(allWork) {
//				debug("Works: " + possibility);
				return possibility;
			}
		}
		
		return "";
	}
	
	private static final int MAX_FILE_LENGTH = 256;
	private static final int MAX_FILES = 144;
	
	public static void test() {
		while(true) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 1+(int)(Math.random()*(MAX_FILE_LENGTH-1)); ++i) {
				sb.append((int)(Math.random()*2));
			}
			String originalFile = sb.toString();
			
			List<String> fragments = new ArrayList<String>();
			for(int i = 0; i < 1+(int)(Math.random()*(MAX_FILES-1)); ++i) {
				int split = (int)(Math.random()*originalFile.length());
				fragments.add(originalFile.substring(0, split));
				fragments.add(originalFile.substring(split, originalFile.length()));
			}
			
			if(fragments.size() == 2) {
				continue;
			}
			
			String solution = solve(fragments);
			if(!originalFile.equals(solution)) {
				System.out.println("Does not work: ");
				System.out.println("Original File: " + originalFile);
				System.out.println("Solution Given: " + solution);
				System.out.println("Fragments: " + fragments);
			}
		}
	}
	
	public static void main(String[] args) {
//		test();
		
		int cases = Integer.parseInt(sc.nextLine());
		sc.nextLine(); //blank line
		
		for(int i = 0; i < cases; ++i) {
			List<String> fragments = new ArrayList<String>();
			String fragment = "";
			
			while(sc.hasNext() && !(fragment = sc.nextLine().trim()).isEmpty()) {
				fragments.add(fragment);
			}
			
			if(i != 0) {
				System.out.println();
			}
			System.out.println(solve(fragments));
		}
	}
}
