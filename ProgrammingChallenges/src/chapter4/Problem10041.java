package chapter4;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Problem10041 {
	public static final boolean DEBUG = false;
	public static final boolean DEBUG_FILE = false;
	
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
	
	public static final Scanner sc = getScanner();
	public static Scanner getScanner() {
		try {
			if(DEBUG_FILE) {
				String fileName = "ProgrammingChallenges/input/chapter4/Problem10041.in";
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
	
	public static void main(String[] args) {
		int cases = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < cases; ++i) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			int r = Integer.parseInt(st.nextToken());
			List<Integer> numbers = new ArrayList<Integer>();
			long total = 0;
			while(st.hasMoreTokens()) {
				int number = Integer.parseInt(st.nextToken());
				numbers.add(number);
				total += number;
			}
			Collections.sort(numbers);
			int medianLow = numbers.get(numbers.size()/2);
			int medianHigh = 0;
			if(numbers.size() % 2 == 0) {
				medianHigh = medianLow;
			}
			else {
				medianHigh = numbers.get(numbers.size()/2+1); 
			}
			
			long distanceLow = 0;
			long distanceHigh = 0;
			
			for(Integer number : numbers) {
				distanceLow += (long)(Math.abs(medianLow-number));
				distanceHigh += (long)(Math.abs(medianHigh-number));
			}
			
			System.out.println(distanceLow < distanceHigh ? distanceLow : distanceHigh);
		}
	}
}
